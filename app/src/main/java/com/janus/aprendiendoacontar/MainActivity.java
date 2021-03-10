package com.janus.aprendiendoacontar;

import android.content.SharedPreferences;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;

import com.janus.aprendiendoacontar.db.Usuario;
import com.janus.aprendiendoacontar.dialogos.PerfilDialog;

public class MainActivity extends BaseActivity {
    private SharedPreferences preferences;

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    @Override
    public void initUI() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        usuario = new Usuario();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showDialog(String accion) {
        DialogFragment dialog = new PerfilDialog(accion);
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onPositiveClick(PerfilDialog dialog) {
        usuario.id = dialog.getId();
        usuario.imagen = dialog.getImagenPerfilElegida();
        usuario.nombre = dialog.getNomUsuario();
    }
}