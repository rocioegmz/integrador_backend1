package com.example.proyecto_integrador.controller;

import com.example.proyecto_integrador.domain.Paciente;
import com.example.proyecto_integrador.exceptions.ResourceNotFoundException;
import com.example.proyecto_integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarPacientes());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Paciente> getByDni(@RequestBody String email) {
        Optional<Paciente> pacienteBuscadoXEmail=pacienteService.buscarXEmail("email");
        if (pacienteBuscadoXEmail.isPresent()){
            return ResponseEntity.ok(pacienteBuscadoXEmail.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Se eliminó el paciente con id: "+id);
    }
    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
    }
}
