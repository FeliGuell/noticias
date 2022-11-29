package com.egg.noticias.controller;


import com.egg.noticias.entity.Noticia;
import com.egg.noticias.entity.Periodista;
import com.egg.noticias.entity.Usuario;
import com.egg.noticias.exception.MessageException;
import com.egg.noticias.service.PeriodistaService;
import com.egg.noticias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PeriodistaService periodistaService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/usuarios")
    public String listar(ModelMap model){
        List<Usuario> usuarios = usuarioService.listaUsuarios();
        model.addAttribute("usuarios", usuarios);

        return "usuario_list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/periodistas")
    public String listarPeriodistas(ModelMap model){
        List<Periodista> periodistas = usuarioService.listaPeriodistas();
        model.addAttribute("periodistas", periodistas);
        return "periodista_list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id, RedirectAttributes flash){
        try{
            usuarioService.cambiarRol(id);
            flash.addFlashAttribute("success", "El usuario con id="+id+" ha sido modificado correctamente!");
        } catch (Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificarEstado/{id}")
    public String cambiarEstado(@PathVariable String id, RedirectAttributes flash){
        try{
            usuarioService.cambiarEstado(id);
            flash.addFlashAttribute("success", "El usuario con id="+id+" ha sido modificado correctamente!");
        }catch (Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificarUsuario/{id}")
    public String modificarUsuarios(@PathVariable("id") String id, RedirectAttributes flash, Model model){
        try{
           Usuario usuario = usuarioService.getOne(id).get();

           model.addAttribute("usuario", usuario);

           flash.addFlashAttribute("success", "El usuario con id="+id+" ha sido modificado correctamente!");
           return "usuario_form";
        } catch (Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuario_list";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificarPeriodista/{id}")
    public String modificarPeriodista(@PathVariable("id") String id, RedirectAttributes flash, Model model){
        try{
            Periodista periodista = periodistaService.getOne(id).get();

            model.addAttribute("periodista", periodista);

            flash.addFlashAttribute("success", "El usuario con id="+id+" ha sido modificado correctamente!");
            return "periodista_form";
        } catch (Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
            return "redirect:/periodista_list";
        }
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario, String password2, RedirectAttributes flash){

        try{
            usuarioService.actualizar(usuario.getId(), usuario.getNombreUsuario(),usuario.getPassword(), password2);
            flash.addFlashAttribute("success", "Usuario modificado");
        }catch (Exception msg){
            flash.addFlashAttribute("error", msg.getMessage());
        }

        return "redirect:/admin/usuarios";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/guardarPeriodista")
    public String guardarPeriodista(Periodista periodista, String password2, RedirectAttributes flash){

        try{
            periodistaService.actualizar(periodista.getId(), periodista.getSueldoMensual(), periodista.getNombreUsuario() ,periodista.getPassword(), password2);
            flash.addFlashAttribute("success", "Usuario modificado");
        }catch (Exception msg){
            flash.addFlashAttribute("error", msg.getMessage());
        }

        return "redirect:/admin/periodistas";
    }




}
