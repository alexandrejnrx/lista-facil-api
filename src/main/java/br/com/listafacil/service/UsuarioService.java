package br.com.listafacil.service;

import br.com.listafacil.model.Usuario;
import br.com.listafacil.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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

    public void cadastrarUsuario(Usuario usuario) {
        String emailParaCadastrar = usuario.getEmail();

        if (usuarioRepository.findByEmail(emailParaCadastrar) != null) {
            String message = "Usuário já cadastrado";
        }

        usuarioRepository.save(usuario);
    }

}
