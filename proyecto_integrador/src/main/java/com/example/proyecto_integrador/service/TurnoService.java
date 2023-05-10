package com.example.proyecto_integrador.service;

import com.example.proyecto_integrador.domain.Odontologo;
import com.example.proyecto_integrador.domain.Paciente;
import com.example.proyecto_integrador.domain.Turno;
import com.example.proyecto_integrador.dto.TurnoDTO;
import com.example.proyecto_integrador.exceptions.BadRequestException;
import com.example.proyecto_integrador.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    public TurnoDTO guardarTurno(TurnoDTO turno) throws BadRequestException {
        if(turno.getPaciente_id() !=null && turno.getOdontologo_id() != null){
            return convertirTurnoATurnoDTO(turnoRepository.save(convertirTurnoDTOATurno(turno)));
        }
        else {
            throw new BadRequestException("Error. No se puede crear turno, no existe paciente/odontologo en la BD");
        }
    }
    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if(turnoBuscado.isPresent()) {
            return Optional.of(convertirTurnoATurnoDTO(turnoBuscado.get()));
        }
        else {
            return Optional.empty();
        }
    }

    public List<TurnoDTO> buscarTurnos(){
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for (Turno turno : turnos) {
            turnosDTO.add(convertirTurnoATurnoDTO(turno));
        }

        return turnosDTO;
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }


    public TurnoDTO actualizarTurno(TurnoDTO turno){
        return convertirTurnoATurnoDTO(turnoRepository.save(convertirTurnoDTOATurno(turno)));
    }

    private Turno convertirTurnoDTOATurno (TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());

        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombre_paciente());
        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());
        // vinculo los objetos
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        return turno;
    }

    private TurnoDTO convertirTurnoATurnoDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setId(turno.getId());
        turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoDTO.setPaciente_id(turno.getPaciente().getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());

        return turnoDTO;
    }


}
