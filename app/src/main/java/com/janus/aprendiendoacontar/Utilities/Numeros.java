package com.janus.aprendiendoacontar.Utilities;

import java.util.Stack;
//Obtiene numeros aleatorios
public class Numeros {

    public static Stack<Integer> obtenerListaDesordenada(int cantidadNumeros) {
        Stack<Integer> lista = new Stack<Integer>();

        int cantidad = (int) Math.floor(Math.random() * cantidadNumeros);
        for (int i = 0; i < cantidadNumeros; i++) {
            while (lista.contains(cantidad)) {
                cantidad = (int) Math.floor(Math.random() * cantidadNumeros + 1);
            }
            lista.push(cantidad);
        }
        return lista;
    }
}
