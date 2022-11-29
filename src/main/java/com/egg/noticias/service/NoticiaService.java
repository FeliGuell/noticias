package com.egg.noticias.service;

import com.egg.noticias.entity.Imagen;
import com.egg.noticias.entity.Noticia;
import com.egg.noticias.entity.Periodista;
import com.egg.noticias.exception.MessageException;
import com.egg.noticias.repository.ImagenRepository;
import com.egg.noticias.repository.NoticiaRepository;
import com.egg.noticias.repository.PeriodistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private PeriodistaRepository periodistaRepository;
    @Autowired
    private ImagenService imagenService;

    @Transactional
    public void crearNoticia(Noticia noticia, MultipartFile archivo, String idPeriodista) throws Exception {

        validar(noticia, archivo);

        Optional<Periodista> respuestaPeriodista = periodistaRepository.findById(idPeriodista);

        Periodista periodista = new Periodista();

        if(respuestaPeriodista.isPresent()){
            periodista = respuestaPeriodista.get();
        }

        Imagen imagen = imagenService.guardar(archivo);

        noticia.setImagen(imagen);
        noticia.setCreador(periodista);
        noticia.setFechaPublicacion(new Date());

        noticiaRepository.save(noticia);
    }

    @Transactional
    public void modificarNoticia(Noticia noticia, MultipartFile archivo, String idPeriodista) throws Exception {

        validar(noticia, archivo);

        Optional<Noticia> respuestaNoticia = noticiaRepository.findById(noticia.getId());
        Optional<Periodista> respuestaPeriodista = periodistaRepository.findById(idPeriodista);

        Periodista periodista = new Periodista();

        Imagen imagen = imagenService.guardar(archivo);

        if(respuestaPeriodista.isPresent()){
            periodista = respuestaPeriodista.get();
        }

        if(respuestaNoticia.isPresent()){
            noticia.setImagen(imagen);
            noticia.setCreador(periodista);
        }

        noticiaRepository.save(noticia);
    }

    @Transactional(readOnly = true)
    public List<Noticia> listaNoticias(){
        List<Noticia> noticias = new ArrayList<>();
        noticias = noticiaRepository.findAll();
        return noticias;
    }

    public Optional<Noticia> getOne(String id){
        return noticiaRepository.findById(id);
    }

    private void validar(String titulo, String cuerpo, MultipartFile imagen) throws MessageException {
        if(titulo.isEmpty() || titulo==null){
            throw new MessageException("El título de la noticia no puede estar vacío");
        }
        if(cuerpo.isEmpty() || cuerpo==null){
            throw new MessageException("El cuerpo de la noticia no puede estar vacío");
        }
        if(imagen.isEmpty() || imagen==null){
            throw new MessageException("Debe cargar una imagen");
        }
    }

    private void validar(Noticia noticia, MultipartFile imagen) throws MessageException {
        if(noticia.getTitulo().isEmpty() || noticia==null){
            throw new MessageException("El título de la noticia no puede estar vacío");
        }
        if(noticia.getCuerpo().isEmpty() || noticia==null){
            throw new MessageException("El cuerpo de la noticia no puede estar vacío");
        }
        if(imagen.isEmpty() || imagen==null){
            throw new MessageException("Debe cargar una imagen");
        }
    }

}
