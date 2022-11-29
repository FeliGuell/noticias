package com.egg.noticias.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Imagen {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String mime;

    private String nombre;

    @OneToOne
    private Imagen imagen;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;


    public Imagen() {
    }

    public Imagen(String id, String mime, String nombre, Imagen imagen, byte[] contenido) {
        this.id = id;
        this.mime = mime;
        this.nombre = nombre;
        this.imagen = imagen;
        this.contenido = contenido;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

}
