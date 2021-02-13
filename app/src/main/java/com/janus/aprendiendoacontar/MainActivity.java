package com.janus.aprendiendoacontar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    private boolean registrado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (registrado) {
            //no mostrar el alertDialog
        } else {
            showDialog();
        }
    }

    private void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        alert.setView(inflater.inflate(R.layout.dialog_login, null));
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        dialog.show();
    }
}