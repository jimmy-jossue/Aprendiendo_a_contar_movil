package com.janus.aprendiendoacontar.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.janus.aprendiendoacontar.R;

public class Recordar {

    public static void recordarActividad(Context context, String actividad, int avance) {
        int avanceAGuardar = esMayor(context, actividad, avance);

        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(actividad, avanceAGuardar);
        editor.apply();
    }

    public static int esMayor(Context context, String actividad, int avance) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
        int avancaGuardado = preferences.getInt(actividad, -1);

        return Math.max(avance, avancaGuardado);
    }

}


