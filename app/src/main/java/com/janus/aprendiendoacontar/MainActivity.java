package com.janus.aprendiendoacontar;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;
import android.widget.Toast;

import com.janus.aprendiendoacontar.db.Usuario;
import com.janus.aprendiendoacontar.db.UsuarioDao;
import com.janus.aprendiendoacontar.dialogos.PerfilDialog;

public class MainActivity extends BaseActivity implements PerfilDialog.ActionDialogListener {
    SharedPreferences preferences;


    @Override
    public void initUI() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        preferences = getSharedPreferences(getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onPositiveClick(PerfilDialog dialog) {
        if (dialog.getAccion().equals("guardar")) {

            Usuario usuario = new Usuario();
            usuario.nombre = dialog.getNomUsuario();
            usuario.imagen = dialog.getImagenPerfilElegida();

            guardarUsuario(usuario);
            notificcarObservadores();
        }
    }

    private void guardarUsuario(Usuario usuario) {
        UsuarioDao dao = db.getUsuario();
        long id = dao.Insertar(usuario);

        usuario.id = (int) id;
        super.usuario = usuario;
        Toast.makeText(mContext, getUsuario() + "\n" + getUsuario().nombre + "\n" + getUsuario().imagen, Toast.LENGTH_SHORT).show();
        recordarUsuario(super.getUsuario());
    }

    private void recordarUsuario(Usuario usuario) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idUsuario", String.valueOf(usuario.id));
        editor.putString("nombreUsuario", String.valueOf(usuario.nombre));
        editor.putString("imagenUsuario", String.valueOf(usuario.imagen));
        editor.apply();
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
//    }
}