package com.jaranas.caseTwo.dao;

import com.jaranas.caseTwo.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrarUsuario(Usuario usuario);

    abstract Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
