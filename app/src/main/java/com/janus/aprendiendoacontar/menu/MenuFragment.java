package com.janus.aprendiendoacontar.menu;

import android.animation.AnimatorInflater;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;

import java.util.Objects;

public class MenuFragment extends Fragment implements View.OnClickListener{

    private ImageButton btnSalir;
    private ImageButton btnPerfil;
    private ImageButton btnConoce;
    private ImageButton btnCuantos;
    private ImageButton btnArrastra;
    private ImageButton btnOrdena;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSalir = view.findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);
        btnPerfil = view.findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(this);
        btnConoce = view.findViewById(R.id.btnConoce);
        btnConoce.setOnClickListener(this);
        btnCuantos = view.findViewById(R.id.btnCuantos);
        btnCuantos.setOnClickListener(this);
        btnArrastra = view.findViewById(R.id.btnArrastra);
        btnArrastra.setOnClickListener(this);
        btnOrdena = view.findViewById(R.id.btnOrdena);
        btnOrdena.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSalir) requireActivity().finish();

        else if (id == R.id.btnPerfil) {

        } else if (id == R.id.btnConoce) {
            irAFragment(v, R.id.action_menuFragment_to_conoceFragment);

        } else if (id == R.id.btnCuantos) {
            irAFragment(v, R.id.action_menuFragment_to_cuantosFragment);

        } else if (id == R.id.btnArrastra) {
            irAFragment(v, R.id.action_menuFragment_to_arrastraFragment);

        } else if (id == R.id.btnOrdena) {
            irAFragment(v, R.id.action_menuFragment_to_ordenaFragment);
        }
    }

    private void irAFragment(View view, int idAccionDestido) {
        UIAnimation.onScaleZoomIn(requireContext(), view);
        Navigation.findNavController(requireView()).navigate(idAccionDestido);
    }
}