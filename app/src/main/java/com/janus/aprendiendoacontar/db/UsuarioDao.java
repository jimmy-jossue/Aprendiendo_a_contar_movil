package com.janus.aprendiendoacontar.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM Usuario WHERE id = :id LIMIT 1")
    Usuario obtenerporId(int id);

    @Query("SELECT * FROM usuario")
    List<Usuario> obtenerTodos();

//    @Query("SELECT * FROM usuario WHERE id IN (:usuarioIds)")
//    List<Usuario> loadAllByIds(int[] usuarioIds);

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre LIMIT 1")
    Usuario buscarPorNombre(String nombre);

    @Insert
    long Insertar(Usuario usuario);

    @Update
    void Editar(Usuario usuario);

}
