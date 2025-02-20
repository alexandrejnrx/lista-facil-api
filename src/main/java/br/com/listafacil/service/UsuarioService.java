package br.com.listafacil.service;

import br.com.listafacil.models.UsuarioModel;
import br.com.listafacil.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void cadastrarUsuario(UsuarioModel usuario) {
        String emailParaCadastrar = usuario.getEmail();

        if (usuarioRepository.findByEmail(emailParaCadastrar) != null) {
            String message = "Usuário já cadastrado";
        }

        usuarioRepository.save(usuario);
    }

}
