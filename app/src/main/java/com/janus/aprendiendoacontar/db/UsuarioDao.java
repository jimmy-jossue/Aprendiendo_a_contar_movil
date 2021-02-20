package com.janus.aprendiendoacontar.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    List<Usuario> obtenerTodos();

//    @Query("SELECT * FROM usuario WHERE id IN (:usuarioIds)")
//    List<Usuario> loadAllByIds(int[] usuarioIds);

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre LIMIT 1")
    Usuario buscarPorNombre(String nombre);

    @Insert
    void Insertar(Usuario... usuarios);

    @Update
    void updateUsers(Usuario... usuarios);

}
