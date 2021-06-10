package com.janus.aprendiendoacontar.juego;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.janus.aprendiendoacontar.BaseActivity;
import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Consts;
import com.janus.aprendiendoacontar.Utilities.Numeros;
import com.janus.aprendiendoacontar.Utilities.Recordar;
import com.janus.aprendiendoacontar.Utilities.Sound;
import com.janus.aprendiendoacontar.db.Actividad;
import com.janus.aprendiendoacontar.db.DataBase;
import com.janus.aprendiendoacontar.dialogos.FinActividadDialog;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ArrastraFragment extends BaseFragment implements Jugable, View.OnClickListener {

    Stack<Integer> cantidades;
    AbsoluteLayout lyContainerViews;
    AbsoluteLayout lyDestino;
    int indice, cantidadActual;
    int idImage;
    private final String TORTUGA = "tortuga";
    private ImageButton btnAtras;
    private ImageButton btnOk;
    private TextView tvCantidadEnCofre;
    int correctos = 0;
    int incorrectos = 0;
    private final String PULPO = "pulpo";
    private final String CANGREJO = "cangrejo";
    private final String CABALLITO = "caballito";
    private final String ESTRELLA = "estrella";
    private final String PEZ = "pez";
    //Evento soltar de las figuras
    private final View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            switch (dragEvent.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:
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

                    if (view.getId() == lyDestino.getId()) {
                        //Remover elemento de su conteneror original
                        ViewGroup viewGroup = (ViewGroup) v.getParent();
                        viewGroup.removeView(v);
                        //agregar elemento al destino
                        AbsoluteLayout destination = (AbsoluteLayout) view;
                        destination.addView(v);
                        v.setVisibility(View.VISIBLE);
                        if (lyDestino.getChildCount() > 0)
                            lyDestino.getChildAt(lyDestino.getChildCount() - 1).setVisibility(View.INVISIBLE);
                        //se reproduce el audio de las burbujas
                        new Sound(requireContext()).play(R.raw.bubble);
                        String cantidadEnCofre = String.valueOf(lyDestino.getChildCount());
                        //actualiza el numero del contador de figuras en el cofre
                        tvCantidadEnCofre.setText(cantidadEnCofre);

                        if (btnOk.getVisibility() == View.INVISIBLE)
                            btnOk.setVisibility(View.VISIBLE);
                    } else {
                        v.setVisibility(View.VISIBLE);
                    }
                    return true;
                default:
                    return false;
            }
        }
    };
    private ImageButton btnPregunta;
    private String animal = "";
    private List<Integer> tortugas = Arrays.asList(R.raw.arrastra_1_tortuga, R.raw.arrastra_2_tortugas, R.raw.arrastra_3_tortugas,
            R.raw.arrastra_4_tortugas, R.raw.arrastra_5_tortugas, R.raw.arrastra_6_tortugas, R.raw.arrastra_7_tortugas, R.raw.arrastra_8_tortugas
    );
    private List<Integer> cangrejos = Arrays.asList(R.raw.arrastra_1_cangrejo, R.raw.arrastra_2_cangrejos, R.raw.arrastra_3_cangrejos,
            R.raw.arrastra_4_cangrejos, R.raw.arrastra_5_cangrejos, R.raw.arrastra_6_cangrejos, R.raw.arrastra_7_cangrejos, R.raw.arrastra_8_cangrejos
    );
    private List<Integer> pulpos = Arrays.asList(R.raw.arrastra_1_pulpo, R.raw.arrastra_2_pulpos, R.raw.arrastra_3_pulpos,
            R.raw.arrastra_4_pulpos, R.raw.arrastra_5_pulpos, R.raw.arrastra_6_pulpos, R.raw.arrastra_7_pulpos, R.raw.arrastra_8_pulpos
    );
    private List<Integer> caballitos = Arrays.asList(R.raw.arrastra_9_caballitos, R.raw.arrastra_10_caballitos, R.raw.arrastra_11_caballitos,
            R.raw.arrastra_12_caballitos, R.raw.arrastra_13_caballitos, R.raw.arrastra_14_caballitos, R.raw.arrastra_15_caballitos
    );
    private List<Integer> estrellas = Arrays.asList(R.raw.arrastra_9_estrellas, R.raw.arrastra_10_estrellas, R.raw.arrastra_11_estrellas,
            R.raw.arrastra_12_estrellas, R.raw.arrastra_13_estrellas, R.raw.arrastra_14_estrellas, R.raw.arrastra_15_estrellas
    );
    private List<Integer> peces = Arrays.asList(R.raw.arrastra_9_peces, R.raw.arrastra_10_peces, R.raw.arrastra_11_peces,
            R.raw.arrastra_12_peces, R.raw.arrastra_13_peces, R.raw.arrastra_14_peces, R.raw.arrastra_15_peces, R.raw.arrastra_16_peces,
            R.raw.arrastra_17_peces, R.raw.arrastra_18_peces, R.raw.arrastra_19_peces, R.raw.arrastra_20_peces
    );

    @Override
    public void initUI(View view) {
        //se crea una lista con los numeros del 1 al 20 desordenados
        cantidades = Numeros.obtenerListaDesordenada(20);
        indice = 0;
        //Se obtiene el primer numero de la lista y se establece como el numero actual
        cantidadActual = cantidades.get(indice);
        StringBuilder texto = new StringBuilder();
        for (int i : cantidades) {
            texto.append(" - ").append(i);
        }

        //Se agregan los eventos a los elementos de la interfaz de usuario
        btnAtras = view.findViewById(R.id.btnAtrasArrastra);
        btnAtras.setOnClickListener(this);
        btnPregunta = view.findViewById(R.id.btnPreguntaArrastra);
        btnPregunta.setOnClickListener(this);
        btnOk = view.findViewById(R.id.btnOkArrastra);
        btnOk.setOnClickListener(this);

        tvCantidadEnCofre = view.findViewById(R.id.tvCantidadEnCofre);
        lyContainerViews = view.findViewById(R.id.lyContainerViews);
        lyDestino = view.findViewById(R.id.lyDestino);
        lyContainerViews.setOnDragListener(dragListener);
        lyDestino.setOnDragListener(dragListener);
        view.setOnDragListener(dragListener);

        //Despues de que se cree el contenedor se llama al metodo para dibujar las figuras
        lyContainerViews.post(this::DibujarFiguras);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_arrastra;
    }

    //Se dibuja una cantidad de figuras dependiendo de la cantidad actual
    private void DibujarFiguras() {
        int numerosDeMas = (int) Math.floor(Math.random() * 4 + 1);
        int numberOfImages = cantidadActual + numerosDeMas;

        int itemSize;

        //Se establese el tamaño de las figuras
        if (numberOfImages < 6) {
            itemSize = (int) getResources().getDimension(R.dimen.img_large_size);
        } else if (numberOfImages < 11) {
            itemSize = (int) getResources().getDimension(R.dimen.img_medium_size);
        } else {
            itemSize = (int) getResources().getDimension(R.dimen.img_small_size);
        }

        int widthContainer = lyContainerViews.getMeasuredWidth();
        int heightContainer = lyContainerViews.getMeasuredHeight();
        idImage = getImage();

        //Se establece una posicion aleatoria dentro de los limites del contenedor de figuras (lyContainerViews)
        for (int i = 1; i <= numberOfImages; i++) {
            int itemX = generaNumeroAleatorio(10, widthContainer - itemSize);
            int itemY = generaNumeroAleatorio(10, heightContainer - itemSize);
            View item = createItem(itemX, itemY, itemSize);
            lyContainerViews.addView(item);
        }
        new Sound(requireContext()).play(getAudio(animal));
    }

    //Metodo para crear una figura
    public ImageView createItem(int x, int y, int itemSize) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemSize, itemSize);
        ImageView ivItem = new ImageView(requireContext());

        ivItem.setImageResource(idImage);
        ivItem.setLayoutParams(params);
        ivItem.setAdjustViewBounds(true);
        ivItem.setX(x);
        ivItem.setY(y);

        //Se agrega el evento de arrastrar a la figura
        ivItem.setOnLongClickListener(view -> {
            String clipText = "";
            ClipData.Item item = new ClipData.Item(clipText);
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(clipText, mimeTypes, item);
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, dragShadowBuilder, view, View.DRAG_FLAG_OPAQUE);
            view.setVisibility(View.INVISIBLE);
            return true;
        });

        return ivItem;
    }

    //dependiendo de la caantidad actual se obtiene la imagen
    // que se le colocara a las figuras que se arrastraran
    public int getImage() {
        int idImage = 0;
        int random = 0;

        // obtiene una imagen aleatoria entre las imagenes de pulpo, tortuga y cangrejo
        if (cantidadActual >= 0 && cantidadActual < 9) {
            random = generaNumeroAleatorio(0, 3);
            switch (random) {
                case 0:
                    idImage = R.drawable.img_tortuga;
                    animal = TORTUGA;
                    break;
                case 1:
                    idImage = R.drawable.img_pulpo_a;
                    animal = PULPO;
                    break;
                case 2:
                    idImage = R.drawable.img_pulpo_m;
                    animal = PULPO;
                    break;
                case 3:
                    idImage = R.drawable.img_cangrejo;
                    animal = CANGREJO;
                    break;
            }

            // obtiene una imagen aleatoria entre las imagenes de caballitos de mar, estrellas y peces
        } else if (cantidadActual > 8 && cantidadActual < 16) {
            random = generaNumeroAleatorio(0, 4);
            switch (random) {
                case 0:
                    idImage = R.drawable.img_caballito;
                    animal = CABALLITO;
                    break;
                case 1:
                    idImage = R.drawable.img_estrella;
                    animal = ESTRELLA;
                    break;
                case 2:
                    idImage = R.drawable.img_pez_am;
                    animal = PEZ;
                    break;
                case 3:
                    idImage = R.drawable.img_pez_az;
                    animal = PEZ;
                    break;
                case 4:
                    idImage = R.drawable.img_pez_globo;
                    animal = PEZ;
                    break;
            }

            // obtiene una imagen aleatoria entre las imagenes de peces
        } else if (cantidadActual > 15 && cantidadActual <= 20) {
            random = generaNumeroAleatorio(0, 2);
            switch (random) {
                case 0:
                    idImage = R.drawable.img_pez_globo;
                    break;
                case 1:
                    idImage = R.drawable.img_pez_am;
                    break;
                case 2:
                    idImage = R.drawable.img_pez_az;
                    break;
            }
            animal = PEZ;
        }
        return idImage;
    }

    //Metodo para generar un numero aleatorio entre un rango (minimo y maximo)
    private int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

    //Se obtiene el audio dependiendo el animal de la figura
    private int getAudio(String animal) {
        int idAudio = 0;
        switch (animal) {
            case TORTUGA:
                idAudio = tortugas.get(cantidadActual - 1);
                break;
            case PULPO:
                idAudio = pulpos.get(cantidadActual - 1);
                break;
            case CANGREJO:
                idAudio = cangrejos.get(cantidadActual - 1);
                break;
            case CABALLITO:
                idAudio = caballitos.get(cantidadActual - 9);
                break;
            case ESTRELLA:
                idAudio = estrellas.get(cantidadActual - 9);
                break;
            case PEZ:
                idAudio = peces.get(cantidadActual - 9);
                break;
        }
        return idAudio;
    }

    //Evento click de los elementos de la interfaz de usuario
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == btnAtras.getId()) {
            finish(requireView(), correctos, incorrectos);

        } else if (id == btnPregunta.getId()) {
            new Sound(requireContext()).play(getAudio(animal));

        } else if (id == btnOk.getId()) {
            indice++;
            if (lyDestino.getChildCount() == cantidadActual) correcto();
            else incorrecto();

            //Si aun hay elementos en la lista de numeros aleatorios se vuelven a dibujar mas elementos en pantalla
            if (indice < cantidades.size()) {
                cantidadActual = cantidades.get(indice);
                limpiar();
                new Handler().postDelayed(
                        this::DibujarFiguras
                        , 800);
            } else {
                //Finaliza el ejercicio
                finish(requireView(), correctos, incorrectos);
            }
        }
    }

    private void limpiar() {    //quita los elementos del contenedor de figuras
        if (lyContainerViews.getChildCount() > 0) lyContainerViews.removeAllViews();
        if (lyDestino.getChildCount() > 0) lyDestino.removeAllViews();
        tvCantidadEnCofre.setText("0");
        btnOk.setVisibility(View.INVISIBLE);
    }

    @Override
    public void correcto() {
        Sound sound = new Sound(requireContext());
        sound.play(R.raw.correcto);
        correctos++;
    }

    @Override
    public void incorrecto() {
        Sound sound = new Sound(requireContext());
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
        act.nombre = Consts.ARRASTRA;
        db.getActividad().Insertar(act);
        Recordar.recordarActividad(requireContext(), Consts.KEY_ARRASTRA, correctos);

        showDialog(viewDestino, correctos, Consts.ARRASTRA);
    }

    //Muestra una ventana de dialogo al finalizar la actividad
    public void showDialog(View view, int correctos, String destino) {
        FinActividadDialog dialog = new FinActividadDialog(view, correctos, destino, 20);
        dialog.show(((BaseActivity) requireContext()).getSupportFragmentManager(), null);
    }
}