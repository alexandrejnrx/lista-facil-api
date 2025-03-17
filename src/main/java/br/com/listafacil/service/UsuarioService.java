package br.com.listafacil.service;

import br.com.listafacil.dto.UsuarioRequestDTO;
import br.com.listafacil.dto.UsuarioResponseDTO;
import br.com.listafacil.dto.converter.UsuarioConverter;
import br.com.listafacil.model.Usuario;
import br.com.listafacil.repository.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private static final String ERRO_EMAIL_CADASTRADO = "E-mail já cadastrado!";
    private static final String ERRO_CAMPO_OBRIGATORIO = "Preencha todos os campos!";
    private static final String ERRO_EMAIL_INVALIDO = "O e-mail informado é invalido.";
    private static final String ERRO_EMAIL_INCORRETO = "E-mail incorreto!";
    private static final String ERRO_SENHA_INCORRETA = "Senha incorreta!";
    private static final String ERRO_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_EMAIL_CADASTRADO);
        }

        Usuario usuarioParaCadastrar = UsuarioConverter.converterDTOParaEntidade(usuarioRequestDTO);
        usuarioParaCadastrar.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
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

        if (usuarioParaDeletar == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_EMAIL_INCORRETO);
        }

        if (!passwordEncoder.matches(usuarioRequestDTO.getSenha(), usuarioParaDeletar.getSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_SENHA_INCORRETA);
        }

        usuarioRepository.delete(usuarioParaDeletar);
    }

    public void atualizarDadosUsuario(Integer id, UsuarioRequestDTO usuarioRequestDTO) {
        Optional<Usuario> usuarioOptionalEncontrado = usuarioRepository.findById(id);

        if (usuarioOptionalEncontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_USUARIO_NAO_ENCONTRADO);
        }

        Usuario usuarioParaAtualizar = usuarioOptionalEncontrado.get();

        if (usuarioRequestDTO.getNome() != null && !usuarioRequestDTO.getNome().isEmpty()) {
            usuarioParaAtualizar.setNome(usuarioRequestDTO.getNome());
        }

        if (usuarioRequestDTO.getEmail() != null && !usuarioRequestDTO.getEmail().isEmpty()) {
            usuarioParaAtualizar.setEmail(usuarioRequestDTO.getEmail());
        }

        if (usuarioRequestDTO.getSenha() != null && !usuarioRequestDTO.getSenha().isEmpty()) {
            usuarioParaAtualizar.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        }

        usuarioRepository.save(usuarioParaAtualizar);
    }

}
