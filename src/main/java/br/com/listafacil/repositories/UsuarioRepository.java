package br.com.listafacil.repositories;

import br.com.listafacil.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
}
