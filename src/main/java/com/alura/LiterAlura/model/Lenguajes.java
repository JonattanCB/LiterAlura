package com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Lenguajes {
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    GERMAN("de"),
    ITALIAN("it"),
    RUSSIAN("ru"),;

    private final String LenguajeOmdb;

    Lenguajes(String LenguajeOmdb) {
        this.LenguajeOmdb = LenguajeOmdb;
    }

    @JsonCreator
    public static Lenguajes fromString(String text) {
        for (Lenguajes l : Lenguajes.values()) {
            if (l.LenguajeOmdb.equalsIgnoreCase(text)){
                return l;
            }
        }
        throw  new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }
}
