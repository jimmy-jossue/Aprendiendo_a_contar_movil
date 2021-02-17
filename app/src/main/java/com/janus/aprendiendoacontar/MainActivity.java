package com.janus.aprendiendoacontar;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.janus.aprendiendoacontar.dialogos.PerfilDialog;

public class MainActivity extends AppCompatActivity implements PerfilDialog.ActionDialogListener {
    private boolean registrado = false;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = new Usuario();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (registrado) {
            //no mostrar el alertDialog
        } else {
            showDialog();
        }
    }

    private void showDialog() {
        DialogFragment dialog = new PerfilDialog();
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onPositiveClick(PerfilDialog dialog) {
        Toast.makeText(getBaseContext(),
                dialog.getNomUsuario(),
                Toast.LENGTH_SHORT).show();
    }
}