package com.janus.aprendiendoacontar;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;

import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.Utilities.Consts;
import com.janus.aprendiendoacontar.db.Usuario;
import com.janus.aprendiendoacontar.db.UsuarioDao;
import com.janus.aprendiendoacontar.dialogos.FinActividadDialog;
import com.janus.aprendiendoacontar.dialogos.PerfilDialog;
import com.janus.aprendiendoacontar.juego.ConoceFragmentDirections;

public class MainActivity extends BaseActivity implements PerfilDialog.ActionDialogListener, FinActividadDialog.ActionDialogListener {
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
        Usuario usuario = new Usuario();
        usuario.nombre = dialog.getNomUsuario();
        usuario.imagen = dialog.getImagenPerfilElegida();

        if (dialog.getAccion().equals("guardar")) {
            guardarUsuario(usuario);
        } else {
            usuario.id = getUsuario().id;
            editarUsuario(usuario);
        }
        notificcarObservadores();
    }

    private void guardarUsuario(Usuario usuario) {
        UsuarioDao dao = db.getUsuario();
        long id = dao.Insertar(usuario);

        usuario.id = (int) id;
        super.usuario = usuario;
        recordarUsuario(super.getUsuario());
    }

    private void editarUsuario(Usuario usuario) {
        UsuarioDao dao = db.getUsuario();
        dao.Editar(usuario);

        getUsuario().id = usuario.id;
        getUsuario().nombre = usuario.nombre;
        getUsuario().imagen = usuario.imagen;

        recordarUsuario(getUsuario());
    }

    private void recordarUsuario(Usuario usuario) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idUsuario", String.valueOf(usuario.id));
        editor.putString("nombreUsuario", String.valueOf(usuario.nombre));
        editor.putString("imagenUsuario", String.valueOf(usuario.imagen));
        editor.apply();
    }

    @Override
    public void onReintentarClick(FinActividadDialog dialog) {
        int idDestino = 0;
        String destino = dialog.obtenerDestino();

        if (destino.equals(Consts.CONOCE)) {
            ConoceFragmentDirections.ActionConoceFragmentSelf accion = ConoceFragmentDirections.actionConoceFragmentSelf();
            accion.setNumero(1);
            accion.setAccionAnterior("anterior");
            Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(accion);
        } else if (destino.equals(Consts.CUANTOS)) {
            idDestino = R.id.action_menuFragment_to_cuantosFragment;
        } else if (destino.equals(Consts.ARRASTRA))
            idDestino = R.id.action_menuFragment_to_arrastraFragment;
        else if (destino.equals(Consts.ORDENA))
            idDestino = R.id.action_menuFragment_to_ordenaFragment;

//        Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(idDestino);
    }

    @Override
    public void onVolverMenuClick(FinActividadDialog dialog) {
        Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(R.id.action_conoceFragment_to_menuFragment);
    }
}