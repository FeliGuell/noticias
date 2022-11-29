package com.egg.noticias.service;

import com.egg.noticias.entity.Imagen;
import com.egg.noticias.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public Imagen guardar(MultipartFile archivo) throws Exception{
        if(archivo!=null){
            try{
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepository.save(imagen);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen actualizar(MultipartFile archivo, String id) throws Exception{
        if(archivo!=null){
            try{
                Imagen imagen = new Imagen();

                if(id != null){
                    Optional<Imagen> respuesta = imagenRepository.findById(id);

                    if(respuesta.isPresent()){
                        imagen = respuesta.get();
                    }

                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepository.save(imagen);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
