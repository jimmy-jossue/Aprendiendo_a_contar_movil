package com.cyti.aprendiendoacontar.Utilities;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;

import com.cyti.aprendiendoacontar.R;

public class UIAnimation {

    //Reproduce una animacion de escalado infinito en la vista que se ingrese como parametro
    public static void onInfiniteScale(Context context, @NonNull View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.infinite_scale);
        view.startAnimation(anim);
    }

    //Reproduce una animacion de escalado hacia dentro en la vista que se ingrese como parametro
    public static void onScaleZoomIn(Context context, @NonNull View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_zoom_in);
        view.startAnimation(anim);
    }

    //Reproduce una animacion de escalado hacia fuera en la vista que se ingrese como parametro
    public static void onScaleZoomOut(Context context, @NonNull View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_zoom_out);
        view.startAnimation(anim);
    }

    // borra la animacion de la vista que se ingrese como parametro
    public static void stopViewAnimation(@NonNull View view) {
        view.clearAnimation();
    }

    //Reproduce una animacion de traslado de izquierda a derecha en la vista que se ingrese como parametro
    public static void translateIn_LeftToRight(Context context, @NonNull View view, @AnimRes int idAnimation) {
        Animation anim = AnimationUtils.loadAnimation(context, idAnimation);
        view.startAnimation(anim);
    }
}
