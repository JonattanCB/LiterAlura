package com.alura.LiterAlura.service;

public interface IConvierteDatos {
    <T> T convertirDatos(String json, Class<T> clase);
}
