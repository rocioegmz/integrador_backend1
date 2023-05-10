package com.example.proyecto_integrador.security;

import com.example.proyecto_integrador.domain.Usuario;
import com.example.proyecto_integrador.domain.UsuarioRol;
import com.example.proyecto_integrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//Esta anotacion permite que Spring reconozca esta clase y exista
public class CargadoraDeDatos implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passACifrar ="1234";
        String passCifrada = cifrador.encode(passACifrar);
        Usuario usuarioAInsertar = new Usuario("Juana", "Lopez", "juana@mail.com",passCifrada, UsuarioRol.ROLE_USER );
        Usuario usuarioAInsertar2 = new Usuario("Juana", "Lopez", "juana@mail.com",passCifrada, UsuarioRol.ROLE_ADMIN );
        usuarioRepository.save(usuarioAInsertar2);
    }
}
