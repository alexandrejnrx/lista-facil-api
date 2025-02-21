package br.com.listafacil.dto.converter;

import br.com.listafacil.dto.UsuarioRequestDTO;
import br.com.listafacil.model.Usuario;

public final class UsuarioConverter {

    public static Usuario converterDTOParaEntidade(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha());

        return usuario;
    }

    public static UsuarioRequestDTO converterEntidadeParaDTO(Usuario usuario) {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();

        usuarioRequestDTO.setNome(usuario.getNome());
        usuarioRequestDTO.setEmail(usuario.getEmail());

        return usuarioRequestDTO;
    }

}
