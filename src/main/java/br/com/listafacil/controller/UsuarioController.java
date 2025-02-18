package br.com.listafacil.controller;

import br.com.listafacil.models.UsuarioModel;
import br.com.listafacil.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
