package br.com.listafacil.service;

import br.com.listafacil.dto.UsuarioRequestDTO;
import br.com.listafacil.dto.UsuarioResponseDTO;
import br.com.listafacil.dto.converter.UsuarioConverter;
import br.com.listafacil.model.Usuario;
import br.com.listafacil.repository.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private static final String ERRO_EMAIL_CADASTRADO = "E-mail já cadastrado!";
    private static final String ERRO_CAMPO_OBRIGATORIO = "Preencha todos os campos!";
    private static final String ERRO_EMAIL_INVALIDO = "O e-mail informado é invalido.";

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioConverter::converterEntidadeParaDTO)
                .collect(Collectors.toList());
    }

    public void cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        validarCamposObrigatorios(usuarioRequestDTO);
        validarEmail(usuarioRequestDTO.getEmail());
        String emailNormalizado = usuarioRequestDTO.getEmail().toLowerCase();

        if (usuarioRepository.findByEmail(emailNormalizado) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_EMAIL_CADASTRADO + usuarioRequestDTO.getEmail());
        }

        Usuario usuarioParaCadastrar = UsuarioConverter.converterDTOParaEntidade(usuarioRequestDTO);
        usuarioRepository.save(usuarioParaCadastrar);
    }

    private void validarCamposObrigatorios(UsuarioRequestDTO usuarioRequestDTO) {
        if (StringUtils.isBlank(usuarioRequestDTO.getNome()) ||
                StringUtils.isBlank(usuarioRequestDTO.getEmail()) ||
                StringUtils.isBlank(usuarioRequestDTO.getSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_CAMPO_OBRIGATORIO);
        }
    }

    private void validarEmail(String email) {
        String regex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_EMAIL_INVALIDO);
        }
    }

    public void deletarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioParaDeletar = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());

        usuarioRepository.delete(usuarioParaDeletar);
    }

}
