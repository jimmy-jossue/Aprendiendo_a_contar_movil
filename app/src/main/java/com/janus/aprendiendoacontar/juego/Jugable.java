package com.janus.aprendiendoacontar.juego;

public interface Jugable {

    void correcto();
    void incorrecto();

    void finish(int correctos, int incorrectos);
}
