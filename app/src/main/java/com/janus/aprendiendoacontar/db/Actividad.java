package com.janus.aprendiendoacontar.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Clase que representa la entidad "Actividad" de la base de datos o un DTO
@Entity
public class Actividad {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "ejerciciosCorrectos")
    public int ejerciciosCorrectos = 0;

    @ColumnInfo(name = "ejerciciosIncorrectos")
    public int ejerciciosIncorrectos = 0;
}