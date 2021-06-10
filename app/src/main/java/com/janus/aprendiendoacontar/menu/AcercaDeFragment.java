package com.janus.aprendiendoacontar.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.R;

public class AcercaDeFragment extends Fragment {

    private ImageButton btnAtrasAcercaDe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acerca_de, container, false);

        //Se coloca el Evento click a el boton atras para regresar al menu
        btnAtrasAcercaDe = view.findViewById(R.id.btnAtrasAcercaDe);
        btnAtrasAcercaDe.setOnClickListener(v ->
                Navigation.findNavController(requireView()).navigate(R.id.action_acercaDeFragment_to_menuFragment));

        return view;
    }
}