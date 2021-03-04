package com.janus.aprendiendoacontar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.janus.aprendiendoacontar.db.DataBase;
import com.janus.aprendiendoacontar.db.Usuario;

public abstract class BaseActivity extends AppCompatActivity {

    //variables globales
    protected Context mContext;
    protected DataBase db;
    protected Usuario usuario;

    //imnplementamos metodos abstractos
    public abstract void initUI();

    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initUI();
        initContext();
        initDB();
        usuario = new Usuario();
    }

    private void initContext() {
        mContext = this;
    }

    protected void initDB() {
        db = DataBase.getInstance(this);
    }
}
