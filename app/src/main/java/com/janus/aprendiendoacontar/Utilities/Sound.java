package com.janus.aprendiendoacontar.Utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.janus.aprendiendoacontar.R;

public class Sound {
    static MediaPlayer sound;
    Context context;

    public Sound(Context context) {
        this.context = context;
    }

    private static int buscarAudioNombreCantidad(int cantidad) {
        int idAudioCantiidad = R.raw.conoce_1;

        switch (cantidad) {
            case 1:
                idAudioCantiidad = R.raw.conoce_1;
                break;
            case 2:
                idAudioCantiidad = R.raw.conoce_2;
                break;
            case 3:
                idAudioCantiidad = R.raw.conoce_3;
                break;
            case 4:
                idAudioCantiidad = R.raw.conoce_4;
                break;
            case 5:
                idAudioCantiidad = R.raw.conoce_5;
                break;
            case 6:
                idAudioCantiidad = R.raw.conoce_6;
                break;
            case 7:
                idAudioCantiidad = R.raw.conoce_7;
                break;
            case 8:
                idAudioCantiidad = R.raw.conoce_8;
                break;
            case 9:
                idAudioCantiidad = R.raw.conoce_9;
                break;
            case 10:
                idAudioCantiidad = R.raw.conoce_10;
                break;
            case 11:
                idAudioCantiidad = R.raw.conoce_11;
                break;
            case 12:
                idAudioCantiidad = R.raw.conoce_12;
                break;
            case 13:
                idAudioCantiidad = R.raw.conoce_13;
                break;
            case 14:
                idAudioCantiidad = R.raw.conoce_14;
                break;
            case 15:
                idAudioCantiidad = R.raw.conoce_15;
                break;
            case 16:
                idAudioCantiidad = R.raw.conoce_16;
                break;
            case 17:
                idAudioCantiidad = R.raw.conoce_17;
                break;
            case 18:
                idAudioCantiidad = R.raw.conoce_18;
                break;
            case 19:
                idAudioCantiidad = R.raw.conoce_19;
                break;
            case 20:
                idAudioCantiidad = R.raw.conoce_20;
                break;
        }
        return idAudioCantiidad;
    }

    public void playSonidoCantidad(int cantidad) {
        int idSonido = buscarAudioNombreCantidad(cantidad);

        stopPlaying();
        sound = MediaPlayer.create(context, idSonido);
        sound.start();
    }

    public void stopPlaying() {
        if (sound != null) {
            sound.stop();
            sound.release();
            sound = null;
        }
    }

    public void play(int idSonido) {
        stopPlaying();
        sound = MediaPlayer.create(context, idSonido);
        sound.start();
    }
}
