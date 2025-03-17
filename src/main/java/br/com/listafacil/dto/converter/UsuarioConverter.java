package br.com.listafacil.dto.converter;

import br.com.listafacil.dto.UsuarioRequestDTO;
import br.com.listafacil.dto.UsuarioResponseDTO;
import br.com.listafacil.model.Usuario;

public final class UsuarioConverter {

    public static Usuario converterDTOParaEntidade(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha());

        return usuario;
    }

    public static UsuarioResponseDTO converterEntidadeParaDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setEmail(usuario.getEmail());

        return usuarioResponseDTO;
    }

}
