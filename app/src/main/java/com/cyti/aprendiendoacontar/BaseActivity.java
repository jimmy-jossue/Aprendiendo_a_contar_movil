package com.cyti.aprendiendoacontar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cyti.aprendiendoacontar.Utilities.Observer;
import com.cyti.aprendiendoacontar.db.DataBase;
import com.cyti.aprendiendoacontar.db.Usuario;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class BaseActivity extends AppCompatActivity {

    //variables globales
    protected Context mContext;
    protected DataBase db;
    protected Usuario usuario;
    Set<Observer> observers;

    //imnplementamos metodos abstractos
    public abstract void initUI();

    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        observers = new LinkedHashSet<Observer>();
        initUI();
        initContext();
        initDB();
        usuario = new Usuario();
    }

    private void initContext() {
        mContext = this;
    }

    protected void initDB() {
        db = DataBase.getInstance(mContext);
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public void agregarObserbador(Observer observer) {
        this.observers.add(observer);
    }


    public void notificcarObservadores() {
        for (Observer observer : observers) {
            observer.notifiar();
        }
    }
}
