package com.janus.aprendiendoacontar.juego;

import android.annotation.SuppressLint;
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

public class ConoceFragment extends Fragment implements View.OnTouchListener {

    final String SIGUIENTE = "siguiente";
    private float firstTouchX;
    private ConoceNumeros conoce;
    private int numero = 1;
    final String ANTERIOR = "anterior";
    private ImageView ivCantidad;
    private Sound sound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conoce, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sound = new Sound(requireContext());
        ivCantidad = view.findViewById(R.id.ivCantidad);
        ImageButton btnAtras = view.findViewById(R.id.btnAtras);
        conoce = new ConoceNumeros(requireContext());

        //Si este fragment tiene argumentos se obtienen y dependiendo de este
        //se crea la vista de cierta forma
        if (getArguments() != null) {
            numero = ConoceFragmentArgs.fromBundle(getArguments()).getNumero();
            ivCantidad.setImageResource(conoce.obtenerImagen(numero));

            if (numero == 20) {
                ivCantidad.setEnabled(false);
                btnAtras.setEnabled(false);
                new Handler().postDelayed(
                        () -> conoce.finish(requireView(), numero, 0)
                        , 1500);
            }
        }
        new Handler().postDelayed(() -> {
            //Se agrega el evento de finalizar al boton atras
            btnAtras.setOnClickListener(y -> conoce.finish(requireView(), numero, 0));
            //Se reproduce el audio de la cantidad actual
            sound.playSonidoCantidad(numero);
            ivCantidad.setOnTouchListener(ConoceFragment.this);
        }, 300);
    }

    //El evento touch:
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouchX = event.getX();
                break;
            case MotionEvent.ACTION_UP:     //Si se desliza el dedo hacia la izquierda se muestra el numero sigiente
                if (firstTouchX > event.getX() + 150) { //MAYOR
                    numeroSiguiente();

                    //Si se desliza el dedo hacia la derecha se muestra el numero anterior
                } else if (firstTouchX < event.getX() - 150) { //MENOR
                    numeroAnterior();
                }
                break;
        }
        return true;
    }

    //Se llama al Metodo IrA con el numero anterior al numero actual
    @SuppressLint("ClickableViewAccessibility")
    private void numeroAnterior() {
        if (numero > 1) {
            ivCantidad.setOnTouchListener(null);
            numero--;
            new Handler().postDelayed(() -> irA(ANTERIOR), 350);
        }
    }

    //Se llama al Metodo IrA con el numero siguiente al numero actual
    @SuppressLint("ClickableViewAccessibility")
    private void numeroSiguiente() {
        if (numero < 20) {
            ivCantidad.setOnTouchListener(null);
            numero++;
            new Handler().postDelayed(() -> irA(SIGUIENTE), 350);
        }
    }

    //Vuelve a llamar a este fragment pero con otro numero como argumento
    private void irA(String accionAnterior) {
        ConoceFragmentDirections.ActionConoceFragmentSelf accion = ConoceFragmentDirections.actionConoceFragmentSelf();
        accion.setNumero(numero);
        accion.setAccionAnterior(accionAnterior);
        Navigation.findNavController(requireView()).navigate(accion);
    }
}