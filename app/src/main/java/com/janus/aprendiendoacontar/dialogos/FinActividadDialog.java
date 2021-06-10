package com.janus.aprendiendoacontar.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Sound;
import com.willy.ratingbar.ScaleRatingBar;

public class FinActividadDialog extends DialogFragment implements View.OnClickListener {

    private FinActividadDialog.ActionDialogListener listener;
    private ImageButton btnVolverMenu;
    private ImageButton btnReitentar;
    private TextView tvCorrectos;
    private TextView tvTotal;
    private int correctos = 0;
    private int total = 0;
    private View view;
    private String destino;

    public FinActividadDialog(View view, int correctos, String destino, int total) {
        this.correctos = correctos;
        this.view = view;
        this.destino = destino;
        this.total = total;
    }

    public View obtenerVistaDestino() {
        return view;
    }

    public String obtenerDestino() {
        return destino;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = createDialog();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    //Se crea la instancia de AlertDialog (la pantalla de dialogo)
    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        new Sound(requireContext()).play(R.raw.win);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fin_actividad, null);
        builder.setView(view);

        final ScaleRatingBar ratingBar = view.findViewById(R.id.rbStarts);
        ratingBar.setClickable(false);
        ratingBar.setRating(calcularPorcentaje());

        tvTotal = view.findViewById(R.id.tvTotal);
        tvTotal.setText(String.valueOf(total));

        tvCorrectos = view.findViewById(R.id.tvCorrectos);
        tvCorrectos.setText(String.valueOf(correctos));

        //Se agregan los eventos click a los botones
        btnVolverMenu = view.findViewById(R.id.btnVolverMenu);
        btnVolverMenu.setOnClickListener(this);

        btnReitentar = view.findViewById(R.id.btnReintentar);
        btnReitentar.setOnClickListener(this);

        setCancelable(false);
        return builder.create();
    }

    //se adjunta el dialogo a la actividad
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (FinActividadDialog.ActionDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(" must implement NoticeDialogListener");
        }
    }

    //Evento clik de los botones
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == btnVolverMenu.getId()) {
            listener.onVolverMenuClick(FinActividadDialog.this);
            dismiss();
        } else if (id == btnReitentar.getId()) {
            listener.onReintentarClick(FinActividadDialog.this);
            dismiss();
        }
    }

    private float calcularPorcentaje() {
        float porcentaje = (correctos * 100) / total;

        float porcionEstrellas = 0F;
        if (porcentaje == 100) porcionEstrellas = 3F;
        else if (porcentaje >= 80) porcionEstrellas = 2.5F;
        else if (porcentaje >= 65) porcionEstrellas = 2F;
        else if (porcentaje >= 50) porcionEstrellas = 1.5F;
        else if (porcentaje >= 30) porcionEstrellas = 1F;
        else if (porcentaje >= 10) porcionEstrellas = 0.5F;
        else porcionEstrellas = 0F;

        return porcionEstrellas;
    }

    //Esta interfaz se tiene que implementar donde se quiera mostrar el dialogo
    // para obtener la respuesta de los botones
    public interface ActionDialogListener {
        void onReintentarClick(FinActividadDialog dialog);

        void onVolverMenuClick(FinActividadDialog dialog);
    }
}