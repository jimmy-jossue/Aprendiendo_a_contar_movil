package com.janus.aprendiendoacontar.juego;

import android.os.Bundle;
import android.os.Handler;
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
import com.janus.aprendiendoacontar.Utilities.Sound;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;

public class ConoceFragment extends Fragment implements View.OnTouchListener {

    final String SIGUIENTE = "siguiente";
    private float firstTouchX;
    ConoceNumeros conoce = new ConoceNumeros();
    int numero = 1;
    final String ANTERIOR = "anterior";
    ImageView ivCantidad;
    private Sound sound;

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
        sound = new Sound(requireContext());
        ivCantidad = view.findViewById(R.id.ivCantidad);
        ImageButton btnAtras = view.findViewById(R.id.btnAtras);

        if (getArguments() != null) {
            numero = ConoceFragmentArgs.fromBundle(getArguments()).getNumero();
            String accionAnterior = ConoceFragmentArgs.fromBundle(getArguments()).getAccionAnterior();
            ivCantidad.setImageResource(conoce.obtenerImagen(numero));

            if (accionAnterior.equals(SIGUIENTE)) {
                UIAnimation.translateIn_LeftToRight(requireContext(), ivCantidad, R.anim.translate_in_left_to_right);
            } else {
                UIAnimation.translateIn_LeftToRight(requireContext(), ivCantidad, R.anim.translate_in_right_to_left);
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        btnAtras.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(requireView()).navigate(R.id.action_conoceFragment_to_menuFragment);
                            }
                        });

                        sound.playSonidoCantidad(numero);
                        ivCantidad.setOnTouchListener(ConoceFragment.this);
                    }
                }
                , 300);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouchX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (firstTouchX > event.getX() + 150) { //MAYOR
                    numeroSiguiente();

                } else if (firstTouchX < event.getX() - 150) { //MENOR
                    numeroAnterior();
                }
                break;
        }
        return true;
    }

    private void numeroAnterior() {
        if (numero > 1) {
            UIAnimation.translateIn_LeftToRight(requireContext(), ivCantidad, R.anim.translate_out_left_to_right);
            ivCantidad.setOnTouchListener(null);
            numero--;
            final Handler handler = new Handler();
            handler.postDelayed(() -> irA(ANTERIOR), 350);
        }
    }

    private void numeroSiguiente() {
        if (numero < 20) {
            UIAnimation.translateIn_LeftToRight(requireContext(), ivCantidad, R.anim.translate_out_right_to_left);
            ivCantidad.setOnTouchListener(null);
            numero++;
            final Handler handler = new Handler();
            handler.postDelayed(() -> irA(SIGUIENTE), 350);
        }
    }

    private void irA(String accionAnterior) {
        ConoceFragmentDirections.ActionConoceFragmentSelf accion = ConoceFragmentDirections.actionConoceFragmentSelf();
        accion.setNumero(numero);
        accion.setAccionAnterior(accionAnterior);
        Navigation.findNavController(requireView()).navigate(accion);
    }

}