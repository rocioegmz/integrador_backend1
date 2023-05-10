package com.example.proyecto_integrador.controller;

import com.example.proyecto_integrador.domain.Odontologo;
import com.example.proyecto_integrador.domain.Paciente;
import com.example.proyecto_integrador.domain.Turno;
import com.example.proyecto_integrador.dto.TurnoDTO;
import com.example.proyecto_integrador.exceptions.BadRequestException;
import com.example.proyecto_integrador.exceptions.GlobalExceptions;
import com.example.proyecto_integrador.service.OdontologoService;
import com.example.proyecto_integrador.service.PacienteService;
import com.example.proyecto_integrador.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static final Logger logger = Logger.getLogger(TurnoController.class);

    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }


    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        ResponseEntity<TurnoDTO> respuesta;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo_id());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTurnos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno con id: "+id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se logró eliminar " +
                    "el turno con id: "+id+". No existe en la BD");
        }
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turno){
        ResponseEntity<TurnoDTO> respuesta;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turno.getId());
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoService.actualizarTurno(turno));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> procesarErrorBR(BadRequestException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
