package com.egg.noticias.controller;

import com.egg.noticias.entity.Usuario;
import com.egg.noticias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PortalController {


    @Autowired
    private UsuarioService usuarioService;

   @GetMapping("/")
    public String inicio(){
       return "inicio.html";
   }

   @GetMapping("/registrar")
    public String registrar(Model model){
       model.addAttribute("tituloPagina", "Registrarse");
       return "usuario_register.html";
   }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario,@RequestParam String password, String password2, ModelMap modelMap) {
        try{
            usuarioService.crearUsuario(nombreUsuario, password, password2);
            modelMap.put("exito", "Usuario registrado correctamente!");
            return "inicio.html";
        }catch (Exception ex){

            modelMap.put("error", ex.getMessage());
            modelMap.put("nombre", nombreUsuario);

            return "usuario_register.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if(error!=null){
            modelo.put("error", "Usuario o contraseña inválidos!");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_PERIODISTA','ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        return "inicio.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_PERIODISTA','ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        model.addAttribute("usuario", usuario);
        model.addAttribute("tituloPagina", "Modificar perfil");
        return "usuario_register.html";
    }
}
