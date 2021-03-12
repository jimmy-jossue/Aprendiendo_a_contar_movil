package com.janus.aprendiendoacontar.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;

import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;


public class MenuFragment extends BaseFragment implements View.OnClickListener {

    SharedPreferences preferences;
    private ImageButton btnSalir;
    private ImageButton btnPerfil;
    private ImageButton btnConoce;
    private ImageButton btnCuantos;
    private ImageButton btnArrastra;
    private ImageButton btnOrdena;

    @Override
    public void initUI(View view) {
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

        preferences = getActivity().getSharedPreferences(getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
//        showDialog("EDITAR");
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_menu;
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
        Navigation.findNavController(getView()).navigate(idAccionDestido);
    }

//    btnPerfil.setBackgroundResource(getUsuario().imagen);

}