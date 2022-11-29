package com.egg.noticias.controller;

import com.egg.noticias.entity.Noticia;
import com.egg.noticias.entity.Periodista;
import com.egg.noticias.repository.NoticiaRepository;
import com.egg.noticias.service.NoticiaService;
import com.egg.noticias.service.PeriodistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class NoticiaController {

    @Autowired
    private NoticiaRepository noticiaRepository;
    @Autowired
    private NoticiaService noticiaService;

    @Autowired
    private PeriodistaService periodistaService;



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PERIODISTA', 'ROLE_USUARIO')")
    @GetMapping("/noticias")
    public String listar(Model model, @Param("keyword")String keyword){

        try{
            List<Noticia> noticias = new ArrayList<>();

            if(keyword==null){
                noticiaRepository.findAll().forEach(noticias::add);
            }else{
                noticiaRepository.findByTituloContainingIgnoreCase(keyword).forEach(noticias::add);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("noticias", noticias);
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
        }
        return "noticia_list.html";
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERIODISTA')")
    @GetMapping("/noticias/crear")
    public String crearNoticia(Model model){
        Noticia noticia = new Noticia();
        noticia.setPublicado(true);
        List<Periodista> periodistas = periodistaService.lista();

        model.addAttribute("periodistas", periodistas);
        model.addAttribute("noticia", noticia);
        model.addAttribute("tituloPagina", "Crear Nueva Noticia");

        return "noticia_form";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERIODISTA')")
    @PostMapping("/noticias/guardar")
    public String guardarNoticia(@RequestParam(name = "file", required = false)MultipartFile imagen, Noticia noticia, RedirectAttributes flash, String idPeriodista){

            try{
                noticiaService.crearNoticia(noticia, imagen, idPeriodista);
                flash.addFlashAttribute("success", "Noticia Creada!");
            }catch (Exception msg){
                flash.addFlashAttribute("error", msg.getMessage());
            }

        return "redirect:/noticias";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERIODISTA')")
    @GetMapping("/noticias/{id}")
    public String editarNoticia(@PathVariable("id") String id, Model model, RedirectAttributes flash){
        try{
            Noticia noticia = noticiaRepository.findById(id).get();
            List<Periodista> periodistas = periodistaService.lista();

            model.addAttribute("periodistas", periodistas);
            model.addAttribute("noticia", noticia);
            model.addAttribute("tituloPagina", "Editar Noticia (ID: " + id + ")");
            return "noticia_form";
        } catch(Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
            return "redirect:/noticia_list";
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
   @GetMapping("/noticias/eliminar/{id}")
    public String eliminarNoticia(@PathVariable("id") String id, Model model, RedirectAttributes flash){
        try{
            noticiaRepository.deleteById(id);
            flash.addFlashAttribute("success", "La noticia con id="+id+" ha sido eliminada correctamente!");
        }catch(Exception e){
            flash.addFlashAttribute("error", "La noticia con id="+id+" no pudo ser eliminada");
        }

       return "redirect:/noticias";
   }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERIODISTA')")
   @GetMapping("/noticias/{id}/publicada/{estado}")
   public String actualizarEstadoPublicadoNoticia(@PathVariable("id")String id, @PathVariable("estado")boolean publicado,
                                                  Model model, RedirectAttributes flash){
        try{
            noticiaRepository.updatePublishedStatus(id, publicado);

            String estado = publicado ? "publicada" : "deshabilitada";
            String mensaje = "La noticia id="+id+" ha sido " + estado;

            flash.addFlashAttribute("success", mensaje);
        }catch (Exception e){
            flash.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/noticias";
   }








}
