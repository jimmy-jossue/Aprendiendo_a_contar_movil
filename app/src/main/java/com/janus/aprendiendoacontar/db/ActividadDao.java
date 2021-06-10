package com.janus.aprendiendoacontar.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Interface que contiene los metodos que se realizaran sobre la entidad "actividad" de la base de datos
@Dao
public interface ActividadDao {

    //Retorna un objeto de tipo actividad
    @Query("SELECT * FROM Actividad WHERE id = :id LIMIT 1")
    Actividad obtenerporId(int id);

    //Retorna una lista de objetos de tipo actividad
    @Query("SELECT * FROM Actividad ORDER BY id DESC")
    List<Actividad> obtenerTodos();

    //Retorna un objeto de tipo actividad
    @Query("SELECT * FROM Actividad WHERE nombre = :nombre ORDER BY id DESC LIMIT 1")
    Actividad buscarPorNombre(String nombre);

    //Retorna el id de la actividad que se inserte
    @Insert
    long Insertar(Actividad actividad);

    @Update
    void update(Actividad actividad);
}
