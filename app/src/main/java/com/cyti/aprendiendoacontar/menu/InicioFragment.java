package com.cyti.aprendiendoacontar.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.cyti.aprendiendoacontar.R;

public class InicioFragment extends Fragment {

    private ImageButton btnJugar;

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
        // Se coloca el evento click al voton de jugar
        btnJugar.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_inicioFragment_to_menuFragment));
    }
}