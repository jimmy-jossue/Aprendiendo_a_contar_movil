package com.janus.aprendiendoacontar.juego;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_cuantos;
    }

    @Override
    public void initUI(View view) {
        btnAtras = view.findViewById(R.id.btnAtras);
        ivCantidad = view.findViewById(R.id.ivCuantosCantidad);
        tvOpcion1 = view.findViewById(R.id.tvOpcion1);
        tvOpcion2 = view.findViewById(R.id.tvOpcion2);
        tvOpcion3 = view.findViewById(R.id.tvOpcion3);

        tvOpcion1.setOnClickListener(this);
        tvOpcion2.setOnClickListener(this);
        tvOpcion3.setOnClickListener(this);
        btnAtras.setOnClickListener(this);

        cuantos = new CuantosHay(requireContext());
        formularPregunta();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAtras) {
            Navigation.findNavController(requireView()).navigate(R.id.action_cuantosFragment_to_menuFragment);

        } else if (id == R.id.tvOpcion1 || id == R.id.tvOpcion2 || id == R.id.tvOpcion3) {
            cuantos.siguienteNumero();
            if (cuantos.getContador() < 19) {
                formularPregunta();
            } else {
                Toast.makeText(requireContext(), "20 numeros - Mostrar el Modal ", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.ivCuantosCantidad) {
            Sound sound = new Sound(requireContext());
            sound.playSonidoCantidad(cuantos.getCantidadActual());
        }
    }

    private void formularPregunta() {
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
                    int posRespuesta = (int) Math.floor(Math.random() * 2);
                    int distractor1;
                    int distractor2;
                    do {
                        distractor1 = (int) Math.floor(Math.random() * 20 + 1);
                    } while (distractor1 == cantidad);
                    do {
                        distractor2 = (int) Math.floor(Math.random() * 20 + 1);
                    } while (distractor2 == cantidad && distractor2 != distractor1);


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
                    Toast.makeText(requireContext(), cantidad + "", Toast.LENGTH_SHORT).show();
                }
                , 100);

        UIAnimation.onScaleZoomOut(requireContext(), ivCantidad);
        UIAnimation.onScaleZoomOut(requireContext(), tvOpcion1);
        UIAnimation.onScaleZoomOut(requireContext(), tvOpcion2);
        UIAnimation.onScaleZoomOut(requireContext(), tvOpcion3);
    }
}