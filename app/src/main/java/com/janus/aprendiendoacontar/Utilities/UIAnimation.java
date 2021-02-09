package com.janus.aprendiendoacontar.Utilities;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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

    public static void stopViewAnimation(View view){
        view.clearAnimation();
    }

    public static void translateIn_RightToLeft(Context context, View view){
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.translate_in_left_to_right);
        view.startAnimation(anim);
    }

    public static void translateIn_LeftToRight(Context context, View view){
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.translate_in_right_to_left);
        view.startAnimation(anim);
    }

    
}
