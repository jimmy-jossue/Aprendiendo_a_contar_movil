package com.janus.aprendiendoacontar;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class Perfil {
    AppCompatActivity appCompatActivity;
    ImageButton btn_perfil, btn_perfil_uno, btn_perfil_dos, btn_perfil_tres, btn_perfil_cuatro;
    ImageView iv_perfil_elegido;

    public Perfil(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void ElegirPerfil() {
        btn_perfil_uno = appCompatActivity.findViewById(R.id.btn_perfil_one);
        btn_perfil_dos = appCompatActivity.findViewById(R.id.btn_perfil_two);
        btn_perfil_tres = appCompatActivity.findViewById(R.id.btn_perfil_three);
        btn_perfil_cuatro = appCompatActivity.findViewById(R.id.btn_perfil_four);
        iv_perfil_elegido = appCompatActivity.findViewById(R.id.img_perfil_elegida);

        btn_perfil_uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_perfil_elegido.setImageResource(R.drawable.ic_perfil_one);
            }
        });

        btn_perfil_dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_perfil_elegido.setImageResource(R.drawable.ic_perfil_two);
            }
        });

        btn_perfil_tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_perfil_elegido.setImageResource(R.drawable.ic_perfil_three);
            }
        });

        btn_perfil_cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_perfil_elegido.setImageResource(R.drawable.ic_perfil_four);
            }
        });


    }


}
