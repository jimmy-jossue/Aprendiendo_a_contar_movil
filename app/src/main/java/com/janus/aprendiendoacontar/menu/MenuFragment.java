package com.janus.aprendiendoacontar.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Consts;
import com.janus.aprendiendoacontar.Utilities.Observer;
import com.janus.aprendiendoacontar.Utilities.UIAnimation;
import com.janus.aprendiendoacontar.db.DataBase;
import com.janus.aprendiendoacontar.db.Usuario;
import com.janus.aprendiendoacontar.db.UsuarioDao;
import com.janus.aprendiendoacontar.dialogos.PerfilDialog;


public class MenuFragment extends BaseFragment implements View.OnClickListener, Observer {

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
        btnCuantos.setEnabled(false);
        btnCuantos.setOnClickListener(this);
        btnArrastra = view.findViewById(R.id.btnArrastra);
        btnArrastra.setEnabled(false);
        btnArrastra.setOnClickListener(this);
        btnOrdena = view.findViewById(R.id.btnOrdena);
        btnOrdena.setEnabled(false);
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
        if (id == btnSalir.getId()) System.exit(0);
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
            setupMenu();
        } else {
            showDialog("guardar");
        }
    }

    public void showDialog(String accion) {
        DialogFragment dialog = new PerfilDialog(accion);
        dialog.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void notifiar() {
        if (getUsuario() != null) {
            btnPerfil.setImageResource(getUsuario().imagen);
        }
    }

    private void setupMenu() {
        int avancesConoce = preferences.getInt(Consts.KEY_CONOCE, 0);
        int avancesCuantos = preferences.getInt(Consts.KEY_CUANTOS, 0);
        int avancesArrastra = preferences.getInt(Consts.KEY_ARRASTRA, 0);

        if (avancesConoce == 20) {
            btnCuantos.setImageResource(R.drawable.btn_menu_cuantos);
            btnCuantos.setEnabled(true);

            if (avancesCuantos >= 10) {
                btnArrastra.setImageResource(R.drawable.btn_menu_arrastra);
                btnArrastra.setEnabled(true);

                if (avancesArrastra > 15) {
                    btnOrdena.setImageResource(R.drawable.btn_menu_ordena);
                    btnOrdena.setEnabled(true);
                }
            }
        }
    }

}