package com.janus.aprendiendoacontar;

import android.content.SharedPreferences;
import android.view.WindowManager;

import com.janus.aprendiendoacontar.db.Usuario;

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
}