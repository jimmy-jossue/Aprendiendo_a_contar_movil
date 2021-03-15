package com.janus.aprendiendoacontar.Utilities;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.AnimRes;

import com.janus.aprendiendoacontar.R;

public class UIAnimation {

    public static void onInfiniteScale(Context context, View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.infinite_scale);
        view.startAnimation(anim);
    }

    public static void onScaleZoomIn(Context context, View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_zoom_in);
        view.startAnimation(anim);
    }

    public static void onScaleZoomOut(Context context, View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_zoom_out);
        view.startAnimation(anim);
    }

    public static void stopViewAnimation(View view) {
        view.clearAnimation();
    }

    public static void translateIn_LeftToRight(Context context, View view, @AnimRes int idAnimation) {
        Animation anim = AnimationUtils.loadAnimation(context, idAnimation);
        view.startAnimation(anim);
    }


}
