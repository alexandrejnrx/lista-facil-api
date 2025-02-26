package br.com.listafacil.controller;

import br.com.listafacil.dto.UsuarioRequestDTO;
import br.com.listafacil.dto.UsuarioResponseDTO;
import br.com.listafacil.model.Usuario;
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
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/cadastrar-usuario")
    public void cadastrarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.cadastrarUsuario(usuarioRequestDTO);
    }

    @DeleteMapping("/deletar-usuario")
    public void deletarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.deletarUsuario(usuarioRequestDTO);
    }

}
