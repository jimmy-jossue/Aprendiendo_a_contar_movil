package com.cyti.aprendiendoacontar.juego;

import android.view.View;

public interface Jugable {

    void correcto();

    void incorrecto();

    void finish(View viewDestino, int correctos, int incorrectos);
}
