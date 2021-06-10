package com.cyti.aprendiendoacontar.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.cyti.aprendiendoacontar.R;

public class Recordar {

    // Guarda los los avances de las actividades en las preferencias compartidas de android
    public static void recordarActividad(Context context, String actividad, int avance) {
        int avanceAGuardar = esMayor(context, actividad, avance);

        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(actividad, avanceAGuardar);
        editor.apply();
    }

    // Combrueba si es mayor el avance guardado en las preferencias del usuario o
    // si es mayor el nuevo avance que se va a ingresar
    // retorna el mayor de ellos
    public static int esMayor(Context context, String actividad, int avance) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
        int avancaGuardado = preferences.getInt(actividad, -1);

        return Math.max(avance, avancaGuardado);
    }

}


