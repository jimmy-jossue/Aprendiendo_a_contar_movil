package com.cyti.aprendiendoacontar.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {

    //Retorna un objeto de tipo Usuario
    @Query("SELECT * FROM Usuario WHERE id = :id LIMIT 1")
    Usuario obtenerporId(int id);

    //Retorna una lista de objetos de tipo Usuario
    @Query("SELECT * FROM usuario")
    List<Usuario> obtenerTodos();

    //Retorna un objeto de tipo Usuario
    @Query("SELECT * FROM Usuario WHERE nombre = :nombre LIMIT 1")
    Usuario buscarPorNombre(String nombre);

    //Retorna el id del Usuario que se inserte
    @Insert
    long Insertar(Usuario usuario);

    @Update
    void Editar(Usuario usuario);

}
