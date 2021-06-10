package com.janus.aprendiendoacontar.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.janus.aprendiendoacontar.BaseActivity;
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
    private String accion;

    public PerfilDialog(String accion) {
        this.accion = accion;
    }

    public String getNomUsuario() {
        return etNomUsuario.getText().toString();
    }

    public int getImagenPerfilElegida() {
        return IdImagen;
    }

    public String getAccion() {
        return accion;
    }

    //Eventos click de los botones del dialogo
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

    //crea el dialogo
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

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);
        builder.setView(view);

        etNomUsuario = view.findViewById(R.id.et_nom_usuario);
        imgPerfilElegida = view.findViewById(R.id.img_perfil_elegida);

        //se le agrega los eventos click a los botones
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

        //Si se abre el dialogo para crear un usuario no se podra cerrar hasta que se cree el usuario
        //si se abre el dialogo para editar un usuario, si se podra cerrar el dialogo
        if (!getAccion().equals("Editar")) {
            setCancelable(false);
        } else {
            BaseActivity context = (BaseActivity) getActivity();
            if (context != null) {
                //Se agregan los datos de usuario al Dialogo
                imgPerfilElegida.setImageResource(context.getUsuario().imagen);
                IdImagen = context.getUsuario().imagen;
                etNomUsuario.setText(context.getUsuario().nombre);
            }
        }

        return builder.create();
    }

    //se adjunta el dialogo a la actividad
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ActionDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(" must implement NoticeDialogListener");
        }
    }

    //Esta interfaz se tiene que implementar donde se quiera mostrar el dialogo
    // para obtener la respuesta de los botones
    public interface ActionDialogListener {
        void onPositiveClick(PerfilDialog dialog);
    }
}
