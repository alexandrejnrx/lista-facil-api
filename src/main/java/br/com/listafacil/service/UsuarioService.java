package br.com.listafacil.service;

import br.com.listafacil.dto.UsuarioRequestDTO;
import br.com.listafacil.dto.UsuarioResponseDTO;
import br.com.listafacil.dto.converter.UsuarioConverter;
import br.com.listafacil.model.Usuario;
import br.com.listafacil.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

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
        String emailParaCadastrar = usuarioRequestDTO.getEmail();

        if (usuarioRepository.findByEmail(emailParaCadastrar) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado!");
        }

        Usuario usuarioParaCadastrar = UsuarioConverter.converterDTOParaEntidade(usuarioRequestDTO);
        usuarioRepository.save(usuarioParaCadastrar);
    }

    public void deletarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioParaDeletar = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());

        usuarioRepository.delete(usuarioParaDeletar);
    }

}
