package com.example.proyecto_integrador.service;

import com.example.proyecto_integrador.domain.Domicilio;
import com.example.proyecto_integrador.domain.Paciente;
import com.example.proyecto_integrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// permite que podamos inyectar objetos de nuestro proyecto
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Domicilio domicilio = new Domicilio("Libertador","123","Rosario","Santa Fe");
        Paciente pacienteAGuardar = new Paciente("Perez","Juan","12345", LocalDate.of(2023,03,29),
                domicilio,"juan@mail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPaciente(){
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
    public void buscarPacientes(){
        int size = pacienteService.buscarPacientes().size();
        assertEquals(1, size);
    }
    @Test
    @Order(4)
    public void actualizarPaciente(){
        Domicilio domicilio = new Domicilio("Libertador","123","Rosario","Santa Fe");
        Paciente paciente= new Paciente("Perez","Ana","12345", LocalDate.of(2023,03,29),
                domicilio,"ana@mail.com");
        Long id = 1L;
        if(pacienteService.buscarPaciente(id).isPresent()){
            assertNotNull(pacienteService.actualizarPaciente(paciente));
        }
    }
    @Test
    @Order(5)
    public void eliminarPaciente() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        pacienteService.eliminarPaciente(idAEliminar);

        assertTrue(pacienteService.buscarPaciente(idAEliminar).isEmpty());
    }
}