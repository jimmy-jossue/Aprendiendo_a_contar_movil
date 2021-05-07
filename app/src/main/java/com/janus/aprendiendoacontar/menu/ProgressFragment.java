package com.janus.aprendiendoacontar.menu;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.janus.aprendiendoacontar.BaseFragment;
import com.janus.aprendiendoacontar.R;
import com.janus.aprendiendoacontar.Utilities.Consts;
import com.janus.aprendiendoacontar.Utilities.Observer;
import com.janus.aprendiendoacontar.db.Actividad;
import com.janus.aprendiendoacontar.db.ActividadDao;
import com.janus.aprendiendoacontar.db.DataBase;
import com.janus.aprendiendoacontar.dialogos.PerfilDialog;

public class ProgressFragment extends BaseFragment implements Observer, View.OnClickListener {

    private ImageView ivImage;
    private TextView tvNombre;
    private ImageButton btnEdit;
    private ImageButton btnBack;

    @Override
    public void initUI(View view) {
        ivImage = view.findViewById(R.id.ivProgreso_image);
        tvNombre = view.findViewById(R.id.tvProgreso_nombre);
        btnEdit = view.findViewById(R.id.btnProgreso_edit);
        btnEdit.setOnClickListener(this);
        btnBack = view.findViewById(R.id.btnAtrasProgreso);
        btnBack.setOnClickListener(this);
        mContext.agregarObserbador(this);
        setupDatos();
        setupTable(view);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_progress;
    }

    private void setupDatos() {

        if (getUsuario() != null) {
            ivImage.setImageResource(getUsuario().imagen);
            tvNombre.setText(getUsuario().nombre);
        }
    }

    private void showDialog(String accion) {
        DialogFragment dialog = new PerfilDialog(accion);
        dialog.show(getActivity().getSupportFragmentManager(), null);
    }


    @Override
    public void notifiar() {
        setupDatos();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == btnBack.getId()) {
            Navigation.findNavController(requireView()).navigate(R.id.action_progressFragment_to_menuFragment);
        } else if (id == btnEdit.getId()) {
            showDialog("Editar");

        }
    }

    private void setupTable(View view) {
        DataBase db = DataBase.getInstance(requireContext());
        ActividadDao dao = db.getActividad();
        Actividad actConoce = dao.buscarPorNombre(Consts.CONOCE);
        Actividad actCuantos = dao.buscarPorNombre(Consts.CUANTOS);
        Actividad actArrastra = dao.buscarPorNombre(Consts.ARRASTRA);
        Actividad actOrdena = dao.buscarPorNombre(Consts.ORDENA);

        TextView tvConoce_correctas = view.findViewById(R.id.tvConoce_correctas);
        TextView tvConoce_incorrectas = view.findViewById(R.id.tvConoce_incorrectas);
        TextView tvConoce_avance = view.findViewById(R.id.tvConoce_avance);

        tvConoce_correctas.setText(String.valueOf(actConoce.ejerciciosCorrectos));
        tvConoce_incorrectas.setText(String.valueOf(actConoce.ejerciciosIncorrectos));
        tvConoce_avance.setText((actConoce.ejerciciosCorrectos * 100) / 20 + "%");

        TextView tvCuantos_correctas = view.findViewById(R.id.tvCuantos_correctas);
        TextView tvCuantos_incorrectas = view.findViewById(R.id.tvCuantos_incorrectas);
        TextView tvCuantos_avance = view.findViewById(R.id.tvCuantos_avance);

//        tvCuantos_correctas.setText(String.valueOf(actCuantos.ejerciciosCorrectos));
//        tvCuantos_incorrectas.setText(String.valueOf(actCuantos.ejerciciosIncorrectos));
//        tvCuantos_avance.setText(String.valueOf((actCuantos.ejerciciosCorrectos * 100) / 20));
//
//        TextView tvArrastra_correctas = view.findViewById(R.id.tvArrastra_correctas);
//        TextView tvArrastra_incorrectas = view.findViewById(R.id.tvArrastra_incorrectas);
//        TextView tvArrastra_avance = view.findViewById(R.id.tvArrastra_avance);
//
//        tvArrastra_correctas.setText(String.valueOf(actArrastra.ejerciciosCorrectos));
//        tvArrastra_incorrectas.setText(String.valueOf(actArrastra.ejerciciosIncorrectos));
//        tvArrastra_avance.setText(String.valueOf((actArrastra.ejerciciosCorrectos * 100) / 20));
//
//        TextView tvOrdena_correctas = view.findViewById(R.id.tvOrdena_correctas);
//        TextView tvOrdena_incorrectas = view.findViewById(R.id.tvOrdena_incorrectas);
//        TextView tvOrdena_avance = view.findViewById(R.id.tvOrdena_avance);
//
//        tvOrdena_correctas.setText(String.valueOf(actOrdena.ejerciciosCorrectos));
//        tvOrdena_incorrectas.setText(String.valueOf(actOrdena.ejerciciosIncorrectos));
//        tvOrdena_avance.setText(String.valueOf((actOrdena.ejerciciosCorrectos * 100) / 20));

    }
}