package com.janus.aprendiendoacontar.juego;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.R;

public class ConoceFragment extends Fragment implements View.OnTouchListener {

    private float firstTouchX;
    ConoceNumeros conoce = new ConoceNumeros();
    int numero = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conoce, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivCantidad = view.findViewById(R.id.ivCantidad);
        ivCantidad.setOnTouchListener(this);

        ImageButton btnAtras = view.findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.action_conoceFragment_to_menuFragment);
            }
        });


        if (getArguments() != null) {
            numero = ConoceFragmentArgs.fromBundle(getArguments()).getNumero();
            ivCantidad.setImageResource(conoce.colocarImagen(numero));
        }

//        UIAnimation.translateIn_LeftToRight(requireContext(), ivCantidad);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouchX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (firstTouchX > event.getX()) { //MAYOR
                    numeroSiguiente();

                } else if (firstTouchX < event.getX()) { //MENOR
                    numeroAnterior();
                }
                break;
        }
        return true;
    }

    private void numeroAnterior() {
        if (numero > 1) {
//            numero--;
//            Navigation.findNavController(requireView()).popBackStack();
            ConoceFragmentDirections.ActionConoceFragmentSelf accion = ConoceFragmentDirections.actionConoceFragmentSelf();
            accion.setNumero(--numero);
            Navigation.findNavController(requireView()).navigate(accion);
        }
    }

    private void numeroSiguiente() {
        if (numero < 20) {
            ConoceFragmentDirections.ActionConoceFragmentSelf accion = ConoceFragmentDirections.actionConoceFragmentSelf();
            accion.setNumero(++numero);
            Navigation.findNavController(requireView()).navigate(accion);
        }
    }


}