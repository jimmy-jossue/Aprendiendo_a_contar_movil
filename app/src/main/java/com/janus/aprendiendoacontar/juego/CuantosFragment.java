package com.janus.aprendiendoacontar.juego;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;

public class CuantosFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton btnAtras;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initUI(View view) {
        btnAtras = view.findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_cuantos;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAtras) {

        }
    }
}