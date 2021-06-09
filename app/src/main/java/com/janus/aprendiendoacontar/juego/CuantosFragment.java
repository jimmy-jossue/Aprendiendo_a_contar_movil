package com.janus.aprendiendoacontar.juego;

import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Sound;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;

public class CuantosFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton btnAtras;
    private CuantosHay cuantos;
    private ImageView ivCantidad;
    private TextView tvOpcion1;
    private TextView tvOpcion2;
    private TextView tvOpcion3;
    Sound sound;

    @Override
    public int getLayout() {
        return R.layout.fragment_cuantos;
    }

    @Override
    public void initUI(View view) {
        btnAtras = view.findViewById(R.id.btnAtras);
        ivCantidad = view.findViewById(R.id.ivCuantosCantidad);
        tvOpcion1 = view.findViewById(R.id.tvCantidadEnCofre);
        tvOpcion2 = view.findViewById(R.id.tvOpcion2);
        tvOpcion3 = view.findViewById(R.id.tvOpcion3);

        ivCantidad.setOnClickListener(this);
        tvOpcion1.setOnClickListener(this);
        tvOpcion2.setOnClickListener(this);
        tvOpcion3.setOnClickListener(this);
        btnAtras.setOnClickListener(this);

        sound = new Sound(requireContext());

        cuantos = new CuantosHay(requireContext());
        formularPregunta();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAtras) {
            cuantos.finish(requireView(), cuantos.intentosCorrectos, cuantos.intentosIncorrectos);
        } else if (id == R.id.tvCantidadEnCofre || id == R.id.tvOpcion2 || id == R.id.tvOpcion3) {
            Evaluarrespuesta(v);
        } else if (id == R.id.ivCuantosCantidad) {
            sound.playSonidoCantidad(cuantos.getCantidadActual());
        }
    }

    private void Evaluarrespuesta(View view) {
        TextView res = (TextView) view;
        String posibleRespuesta = res.getText().toString();

        if (Integer.parseInt(posibleRespuesta) == cuantos.getCantidadActual()) {
            cuantos.correcto();
        } else {
            cuantos.incorrecto();
        }

        tvOpcion1.setEnabled(false);
        tvOpcion2.setEnabled(false);
        tvOpcion3.setEnabled(false);
        cuantos.siguienteNumero();
        final Handler handler = new Handler();
        handler.postDelayed(
                () -> {
                    if (cuantos.getContador() < 20) {
                        formularPregunta();
                    } else {
                        cuantos.finish(requireView(), cuantos.intentosCorrectos, cuantos.intentosIncorrectos);
                    }
                }
                , 500);
    }


    private void formularPregunta() {
        tvOpcion1.setEnabled(true);
        tvOpcion2.setEnabled(true);
        tvOpcion3.setEnabled(true);
        UIAnimation.onScaleZoomIn(requireContext(), tvOpcion1);
        UIAnimation.onScaleZoomIn(requireContext(), tvOpcion2);
        UIAnimation.onScaleZoomIn(requireContext(), tvOpcion2);

        Animal animal = cuantos.obtenerAnimalitos();
        ivCantidad.setImageResource(animal.getImg());
        int cantidad = cuantos.getCantidadActual();

        cuantos.Preguntar(animal.getTipoAnimal());

        final Handler handler = new Handler();
        handler.postDelayed(
                () -> {
                    int minDistractor = 0;
                    int maxDistractor = 0;
                    int posRespuesta = (int) Math.floor(Math.random() * 3);
                    int distractor1;
                    int distractor2;

                    if (cantidad <= 2) {
                        minDistractor = cantidad;
                        maxDistractor = cantidad + 5;
                    } else if (cantidad <= 18) {
                        minDistractor = cantidad - 2;
                        maxDistractor = cantidad + 2;
                    } else {
                        minDistractor = cantidad - 5;
                        maxDistractor = cantidad;
                    }

                    do {
                        distractor1 = generaNumeroAleatorio(minDistractor, maxDistractor);
                    } while (distractor1 == cantidad);
                    do {
                        distractor2 = generaNumeroAleatorio(minDistractor, maxDistractor);

                    } while (distractor2 == cantidad || distractor2 == distractor1);


                    switch (posRespuesta) {
                        case 0:
                            tvOpcion1.setText(String.valueOf(cantidad));
                            tvOpcion2.setText(String.valueOf(distractor1));
                            tvOpcion3.setText(String.valueOf(distractor2));
                            break;
                        case 1:
                            tvOpcion1.setText(String.valueOf(distractor1));
                            tvOpcion2.setText(String.valueOf(cantidad));
                            tvOpcion3.setText(String.valueOf(distractor2));
                            break;
                        default:
                            tvOpcion1.setText(String.valueOf(distractor1));
                            tvOpcion2.setText(String.valueOf(distractor2));
                            tvOpcion3.setText(String.valueOf(cantidad));
                            break;
                    }
                }
                , 100);

        UIAnimation.onScaleZoomOut(requireContext(), ivCantidad);
        UIAnimation.onScaleZoomOut(requireContext(), tvOpcion1);
        UIAnimation.onScaleZoomOut(requireContext(), tvOpcion2);
        UIAnimation.onScaleZoomOut(requireContext(), tvOpcion3);
    }

    private int generaNumeroAleatorio(int minimo, int maximo) {

        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
}