package br.com.listafacil.controller;

import br.com.listafacil.models.UsuarioModel;
import br.com.listafacil.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar-usuarios")
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/cadastrar-usuario")
    public void cadastrarUsuario(@RequestBody UsuarioModel usuario) {
        usuarioService.cadastrarUsuario(usuario);
    }

}
