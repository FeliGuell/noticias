package com.egg.noticias.service;

import com.egg.noticias.entity.Periodista;
import com.egg.noticias.entity.Usuario;
import com.egg.noticias.exception.MessageException;
import com.egg.noticias.repository.PeriodistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodistaService {

    @Autowired
    private PeriodistaRepository periodistaRepository;


    @Transactional(readOnly = true)
    public List<Periodista> lista(){
        List<Periodista> periodistas = new ArrayList<>();
        periodistas = periodistaRepository.findAll();
        return periodistas;
    }

    @Transactional(readOnly = true)
    public Optional<Periodista> getOne(String id){
        return periodistaRepository.findById(id);
    }

    @Transactional
    public void actualizar(String idUsuario,Integer sueldoMensual, String nombreUsuario, String password, String password2) throws Exception{

        validar(nombreUsuario,password, password2);

        Optional<Periodista> respuesta = getOne(idUsuario);

        if(respuesta.isPresent()){
            Periodista periodista = respuesta.get();

            periodista.setNombreUsuario(nombreUsuario);
            periodista.setPassword(new BCryptPasswordEncoder().encode(password));
            periodista.setActivo(Boolean.TRUE);
            periodista.setSueldoMensual(sueldoMensual);
            periodistaRepository.save(periodista);
        }

    }


    private void validar(String nombreUsuario,String password, String password2) throws MessageException {
        if(nombreUsuario.isEmpty() || nombreUsuario==null){
            throw new MessageException("El nombre no puede ser nulo o estar vacío");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MessageException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MessageException("Las contraseñas ingresadas deben ser iguales");
        }

    }


}
