package com.cyti.aprendiendoacontar.juego;

import android.content.Context;
import android.view.View;

import com.cyti.aprendiendoacontar.BaseActivity;
import com.cyti.aprendiendoacontar.R;
import com.cyti.aprendiendoacontar.Utilities.Consts;
import com.cyti.aprendiendoacontar.Utilities.Recordar;
import com.cyti.aprendiendoacontar.Utilities.Sound;
import com.cyti.aprendiendoacontar.db.Actividad;
import com.cyti.aprendiendoacontar.db.DataBase;
import com.cyti.aprendiendoacontar.dialogos.FinActividadDialog;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CuantosHay implements Jugable {

    private Stack<Integer> cantidades = new Stack<Integer>();
    private Context context;
    private Sound sound;
    private Animal animal;
    int intentosCorrectos = 0;
    int intentosIncorrectos = 0;
    private int cantidadActual;
    private int contador = 0;

    //Lista de imagenes de las tortugas
    private List<Integer> tortugas = Arrays.asList(
            R.drawable.cuantos_1_tortuga,
            R.drawable.cuantos_2_tortuga,
            R.drawable.cuantos_3_tortuga,
            R.drawable.cuantos_4_tortuga,
            R.drawable.cuantos_5_tortuga,
            R.drawable.cuantos_6_tortuga,
            R.drawable.cuantos_7_tortuga,
            R.drawable.cuantos_8_tortuga
    );

    //Lista de imagenes de los cangrejos
    private List<Integer> cangrejos = Arrays.asList(
            R.drawable.cuantos_1_cangrejo,
            R.drawable.cuantos_2_cangrejo,
            R.drawable.cuantos_3_cangrejo,
            R.drawable.cuantos_4_cangrejo,
            R.drawable.cuantos_5_cangrejo,
            R.drawable.cuantos_6_cangrejo,
            R.drawable.cuantos_7_cangrejo,
            R.drawable.cuantos_8_cangrejo
    );

    //Lista de imagenes de los pulpos
    private List<Integer> pulposMorados = Arrays.asList(
            R.drawable.cuantos_1_pulpo_m,
            R.drawable.cuantos_2_pulpo_m,
            R.drawable.cuantos_3_pulpo_m,
            R.drawable.cuantos_4_pulpo_m,
            R.drawable.cuantos_5_pulpo_m,
            R.drawable.cuantos_6_pulpo_m,
            R.drawable.cuantos_7_pulpo_m,
            R.drawable.cuantos_8_pulpo_m
    );

    //Lista de imagenes de los pulpos
    private List<Integer> pulposAmarillos = Arrays.asList(
            R.drawable.cuantos_9_pulpo_a,
            R.drawable.cuantos_10_pulpo_a,
            R.drawable.cuantos_11_pulpo_a,
            R.drawable.cuantos_12_pulpo_a,
            R.drawable.cuantos_13_pulpo_a,
            R.drawable.cuantos_14_pulpo_a,
            R.drawable.cuantos_15_pulpo_a
    );

    //Lista de imagenes de los caballitos
    private List<Integer> caballitos = Arrays.asList(
            R.drawable.cuantos_9_caballitos,
            R.drawable.cuantos_10_caballitos,
            R.drawable.cuantos_11_caballitos,
            R.drawable.cuantos_12_caballitos,
            R.drawable.cuantos_13_caballitos,
            R.drawable.cuantos_14_caballitos,
            R.drawable.cuantos_15_caballitos
    );

    //Lista de imagenes de las estrellas
    private List<Integer> estrellas = Arrays.asList(
            R.drawable.cuantos_9_estrellas,
            R.drawable.cuantos_10_estrellas,
            R.drawable.cuantos_11_estrellas,
            R.drawable.cuantos_12_estrellas,
            R.drawable.cuantos_13_estrellas,
            R.drawable.cuantos_14_estrellas,
            R.drawable.cuantos_15_estrellas
    );

    //Lista de imagenes de los peces
    private List<Integer> pecesAm = Arrays.asList(
            R.drawable.cuantos_16_pez_am,
            R.drawable.cuantos_17_pez_am,
            R.drawable.cuantos_18_pez_am,
            R.drawable.cuantos_19_pez_am,
            R.drawable.cuantos_20_pez_am
    );

    //Lista de imagenes de los peces
    private List<Integer> pecesAz = Arrays.asList(
            R.drawable.cuantos_16_pez_az,
            R.drawable.cuantos_17_pez_az,
            R.drawable.cuantos_18_pez_az,
            R.drawable.cuantos_19_pez_az,
            R.drawable.cuantos_20_pez_az
    );

    //Lista de imagenes de los peces
    private List<Integer> pecesGlobo = Arrays.asList(
            R.drawable.cuantos_16_pez_globo,
            R.drawable.cuantos_17_pez_globo,
            R.drawable.cuantos_18_pez_globo,
            R.drawable.cuantos_19_pez_globo,
            R.drawable.cuantos_20_pez_globo
    );

    public CuantosHay(Context context) {
        this.context = context;
        sound = new Sound(this.context);
        animal = new Animal();

        int cantidadTope = 20;

        //Se genera una lista de numeros del 1 al 20 pero desordenados
        int cantidad = (int) Math.floor(Math.random() * cantidadTope + 1);
        for (int i = 0; i < cantidadTope; i++) {
            while (cantidades.contains(cantidad)) {
                cantidad = (int) Math.floor(Math.random() * (cantidadTope) + 1);
            }
            cantidades.push(cantidad);
        }
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public int getContador() {
        return contador;
    }

    public void siguienteNumero() {
        contador++;
    }

    //En este metodo se busca la imagen que se muestar en pantalla
    //dependiendo de la cantidad actual
    public Animal obtenerAnimalitos() {
        int img = 0;
        cantidadActual = cantidades.get(contador);

        if (cantidadActual >= 16)
            img = animalesChicos(cantidadActual - 16);
        else if (cantidadActual >= 9)
            img = animaleMedianos(cantidadActual - 9);
        else
            img = animalesGrandes(cantidadActual - 1);

        animal.setImg(img);
        return animal;
    }

    //busca y obtiene una imagen aleatoria entre las imagenes de pulpo, tortuga y cangrejo
    //dependiendo de la cantidad actual
    private int animalesGrandes(int cantidad) {
        int img = 0;

        int tipoAnimmal = (int) Math.floor(Math.random() * 3 + 1);

        switch (tipoAnimmal) {
            case 1:
                img = tortugas.get(cantidad);
                animal.setTipoAnimal(Animal.TORTUGA);
                break;
            case 2:
                img = cangrejos.get(cantidad);
                animal.setTipoAnimal(Animal.CANGREJO);
                break;
            case 3:
                img = pulposMorados.get(cantidad);
                animal.setTipoAnimal(Animal.PULPO);
                break;
        }

        return img;
    }

    //busca obtiene una imagen aleatoria entre las imagenes de pulpo, caballitos de mar y estrellas de mar
    //dependiendo de la cantidad actual
    private int animaleMedianos(int cantidad) {
        int img = 0;

        int tipoAnimmal = (int) Math.floor(Math.random() * 3 + 1);

        switch (tipoAnimmal) {
            case 1:
                img = pulposAmarillos.get(cantidad);
                animal.setTipoAnimal(Animal.PULPO);
                break;
            case 2:
                img = caballitos.get(cantidad);
                animal.setTipoAnimal(Animal.CABALLITO);
                break;
            case 3:
                img = estrellas.get(cantidad);
                animal.setTipoAnimal(Animal.ESTRELLA);
                break;
        }
        return img;
    }

    //busca obtiene una imagen aleatoria entre las imagenes de peces
    //dependiendo de la cantidad actual
    private int animalesChicos(int cantidad) {
        int img = 0;

        int tipoAnimmal = (int) Math.floor(Math.random() * 3 + 1);

        switch (tipoAnimmal) {
            case 1:
                img = pecesAm.get(cantidad);
                animal.setTipoAnimal(Animal.PEZ);
                break;
            case 2:
                img = pecesGlobo.get(cantidad);
                animal.setTipoAnimal(Animal.PEZ);
                break;
            case 3:
                img = pecesAz.get(cantidad);
                animal.setTipoAnimal(Animal.PEZ);
                break;
        }
        return img;
    }

    @Override
    public void correcto() {
        Sound snd = new Sound(context);
        snd.play(R.raw.correcto);
        intentosCorrectos++;
    }

    @Override
    public void incorrecto() {
        Sound snd = new Sound(context);
        snd.play(R.raw.error);
        intentosIncorrectos++;
    }

    //Finaliza el ejercicio y se guardan los datos en la base de datos
    @Override
    public void finish(View view, int correctos, int incorrectos) {
        DataBase db = DataBase.getInstance(context);
        Actividad act = new Actividad();
        act.ejerciciosCorrectos = correctos;
        act.ejerciciosIncorrectos = incorrectos;
        act.nombre = Consts.CUANTOS;
        db.getActividad().Insertar(act);
        Recordar.recordarActividad(context, Consts.KEY_CUANTOS, correctos);

        showDialog(view, correctos, Consts.CUANTOS);
    }

    //Muestra una ventana de dialogo al finalizar la actividad
    public void showDialog(View view, int correctos, String destino) {
        FinActividadDialog dialog = new FinActividadDialog(view, correctos, destino, 20);
        dialog.show(((BaseActivity) context).getSupportFragmentManager(), null);
    }

    //Reproduce el audio de la pregunta dependiendo del tipo de animal que se muestre
    public void Preguntar(String tipoAnimal) {
        int idSonido = 0;
        switch (tipoAnimal) {
            case Animal.TORTUGA:
                idSonido = R.raw.cuantos_tortugas;
                break;
            case Animal.CANGREJO:
                idSonido = R.raw.cuantos_cangrejos;
                break;
            case Animal.PULPO:
                idSonido = R.raw.cuantos_pulpos;
                break;
            case Animal.CABALLITO:
                idSonido = R.raw.cuantos_caballitos;
                break;
            case Animal.ESTRELLA:
                idSonido = R.raw.cuantos_estrellas;
                break;
            case Animal.PEZ:
                idSonido = R.raw.cuantos_peces;
                break;
        }
        sound.play(idSonido);
    }
}

class Animal {
    final static String TORTUGA = "tortuga";
    final static String CANGREJO = "cangrejo";
    final static String PULPO = "pulpo";
    final static String CABALLITO = "caballitos";
    final static String ESTRELLA = "estrella";
    final static String PEZ = "pez";
    private int img;
    private String tipoAnimal;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}