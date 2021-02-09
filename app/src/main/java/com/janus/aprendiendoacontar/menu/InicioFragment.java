package com.janus.aprendiendoacontar.menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;

public class InicioFragment extends Fragment {

    private ImageButton btnJugar;
    private ImageView ivTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnJugar = view.findViewById(R.id.btnJugar);
        ivTitle = view.findViewById(R.id.ivTitle);

//        ivTitle.animate().alpha(1).setDuration(1000).setStartDelay(500);
//        btnJugar.animate().alpha(1).setDuration(1000).setStartDelay(500);

        UIAnimation.onInfiniteScale(requireContext(), btnJugar);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UIAnimation.onScaleZoomIn(requireContext(), btnJugar);
                ivTitle.animate().translationY(-1000).setDuration(600).setStartDelay(100);
                btnJugar.animate().translationY(-1000).setDuration(700).setStartDelay(200);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Navigation.findNavController(v).navigate(R.id.action_inicioFragment_to_menuFragment);
                    }
                }, 1600);

            }
        });

    }
}