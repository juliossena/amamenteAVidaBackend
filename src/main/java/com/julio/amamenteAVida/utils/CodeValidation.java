package com.julio.amamenteAVida.utils;

import java.util.Random;

public class CodeValidation {

    private static Random rand = new Random();

    public static String newPassword(final Integer size) {
        final char[] vet = new char[size];
        for (int i = 0; i < size; i++) {
            vet[i] = randomInteger();
        }
        return new String(vet);
    }

    public static char randomInteger() {
        return (char) (rand.nextInt(10) + 48);
    }

}
