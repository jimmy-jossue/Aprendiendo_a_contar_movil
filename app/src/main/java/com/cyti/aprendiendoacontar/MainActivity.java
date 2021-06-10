package com.cyti.aprendiendoacontar;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;

import androidx.navigation.Navigation;

import com.cyti.aprendiendoacontar.Utilities.Consts;
import com.cyti.aprendiendoacontar.db.Usuario;
import com.cyti.aprendiendoacontar.db.UsuarioDao;
import com.cyti.aprendiendoacontar.dialogos.FinActividadDialog;
import com.cyti.aprendiendoacontar.dialogos.PerfilDialog;
import com.cyti.aprendiendoacontar.juego.ConoceFragmentDirections;

public class MainActivity extends BaseActivity implements PerfilDialog.ActionDialogListener, FinActividadDialog.ActionDialogListener {

    SharedPreferences preferences;

    @Override
    public void initUI() {
        // Enlazamos la app a las preferencias compartidas de android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        preferences = getSharedPreferences(getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    //Evento que se desencadena cuando se presiona el boton "ok" de la pantalla de dialogo
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

    //Evento que se desencadena cuando se presiona el boton "reintentar" de la pantalla de dialogo
    @Override
    public void onReintentarClick(FinActividadDialog dialog) {
        String destino = dialog.obtenerDestino();

        switch (destino) {
            case Consts.CONOCE:
                ConoceFragmentDirections.ActionConoceFragmentSelf accion = ConoceFragmentDirections.actionConoceFragmentSelf();
                accion.setNumero(1);
                accion.setAccionAnterior("anterior");
                Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(accion);
                break;
            case Consts.CUANTOS:
                Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(R.id.action_cuantosFragment_to_menuFragment);

                Navigation
                        .findNavController(dialog.obtenerVistaDestino())
                        .navigate(R.id.action_menuFragment_to_cuantosFragment);
                break;
            case Consts.ARRASTRA:
                Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(R.id.action_arrastraFragment_to_menuFragment);

                Navigation
                        .findNavController(dialog.obtenerVistaDestino())
                        .navigate(R.id.action_menuFragment_to_arrastraFragment);
                break;
            case Consts.ORDENA:
                Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(R.id.action_ordenaFragment_to_menuFragment);

                Navigation
                        .findNavController(dialog.obtenerVistaDestino())
                        .navigate(R.id.action_menuFragment_to_ordenaFragment);
                break;
        }
    }

    //Evento que se desencadena cuando se presiona el boton "volver al menú" de la pantalla de dialogo
    @Override
    public void onVolverMenuClick(FinActividadDialog dialog) {
        int idDestino = 0;
        String destino = dialog.obtenerDestino();
        switch (destino) {
            case Consts.CONOCE:
                idDestino = R.id.action_conoceFragment_to_menuFragment;
                break;
            case Consts.CUANTOS:
                idDestino = R.id.action_cuantosFragment_to_menuFragment;
                break;
            case Consts.ARRASTRA:
                idDestino = R.id.action_arrastraFragment_to_menuFragment;
                break;
            case Consts.ORDENA:
                idDestino = R.id.action_ordenaFragment_to_menuFragment;
                break;
        }
        Navigation.findNavController(dialog.obtenerVistaDestino()).navigate(idDestino);
    }
}