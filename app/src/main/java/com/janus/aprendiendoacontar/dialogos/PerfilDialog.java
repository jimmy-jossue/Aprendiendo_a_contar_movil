package com.janus.aprendiendoacontar.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.janus.aprendiendoacontar.R;

public class PerfilDialog extends DialogFragment implements View.OnClickListener {

    ActionDialogListener listener;
    private EditText etNomUsuario;
    private ImageButton btnList;
    private ImageView imgPerfilElegida;
    private ImageView btnPerfilOne;
    private ImageView btnPerfilTwo;
    private ImageView btnPerfilThree;
    private ImageView btnPerfilFour;
    private int IdImagen = 0;

    public String getNomUsuario() {
        return etNomUsuario.getText().toString();
    }

    public int getImagenPerfilElegida() {
        return IdImagen;
    }

    @Override
    public void onClick(View view) {
        ImageView img = (ImageView) view;
        imgPerfilElegida.setImageDrawable(img.getDrawable());
        if (img.getId() == R.id.btn_perfil_one) {
            IdImagen = R.drawable.ic_perfil_one;

        } else if (img.getId() == R.id.btn_perfil_two) {
            IdImagen = R.drawable.ic_perfil_two;

        } else if (img.getId() == R.id.btn_perfil_three) {
            IdImagen = R.drawable.ic_perfil_three;

        } else if (img.getId() == R.id.btn_perfil_four) {
            IdImagen = R.drawable.ic_perfil_four;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return createDialog();
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);
        builder.setView(view);

        etNomUsuario = view.findViewById(R.id.et_nom_usuario);
        imgPerfilElegida = view.findViewById(R.id.img_perfil_elegida);

        btnPerfilOne = view.findViewById(R.id.btn_perfil_one);
        btnPerfilOne.setOnClickListener(this);

        btnPerfilTwo = view.findViewById(R.id.btn_perfil_two);
        btnPerfilTwo.setOnClickListener(this);

        btnPerfilThree = view.findViewById(R.id.btn_perfil_three);
        btnPerfilThree.setOnClickListener(this);

        btnPerfilFour = view.findViewById(R.id.btn_perfil_four);
        btnPerfilFour.setOnClickListener(this);

        btnList = view.findViewById(R.id.btn_list);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNomUsuario.getText().toString().isEmpty() && IdImagen != 0) {
                    listener.onPositiveClick(PerfilDialog.this);
                    dismiss();
                }
            }
        });

        setCancelable(false);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ActionDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(" must implement NoticeDialogListener");
        }
    }

    public interface ActionDialogListener {
        public void onPositiveClick(PerfilDialog dialog);
    }
}
