package com.cyti.aprendiendoacontar.menu;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.cyti.aprendiendoacontar.BaseFragment;
import com.cyti.aprendiendoacontar.R;
import com.cyti.aprendiendoacontar.Utilities.Consts;
import com.cyti.aprendiendoacontar.Utilities.Observer;
import com.cyti.aprendiendoacontar.db.Actividad;
import com.cyti.aprendiendoacontar.db.ActividadDao;
import com.cyti.aprendiendoacontar.db.DataBase;
import com.cyti.aprendiendoacontar.dialogos.PerfilDialog;

public class ProgressFragment extends BaseFragment implements Observer, View.OnClickListener {

    private ImageView ivImage;
    private TextView tvNombre;
    private ImageButton btnEdit;
    private ImageButton btnBack;

    @Override
    public void initUI(View view) {
        //Se inician los elementos de la interfaz y se le agregan los eventos
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
        //Si, si hay usuarios en el objeto usuario
        // se colocan los datos en la interfaz
        if (getUsuario() != null) {
            ivImage.setImageResource(getUsuario().imagen);
            tvNombre.setText(getUsuario().nombre);
        }
    }

    //Muestra una ventana de dialogo para editar el usuario
    private void showDialog(String accion) {
        DialogFragment dialog = new PerfilDialog(accion);
        dialog.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void notifiar() {
        setupDatos();
    }

    //Evento click para regresar al menu o editar el usuario
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == btnBack.getId()) {
            Navigation.findNavController(requireView()).navigate(R.id.action_progressFragment_to_menuFragment);
        } else if (id == btnEdit.getId()) {
            showDialog("Editar");
        }
    }

    //Carga los datos de las actividades que a realizado el usuario en la tabla
    private void setupTable(View view) {
        DataBase db = DataBase.getInstance(requireContext());
        ActividadDao dao = db.getActividad();
        //Se obtienen los datos de las actividades en la base de datos
        Actividad actConoce = dao.buscarPorNombre(Consts.CONOCE);
        Actividad actCuantos = dao.buscarPorNombre(Consts.CUANTOS);
        Actividad actArrastra = dao.buscarPorNombre(Consts.ARRASTRA);
        Actividad actOrdena = dao.buscarPorNombre(Consts.ORDENA);

        TextView tvConoce_correctas = view.findViewById(R.id.tvConoce_correctas);
        TextView tvConoce_incorrectas = view.findViewById(R.id.tvConoce_incorrectas);
        TextView tvConoce_avance = view.findViewById(R.id.tvConoce_avance);

        //Se colocan los datos de la actividad "conoce los numeros"
        // y se calcula el porcentaje  del total
        try {
            tvConoce_correctas.setText(String.valueOf(actConoce.ejerciciosCorrectos));
            tvConoce_incorrectas.setText(String.valueOf(actConoce.ejerciciosIncorrectos));
            tvConoce_avance.setText((actConoce.ejerciciosCorrectos * 100) / 20 + "%");

        } catch (Exception ex) {
            tvConoce_correctas.setText("-");
            tvConoce_incorrectas.setText("-");
            tvConoce_avance.setText("0%");
        }


        TextView tvCuantos_correctas = view.findViewById(R.id.tvCuantos_correctas);
        TextView tvCuantos_incorrectas = view.findViewById(R.id.tvCuantos_incorrectas);
        TextView tvCuantos_avance = view.findViewById(R.id.tvCuantos_avance);

        //Se colocan los datos de la actividad "Cuantos hay"
        // y se calcula el porcentaje  del total
        try {
            tvCuantos_correctas.setText(String.valueOf(actCuantos.ejerciciosCorrectos));
            tvCuantos_incorrectas.setText(String.valueOf(actCuantos.ejerciciosIncorrectos));
            tvCuantos_avance.setText((actCuantos.ejerciciosCorrectos * 100) / 20 + "%");

        } catch (Exception ex) {
            tvCuantos_correctas.setText("-");
            tvCuantos_incorrectas.setText("-");
            tvCuantos_avance.setText("0%");
        }

        TextView tvArrastra_correctas = view.findViewById(R.id.tvArrastra_correctas);
        TextView tvArrastra_incorrectas = view.findViewById(R.id.tvArrastra_incorrectas);
        TextView tvArrastra_avance = view.findViewById(R.id.tvArrastra_avance);

        //Se colocan los datos de la actividad "Arrastra los elemmentos"
        // y se calcula el porcentaje  del total
        try {
            tvArrastra_correctas.setText(String.valueOf(actArrastra.ejerciciosCorrectos));
            tvArrastra_incorrectas.setText(String.valueOf(actArrastra.ejerciciosIncorrectos));
            tvArrastra_avance.setText((actArrastra.ejerciciosCorrectos * 100) / 20 + "%");

        } catch (Exception ex) {
            tvArrastra_correctas.setText("-");
            tvArrastra_incorrectas.setText("-");
            tvArrastra_avance.setText("0%");
        }

        TextView tvOrdena_correctas = view.findViewById(R.id.tvOrdena_correctas);
        TextView tvOrdena_incorrectas = view.findViewById(R.id.tvOrdena_incorrectas);
        TextView tvOrdena_avance = view.findViewById(R.id.tvOrdena_avance);

        //Se colocan los datos de la actividad "Ordena los elementos"
        // y se calcula el porcentaje  del total
        try {
            tvOrdena_correctas.setText(String.valueOf(actOrdena.ejerciciosCorrectos));
            tvOrdena_incorrectas.setText(String.valueOf(actOrdena.ejerciciosIncorrectos));
            tvOrdena_avance.setText((actOrdena.ejerciciosCorrectos * 100) / 20 + "%");

        } catch (Exception ex) {
            tvOrdena_correctas.setText("-");
            tvOrdena_incorrectas.setText("-");
            tvOrdena_avance.setText("0%");
        }
    }
}