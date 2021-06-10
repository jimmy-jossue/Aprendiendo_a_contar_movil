package com.cyti.aprendiendoacontar;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cyti.aprendiendoacontar.db.DataBase;
import com.cyti.aprendiendoacontar.db.Usuario;

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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (BaseActivity) context;
    }

    public DataBase getDB() {
        return mContext.db;
    }

    public Usuario getUsuario() {
        return mContext.usuario;
    }
}
