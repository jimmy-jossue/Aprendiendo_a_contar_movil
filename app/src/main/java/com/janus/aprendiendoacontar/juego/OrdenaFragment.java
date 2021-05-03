package com.janus.aprendiendoacontar.juego;

import android.content.ClipData;
import android.content.ClipDescription;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;

public class OrdenaFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {

    private ImageButton btnBack;
    private ImageButton btnOk;
    private TextView tvNumero1;
    private TextView tvNumero2;
    private TextView tvNumero3;
    private TextView tvNumero4;
    private TextView tvNumero5;

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

                    //Remover elemento de su conteneror original
                    TextView viewArratrado = (TextView) v;
                    String numero1 = viewArratrado.getText().toString();

                    //agregar elemento al destino
                    if (view.getId() == tvNumero1.getId() || view.getId() == tvNumero2.getId() ||
                            view.getId() == tvNumero3.getId() || view.getId() == tvNumero4.getId() ||
                            view.getId() == tvNumero5.getId()) {

                        TextView destination = (TextView) view;
                        String numero2 = destination.getText().toString();

                        viewArratrado.setText(numero2);
                        destination.setText(numero1);
                    }


                    v.setVisibility(View.VISIBLE);
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    public void initUI(View view) {
        btnBack = view.findViewById(R.id.btnAtrasOrdena);
        btnBack.setOnClickListener(this);
        btnOk = view.findViewById(R.id.btnOkOrdena);
        btnOk.setOnClickListener(this);

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
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_ordena;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == btnBack.getId()) {
            Navigation.findNavController(requireView()).navigate(R.id.action_ordenaFragment_to_menuFragment);
        } else if (id == btnOk.getId()) {

        }
    }

    @Override
    public boolean onLongClick(View v) {
        String clipText = "";
        ClipData.Item item = new ClipData.Item(clipText);
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(clipText, mimeTypes, item);
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
        v.startDragAndDrop(data, dragShadowBuilder, v, View.DRAG_FLAG_OPAQUE);
        v.setVisibility(View.INVISIBLE);
        return true;
    }


}