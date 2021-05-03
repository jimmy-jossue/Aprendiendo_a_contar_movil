package com.janus.aprendiendoacontar.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Observer;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;
import com.janus.aprendiendoacontar.db.DataBase;
import com.janus.aprendiendoacontar.db.Usuario;
import com.janus.aprendiendoacontar.db.UsuarioDao;
import com.janus.aprendiendoacontar.dialogos.PerfilDialog;


public class MenuFragment extends BaseFragment implements View.OnClickListener, Observer /*, PerfilDialog.ActionDialogListener*/ {

    SharedPreferences preferences;
    private ImageButton btnSalir;
    private ImageButton btnPerfil;
    private ImageButton btnConoce;
    private ImageButton btnCuantos;
    private ImageButton btnArrastra;
    private ImageButton btnOrdena;
    private DataBase db;

    @Override
    public void initUI(View view) {
        btnSalir = view.findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);
        btnPerfil = view.findViewById(R.id.btnPerfilMenu);
        btnPerfil.setOnClickListener(this);
        btnConoce = view.findViewById(R.id.btnConoce);
        btnConoce.setOnClickListener(this);
        btnCuantos = view.findViewById(R.id.btnCuantos);
        btnCuantos.setOnClickListener(this);
        btnArrastra = view.findViewById(R.id.btnArrastra);
        btnArrastra.setOnClickListener(this);
        btnOrdena = view.findViewById(R.id.btnOrdena);
        btnOrdena.setOnClickListener(this);

        preferences = requireActivity().getSharedPreferences(getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
        mContext.agregarObserbador(this);

        setUpDatabase();
        comprobarSesion();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_menu;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == btnSalir.getId()) requireActivity().finish();
        else if (id == btnPerfil.getId())
            irAFragment(v, R.id.action_menuFragment_to_progressFragment);
        else if (id == btnConoce.getId())
            irAFragment(v, R.id.action_menuFragment_to_conoceFragment);
        else if (id == btnCuantos.getId())
            irAFragment(v, R.id.action_menuFragment_to_cuantosFragment);
        else if (id == btnArrastra.getId())
            irAFragment(v, R.id.action_menuFragment_to_arrastraFragment);
        else if (id == btnOrdena.getId())
            irAFragment(v, R.id.action_menuFragment_to_ordenaFragment);
    }

    private void irAFragment(View view, int idAccionDestido) {
        UIAnimation.onScaleZoomIn(requireContext(), view);
        Navigation.findNavController(getView()).navigate(idAccionDestido);
    }

    private void setUpDatabase() {
        db = DataBase.getInstance(requireContext());
    }

    private void comprobarSesion() {
        String usuarioId = preferences.getString("idUsuario", "-1");
        if (!usuarioId.equals("-1")) {
            UsuarioDao usuarioDao = db.getUsuario();
            Usuario usuario = usuarioDao.obtenerporId(Integer.parseInt(usuarioId));
            getUsuario().id = usuario.id;
            getUsuario().nombre = usuario.nombre;
            getUsuario().imagen = usuario.imagen;

            btnPerfil.setImageResource(getUsuario().imagen);
        } else {
            showDialog("guardar");
//           if (getUsuario() != null){
////               btnPerfil.setImageResource(getUsuario().imagen);
//           }
        }
    }

//    private void guardarUsuario(Usuario usuario) {
//        UsuarioDao dao = db.getUsuario();
//        long id = dao.Insertar(usuario);
//
//        usuario.id = (int) id;
//        super.setUsuario(usuario);
//        Toast.makeText(requireContext(), getUsuario() + "\n" + getUsuario().nombre + "\n" + getUsuario().imagen, Toast.LENGTH_SHORT).show();
//        recordarUsuario(super.getUsuario());
//    }

//    private void recordarUsuario(Usuario usuario) {
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("idUsuario", String.valueOf(usuario.id));
//        editor.putString("nombreUsuario", String.valueOf(usuario.nombre));
//        editor.putString("imagenUsuario", String.valueOf(usuario.imagen));
//        editor.apply();
//    }


    public void showDialog(String accion) {
        DialogFragment dialog = new PerfilDialog(accion);
        dialog.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void notifiar() {
        Toast.makeText(requireContext(), "Metodo notificar del observer", Toast.LENGTH_SHORT).show();
        if (getUsuario() != null) {
            btnPerfil.setImageResource(getUsuario().imagen);
        }
    }

//    @Override
//    public void onPositiveClick(PerfilDialog dialog) {
//        Usuario usuario = new Usuario();
//        usuario.nombre = dialog.getNomUsuario();
//        usuario.imagen = dialog.getImagenPerfilElegida();
//        Toast.makeText(mContext, "Antes de guardar", Toast.LENGTH_LONG);
//
//        guardarUsuario(usuario);
//        Toast.makeText(mContext, "Guardar \n" + usuario.id
//                + "\n" + usuario.nombre
//                + "\n" + usuario.imagen, Toast.LENGTH_SHORT).show();
//
//        if (super.getUsuario()!=null){
//            btnPerfil.setImageResource(super.getUsuario().imagen);
//        }
//    }
}