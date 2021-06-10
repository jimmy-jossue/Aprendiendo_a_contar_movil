package com.janus.aprendiendoacontar.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class, Actividad.class}, version = 2, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "Aprendiendo_a_contar";

    private static DataBase database;

    public abstract UsuarioDao getUsuario();

    public abstract ActividadDao getActividad();

    //Crea una instancia de la clase que se conecta con la base de datos de Room
    public static DataBase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, DataBase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}
