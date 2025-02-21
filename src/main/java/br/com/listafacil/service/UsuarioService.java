package br.com.listafacil.service;

import br.com.listafacil.dto.UsuarioRequestDTO;
import br.com.listafacil.dto.converter.UsuarioConverter;
import br.com.listafacil.model.Usuario;
import br.com.listafacil.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        String emailParaCadastrar = usuarioRequestDTO.getEmail();

        if (usuarioRepository.findByEmail(emailParaCadastrar) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado!");
        }

        Usuario usuarioParaCadastrar = UsuarioConverter.converterDTOParaEntidade(usuarioRequestDTO);
        usuarioRepository.save(usuarioParaCadastrar);
    }

}
