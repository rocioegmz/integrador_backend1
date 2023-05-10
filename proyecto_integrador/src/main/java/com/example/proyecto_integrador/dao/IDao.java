package com.example.proyecto_integrador.dao;

import java.util.List;

public interface IDao <T>{
    T guardar (T t);
    T buscar (Long id);
    void eliminar (Long id);
    T actualizar (T t);
    List<T> buscarTodos();
    T buscarXCriterioString(String criterio);
}
