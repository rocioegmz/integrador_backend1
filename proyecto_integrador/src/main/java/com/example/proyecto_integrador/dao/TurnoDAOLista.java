package com.example.proyecto_integrador.dao;

import com.example.proyecto_integrador.domain.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDAOLista implements IDao<Turno>{

    private List<Turno> turnos=new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Long id) {
        //Turno turnoBuscado=null;
        for (Turno turnoEnRevision:turnos) {
            if (turnoEnRevision.getId()==id){
                return turnoEnRevision;
            }
        }
        return null;
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarXCriterioString(String criterio) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        Turno buscado=buscar(id);
        if (buscado!=null){
            turnos.remove(buscado);
        }
        else{
            System.out.println("estaría bueno un log aquí");
        }
    }

    @Override
    public Turno actualizar(Turno turno) {
        int indice=turnos.indexOf(turno);
        turnos.set(indice,turno);
        return buscar(turno.getId());
    }
}
