package com.example.proyecto_integrador.service;

import com.example.proyecto_integrador.domain.Paciente;
import com.example.proyecto_integrador.exceptions.ResourceNotFoundException;
import com.example.proyecto_integrador.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){return pacienteRepository.findById(id);}
    public List<Paciente> buscarPacientes(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarXEmail(String email){
        return pacienteRepository.findByEmail(email);
    }
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Error. No existe el paciente con id: "+id);
        }
    }
    public Paciente actualizarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
}
