package com.janus.aprendiendoacontar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.janus.aprendiendoacontar.db.DataBase;
import com.janus.aprendiendoacontar.db.Usuario;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mContext;

    //imnplementamos metodos abstractos
    public abstract void initUI(View view);

    public abstract int getLayout();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        initUI(view);
        return view;
    }

    public DataBase getDB() {
        return mContext.db;
    }

    public Usuario getUsuario() {
        return mContext.usuario;
    }

    public void showDialog(String accion) {
        mContext.showDialog(accion);
    }
}
