package com.janus.aprendiendoacontar.Utilities;

import java.util.Stack;

public class Numeros {

    public static Stack<Integer> getUnorderedList() {
        Stack<Integer> cantidades = new Stack<Integer>();

        int cantidad = (int) Math.floor(Math.random() * 20);
        for (int i = 0; i < 20; i++) {
            while (cantidades.contains(cantidad)) {
                cantidad = (int) Math.floor(Math.random() * 20 + 1);
            }
            cantidades.push(cantidad);
        }

        return cantidades;
    }
}
