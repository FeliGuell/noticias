package com.egg.noticias.entity;

import com.egg.noticias.enumeraciones.Rol;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Admin extends Usuario{
    public Admin() {
    }

    public Admin(String id, String nombreUsuario, String password, Date fechaDeAlta, Rol rol, Boolean activo) {
        super(id, nombreUsuario, password, fechaDeAlta, rol, activo);
    }
}
