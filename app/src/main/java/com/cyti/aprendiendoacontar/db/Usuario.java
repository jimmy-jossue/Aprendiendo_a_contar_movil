package com.cyti.aprendiendoacontar.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Clase que representa la entidad "Usuario" de la base de datos o un DTO
@Entity
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "imagen")
    public int imagen;
}
