package com.egg.noticias.controller;


import com.egg.noticias.entity.Noticia;
import com.egg.noticias.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/noticia/{id}")
    public ResponseEntity<byte[]> imagenNoticia (@PathVariable("id") String id ){
        Optional<Noticia> respuestaNoticia = noticiaService.getOne(id);
        Noticia noticia = new Noticia();

        if(respuestaNoticia.isPresent()){
            noticia = respuestaNoticia.get();
            byte[] imagen = noticia.getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
        }
        return null;
    }

}
