package com.janus.aprendiendoacontar.juego;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Point;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.janus.aprendiendoacontar.BaseActivity;
import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Consts;
import com.janus.aprendiendoacontar.Utilities.Recordar;
import com.janus.aprendiendoacontar.Utilities.Sound;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;
import com.janus.aprendiendoacontar.db.Actividad;
import com.janus.aprendiendoacontar.db.DataBase;
import com.janus.aprendiendoacontar.dialogos.FinActividadDialog;

import java.util.Stack;

public class OrdenaFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener, Jugable {

    private Stack<Integer> cantidades = new Stack<Integer>();
    private ImageButton btnBack;
    private ImageButton btnOk;
    private TextView tvNumero1;
    private TextView tvNumero2;
    private TextView tvNumero3;
    private TextView tvNumero4;
    private TextView tvNumero5;
    private int cantidadActual;
    private int[] numeros;
    private Point[] posicionesBurbujas;
    private int contador = 0;
    private int correctos = 0;
    private int incorrectos = 0;
    private Sound sound;
    private boolean pocicionesGuardadas = false;

    private final View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            switch (dragEvent.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:
                    if (!pocicionesGuardadas) {     ////Se guarda la pocicion actual cada una de las burbujas
                        posicionesBurbujas[0] = new Point((int) tvNumero1.getX(), (int) tvNumero1.getY());
                        posicionesBurbujas[1] = new Point((int) tvNumero2.getX(), (int) tvNumero2.getY());
                        posicionesBurbujas[2] = new Point((int) tvNumero3.getX(), (int) tvNumero3.getY());
                        posicionesBurbujas[3] = new Point((int) tvNumero4.getX(), (int) tvNumero4.getY());
                        posicionesBurbujas[4] = new Point((int) tvNumero5.getX(), (int) tvNumero5.getY());
                        pocicionesGuardadas = true;
                    }
                    return dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
                case DragEvent.ACTION_DRAG_ENTERED:
                case DragEvent.ACTION_DRAG_EXITED:
                case DragEvent.ACTION_DRAG_ENDED:
                    view.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:

                    return true;
                case DragEvent.ACTION_DROP:
                    view.invalidate();
                    View v = (View) dragEvent.getLocalState();

                    //Entra si el "View" al que se suelta es alguna de las burbujas
                    if (view.getId() == tvNumero1.getId() || view.getId() == tvNumero2.getId() ||
                            view.getId() == tvNumero3.getId() || view.getId() == tvNumero4.getId() ||
                            view.getId() == tvNumero5.getId()) {

                        TextView viewArrastrado = (TextView) v;
                        TextView destination = (TextView) view;

                        int posicionArrastrado = 0;
                        int posicionDestino = 0;
                        int numeroArrastrado = Integer.parseInt(viewArrastrado.getText().toString());
                        int numeroDestino = Integer.parseInt(destination.getText().toString());

                        //busca la posicion en el arreglo de numeros, los valores de las burbujas que se cambian de posicion
                        for (int i = 0; i < numeros.length; i++) {
                            if (numeroArrastrado == numeros[i]) posicionArrastrado = i;
                            if (numeroDestino == numeros[i]) posicionDestino = i;
                        }

                        //Cambia la posicion en el arreglo, los valores de las burbujas que se cambian de posicion
                        numeros[posicionArrastrado] = numeroDestino;
                        numeros[posicionDestino] = numeroArrastrado;

                        //se llama al metodo para intercambiar la posicion de la burbujas en la interfaz grafica
                        cambiarPosicion(viewArrastrado, destination);
                    }
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    public void initUI(View view) {
        sound = new Sound(requireContext());
        numeros = new int[5];
        posicionesBurbujas = new Point[5];

        //Se inician los componentes de la interfaz y sus eventos
        btnBack = view.findViewById(R.id.btnAtrasOrdena);
        btnOk = view.findViewById(R.id.btnOkOrdena);

        tvNumero1 = view.findViewById(R.id.tvNumero1);
        tvNumero1.setOnDragListener(dragListener);
        tvNumero1.setOnLongClickListener(this);

        tvNumero2 = view.findViewById(R.id.tvNumero2);
        tvNumero2.setOnDragListener(dragListener);
        tvNumero2.setOnLongClickListener(this);

        tvNumero3 = view.findViewById(R.id.tvNumero3);
        tvNumero3.setOnDragListener(dragListener);
        tvNumero3.setOnLongClickListener(this);

        tvNumero4 = view.findViewById(R.id.tvNumero4);
        tvNumero4.setOnDragListener(dragListener);
        tvNumero4.setOnLongClickListener(this);

        tvNumero5 = view.findViewById(R.id.tvNumero5);
        tvNumero5.setOnDragListener(dragListener);
        tvNumero5.setOnLongClickListener(this);

        int cantidadTope = 15;

        //Se genera una lista de numeros del 1 al 20 ordenados aleatoriamente
        int cantidad = (int) Math.floor(Math.random() * cantidadTope + 1);
        for (int i = 0; i < cantidadTope; i++) {
            while (cantidades.contains(cantidad)) {
                cantidad = (int) Math.floor(Math.random() * (cantidadTope) + 1);
            }
            cantidades.push(cantidad);
        }

        //Se guarda la pocicion actual cada una de las burbujas
        posicionesBurbujas[0] = new Point((int) tvNumero1.getX(), (int) tvNumero1.getY());
        posicionesBurbujas[1] = new Point((int) tvNumero2.getX(), (int) tvNumero2.getY());
        posicionesBurbujas[2] = new Point((int) tvNumero3.getX(), (int) tvNumero3.getY());
        posicionesBurbujas[3] = new Point((int) tvNumero4.getX(), (int) tvNumero4.getY());
        posicionesBurbujas[4] = new Point((int) tvNumero5.getX(), (int) tvNumero5.getY());

        desordenarNumeros();
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        view.setOnDragListener(dragListener);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_ordena;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == btnBack.getId()) {
            finish(requireView(), correctos, incorrectos);
        } else if (id == btnOk.getId()) {

            if (!pocicionesGuardadas) {
                posicionesBurbujas[0] = new Point((int) tvNumero1.getX(), (int) tvNumero1.getY());
                posicionesBurbujas[1] = new Point((int) tvNumero2.getX(), (int) tvNumero2.getY());
                posicionesBurbujas[2] = new Point((int) tvNumero3.getX(), (int) tvNumero3.getY());
                posicionesBurbujas[3] = new Point((int) tvNumero4.getX(), (int) tvNumero4.getY());
                posicionesBurbujas[4] = new Point((int) tvNumero5.getX(), (int) tvNumero5.getY());
                pocicionesGuardadas = true;
            }

            boolean ordenados = true;
            for (int i = 0; i < numeros.length - 1; i++) {
                if (numeros[i] > numeros[i + 1]) {
                    ordenados = false;
                    break;
                }
            }

            if (ordenados) correcto();
            else incorrecto();

            if (contador < 14) new Handler().postDelayed(this::colocarNumeros, 500);
            else finish(requireView(), correctos, incorrectos);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        String clipText = "";
        ClipData.Item item = new ClipData.Item(clipText);
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(clipText, mimeTypes, item);
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
        v.startDragAndDrop(data, dragShadowBuilder, v, View.DRAG_FLAG_GLOBAL);
        return true;
    }

    // se generan 5 numeros apartir del numero actual
    // y se desordenan
    private void desordenarNumeros() {
        cantidadActual = cantidades.get(contador);
        Stack<Integer> posiciones = new Stack<Integer>();

        numeros[0] = cantidadActual;
        numeros[1] = cantidadActual + 1;
        numeros[2] = cantidadActual + 2;
        numeros[3] = cantidadActual + 3;
        numeros[4] = cantidadActual + 4;

        int cantidadTope = 5;
        int cantidad = (int) Math.floor(Math.random() * cantidadTope);
        for (int i = 0; i < cantidadTope; i++) {
            while (posiciones.contains(cantidad)) {
                cantidad = (int) Math.floor(Math.random() * (cantidadTope));
            }
            posiciones.push(cantidad);
        }

        tvNumero1.setText(String.valueOf(numeros[posiciones.get(0)]));
        tvNumero2.setText(String.valueOf(numeros[posiciones.get(1)]));
        tvNumero3.setText(String.valueOf(numeros[posiciones.get(2)]));
        tvNumero4.setText(String.valueOf(numeros[posiciones.get(3)]));
        tvNumero5.setText(String.valueOf(numeros[posiciones.get(4)]));

        numeros[0] = Integer.parseInt(tvNumero1.getText().toString());
        numeros[1] = Integer.parseInt(tvNumero2.getText().toString());
        numeros[2] = Integer.parseInt(tvNumero3.getText().toString());
        numeros[3] = Integer.parseInt(tvNumero4.getText().toString());
        numeros[4] = Integer.parseInt(tvNumero5.getText().toString());

        sound.play(R.raw.ordena);
        posiciones = null;
    }

    @Override
    public void correcto() {
        sound.play(R.raw.correcto);
        correctos++;
    }

    @Override
    public void incorrecto() {
        sound.play(R.raw.error);
        incorrectos++;
    }

    //Finaliza el ejercicio y se guardan los datos en la base de datos
    @Override
    public void finish(View viewDestino, int correctos, int incorrectos) {
        DataBase db = DataBase.getInstance(requireContext());
        Actividad act = new Actividad();
        act.ejerciciosCorrectos = correctos;
        act.ejerciciosIncorrectos = incorrectos;
        act.nombre = Consts.ORDENA;

        db.getActividad().Insertar(act);
        Recordar.recordarActividad(requireContext(), Consts.KEY_ORDENA, correctos);
        showDialog(viewDestino, correctos, Consts.ORDENA);
    }

    //Muestra una ventana de dialogo al finalizar la actividad
    public void showDialog(View view, int correctos, String destino) {
        FinActividadDialog dialog = new FinActividadDialog(view, correctos, destino, 15);
        dialog.show(((BaseActivity) requireContext()).getSupportFragmentManager(), null);
    }

    //se llama al metodo para intercambiar la posicion de la burbujas en la interfaz grafica
    private void cambiarPosicion(View view, View viewDestino) {
        btnOk.setEnabled(false);
        int duracion = 500;
        float desplazamiento = 0;
        view.bringToFront();
        viewDestino.bringToFront();

        //Toma dos elementos e intercambia sus pocisiones en X (de derecha a izquierda)
        desplazamiento = view.getX() - viewDestino.getX();
        view.animate().translationXBy(desplazamiento - (desplazamiento * 2)).setDuration(duracion).setStartDelay(0);
        viewDestino.animate().translationXBy(desplazamiento).setDuration(duracion).setStartDelay(0);
        new Handler().postDelayed(() -> btnOk.setEnabled(true), 510);
    }

    // se colocan las burbujas en su posicion antes de ser movidas
    // y se colocan los nuevos numeros ya desordenados
    private void colocarNumeros() {
        tvNumero1.setX(posicionesBurbujas[0].x);
        tvNumero1.setY(posicionesBurbujas[0].y);
        tvNumero2.setX(posicionesBurbujas[1].x);
        tvNumero2.setY(posicionesBurbujas[1].y);
        tvNumero3.setX(posicionesBurbujas[2].x);
        tvNumero3.setY(posicionesBurbujas[2].y);
        tvNumero4.setX(posicionesBurbujas[3].x);
        tvNumero4.setY(posicionesBurbujas[3].y);
        tvNumero5.setX(posicionesBurbujas[4].x);
        tvNumero5.setY(posicionesBurbujas[4].y);

        UIAnimation.onScaleZoomOut(requireContext(), tvNumero1);
        UIAnimation.onScaleZoomOut(requireContext(), tvNumero2);
        UIAnimation.onScaleZoomOut(requireContext(), tvNumero3);
        UIAnimation.onScaleZoomOut(requireContext(), tvNumero4);
        UIAnimation.onScaleZoomOut(requireContext(), tvNumero5);

        new Handler().postDelayed(() -> {
            contador++;
            desordenarNumeros();
        }, 100);

        UIAnimation.onScaleZoomIn(requireContext(), tvNumero1);
        UIAnimation.onScaleZoomIn(requireContext(), tvNumero2);
        UIAnimation.onScaleZoomIn(requireContext(), tvNumero3);
        UIAnimation.onScaleZoomIn(requireContext(), tvNumero4);
        UIAnimation.onScaleZoomIn(requireContext(), tvNumero5);
    }
}