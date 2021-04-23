package com.janus.aprendiendoacontar.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;
import com.janus.aprendiendoacontar.db.Usuario;
import com.janus.aprendiendoacontar.db.UsuarioDao;


public class MenuFragment extends BaseFragment implements View.OnClickListener {

    SharedPreferences preferences;
    private ImageButton btnSalir;
    private ImageButton btnPerfil;
    private ImageButton btnConoce;
    private ImageButton btnCuantos;
    private ImageButton btnArrastra;
    private ImageButton btnOrdena;

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

        preferences = getActivity().getSharedPreferences(getString(R.string.key_preference_AC), Context.MODE_PRIVATE);
//        Usuario u = new Usuario();
//        u.nombre = "jason";
//        u.imagen = 123456;
//        rememberUser(u);

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

//    btnPerfil.setBackgroundResource(getUsuario().imagen);

    private void rememberUser(Usuario usuario) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idUsuario", String.valueOf(usuario.id));
        editor.putString("nombreUsuario", String.valueOf(usuario.nombre));
        editor.putString("imagenUsuario", String.valueOf(usuario.imagen));
        editor.apply();
    }


    private void comprobarSesion() {
        String usuarioId = preferences.getString("idUsuario", "-1");
        if (!usuarioId.equals("-1") || usuarioId.isEmpty()) {
            UsuarioDao usuarioDao = getDB().getUsuario();
            Usuario usuario = usuarioDao.obtenerporId(Integer.parseInt(usuarioId));
            getUsuario().id = usuario.id;
            getUsuario().nombre = usuario.nombre;
            getUsuario().imagen = usuario.imagen;

            btnPerfil.setImageResource(getUsuario().imagen);
        } else {
            abrirDialog();
        }
    }

    private void abrirDialog() {
        Toast.makeText(requireContext(), "DIALOGO", Toast.LENGTH_SHORT).show();
    }
}