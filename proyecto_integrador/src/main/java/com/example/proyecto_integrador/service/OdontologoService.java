package com.example.proyecto_integrador.service;

import com.example.proyecto_integrador.domain.Odontologo;
import com.example.proyecto_integrador.domain.Paciente;
import com.example.proyecto_integrador.exceptions.ResourceNotFoundException;
import com.example.proyecto_integrador.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarOdontologos(){
        return odontologoRepository.findAll();
    }
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);
        if(odontologoBuscado.isPresent()){
            odontologoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Error. No existe el odontologo con id: "+id);
        }
    }
    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
}
