package br.com.listafacil.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    private Integer id;
    private String nome;
    private String email;
    private String senha;

}
