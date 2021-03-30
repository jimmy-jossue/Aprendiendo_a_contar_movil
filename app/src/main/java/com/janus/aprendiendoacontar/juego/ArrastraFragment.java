package com.janus.aprendiendoacontar.juego;

import android.content.ClipData;
import android.content.ClipDescription;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Numeros;

import java.util.Stack;

public class ArrastraFragment extends BaseFragment implements View.OnClickListener {

    Stack<Integer> cantidades;
    AbsoluteLayout lyContainerViews;
    FrameLayout lyDestino;
    int indice, cantidadActual;
    int idImage;
    private ImageButton btnAtras;
    private ImageButton btnOk;
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
                    ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                    view.invalidate();
                    View v = (View) dragEvent.getLocalState();
                    //Remover elemento de su conteneror original
                    ViewGroup viewGroup = (ViewGroup) v.getParent();
                    viewGroup.removeView(v);
                    //agregar elemento al destino
                    FrameLayout destination = (FrameLayout) view;
                    destination.addView(v);
                    v.setVisibility(View.INVISIBLE);


                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    public void initUI(View view) {
        cantidades = Numeros.getUnorderedList();
        indice = 0;
        cantidadActual = cantidades.get(indice);

        btnAtras = view.findViewById(R.id.btnAtrasArrastra);
        btnAtras.setOnClickListener(this);
        btnOk = view.findViewById(R.id.btnOkArrastra);
        btnOk.setOnClickListener(this);

        lyContainerViews = view.findViewById(R.id.lyContainerViews);
        lyDestino = view.findViewById(R.id.lyDestino);
        lyContainerViews.setOnDragListener(dragListener);
        lyDestino.setOnDragListener(dragListener);

        paintPictures(cantidadActual);


    }

    @Override
    public int getLayout() {
        return R.layout.fragment_arrastra;
    }

    private void paintPictures(int numberOfImages) {
        if (lyContainerViews.getChildCount() > 0)
            lyContainerViews.removeAllViews();

        DisplayMetrics metrics = requireContext().getResources().getDisplayMetrics();
        int itemSize = metrics.widthPixels / (numberOfImages);
        int widthContainer = metrics.widthPixels - (itemSize + 25);

        idImage = getImage((int) Math.floor(Math.random() * 8));

        for (int i = 1; i <= numberOfImages; i++) {

            itemSize = Math.min(itemSize, 350);

            int itemX = (int) (Math.random() * (widthContainer - 25) + 25);
            int itemY = (int) (Math.random() * (widthContainer - 25) + 25);

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

        ivItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String clipText = "";
                ClipData.Item item = new ClipData.Item(clipText);
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData data = new ClipData(clipText, mimeTypes, item);
                View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(data, dragShadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
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
            Navigation.findNavController(requireView()).navigate(R.id.action_arrastraFragment_to_menuFragment);

        } else if (id == btnOk.getId()) {
            if (lyDestino.getChildCount() == cantidadActual) {

            }
        }


    }



}