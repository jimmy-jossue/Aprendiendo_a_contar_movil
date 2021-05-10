package com.janus.aprendiendoacontar.juego;

import android.content.ClipData;
import android.content.ClipDescription;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.Stack;

public class ArrastraFragment extends BaseFragment implements Jugable, View.OnClickListener {

    Stack<Integer> cantidades;
    AbsoluteLayout lyContainerViews;
    AbsoluteLayout lyDestino;
    int indice, cantidadActual;
    int idImage;
    private ImageButton btnAtras;
    private ImageButton btnOk;
    private TextView tvCantidadEnCofre;
    int correctos = 0;
    int incorrectos = 0;

    private View.OnDragListener dragListener = new View.OnDragListener() {
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
                        new Sound(requireContext()).play(R.raw.bubble);
                        String cantidadEnCofre = String.valueOf(lyDestino.getChildCount());
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

    @Override
    public void initUI(View view) {
        cantidades = Numeros.obtenerListaDesordenada(20);
        indice = 0;
        cantidadActual = cantidades.get(indice);
        StringBuilder texto = new StringBuilder();
        for (int i : cantidades) {
            texto.append(" - ").append(i);
        }

        btnAtras = view.findViewById(R.id.btnAtrasArrastra);
        btnAtras.setOnClickListener(this);
        btnOk = view.findViewById(R.id.btnOkArrastra);
        btnOk.setOnClickListener(this);

        tvCantidadEnCofre = view.findViewById(R.id.tvCantidadEnCofre);
        lyContainerViews = view.findViewById(R.id.lyContainerViews);
        lyDestino = view.findViewById(R.id.lyDestino);
        lyContainerViews.setOnDragListener(dragListener);
        lyDestino.setOnDragListener(dragListener);
        view.setOnDragListener(dragListener);

        DibujarFiguras();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_arrastra;
    }

    private void DibujarFiguras() {
        Toast.makeText(requireContext(), cantidadActual + "", Toast.LENGTH_SHORT).show();
        int numerosDeMas = (int) Math.floor(Math.random() * 4 + 1);
        int numberOfImages = cantidadActual + numerosDeMas;

        DisplayMetrics metrics = requireContext().getResources().getDisplayMetrics();
        int itemSize;

        if (numberOfImages < 6) {
            itemSize = (int) getResources().getDimension(R.dimen.img_large_size);
        } else if (numberOfImages < 11) {
            itemSize = (int) getResources().getDimension(R.dimen.img_medium_size);
        } else {
            itemSize = (int) getResources().getDimension(R.dimen.img_small_size);
        }

        int widthContainer = metrics.widthPixels - (itemSize + 10);
        idImage = getImage((int) Math.floor(Math.random() * 8));

        for (int i = 1; i <= numberOfImages; i++) {
            int itemX = (int) (Math.random() * (widthContainer) + 10);
            int itemY = (int) (Math.random() * (((metrics.heightPixels / 3) * 2) - itemSize) + 10);
            View item = createItem(itemX, itemY, itemSize);
            lyContainerViews.addView(item);
        }
    }

    public ImageView createItem(int x, int y, int itemSize) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemSize, itemSize);
        ImageView ivItem = new ImageView(requireContext());

        ivItem.setImageResource(idImage);
        ivItem.setLayoutParams(params);
        ivItem.setAdjustViewBounds(true);
        ivItem.setX(x);
        ivItem.setY(y);

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

    public int getImage(int numero) {
        int idImage = 0;
        switch (numero) {
            case 0:
                idImage = R.drawable.img_tortuga;
                break;
            case 1:
                idImage = R.drawable.img_pulpo_a;
                break;
            case 2:
                idImage = R.drawable.img_pulpo_m;
                break;
            case 3:
                idImage = R.drawable.img_caballito;
                break;
            case 4:
                idImage = R.drawable.img_estrella;
                break;
            case 5:
                idImage = R.drawable.img_cangrejo;
                break;
            case 6:
                idImage = R.drawable.img_pez_am;
                break;
            case 7:
                idImage = R.drawable.img_pez_az;
                break;
            default:
                idImage = R.drawable.img_pez_globo;
                break;
        }
        return idImage;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == btnAtras.getId()) {
            finish(requireView(), correctos, incorrectos);

        } else if (id == btnOk.getId()) {
            indice++;

            if (lyDestino.getChildCount() == cantidadActual) correcto();
            else incorrecto();

            if (indice < cantidades.size()) {
                cantidadActual = cantidades.get(indice);
                limpiar();
                DibujarFiguras();
            } else {
                finish(requireView(), correctos, incorrectos);
            }

        }
    }

    private void limpiar() {
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

    public void showDialog(View view, int correctos, String destino) {
        FinActividadDialog dialog = new FinActividadDialog(view, correctos, destino);
        dialog.show(((BaseActivity) requireContext()).getSupportFragmentManager(), null);
    }
}