package com.janus.aprendiendoacontar.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ActividadDao {

    @Query("SELECT * FROM Actividad WHERE id = :id LIMIT 1")
    Actividad obtenerporId(int id);

    @Query("SELECT * FROM Actividad")
    List<Actividad> obtenerTodos();

    @Query("SELECT * FROM Actividad WHERE nombre = :nombre LIMIT 1")
    Actividad buscarPorNombre(String nombre);

    @Insert
    long Insertar(Actividad actividad);

    @Update
    void update(Actividad actividad);
}
