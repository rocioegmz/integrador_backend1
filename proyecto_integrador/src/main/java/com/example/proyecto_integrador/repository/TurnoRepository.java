package com.example.proyecto_integrador.repository;

import com.example.proyecto_integrador.domain.Paciente;
import com.example.proyecto_integrador.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
