package com.egg.noticias.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "noticias")
public class Noticia {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;

    @Column(length = 5000)
    private String cuerpo;

    @OneToOne
    private Imagen imagen;

    @Column
    private boolean publicado;

    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;

    @OneToOne
    private Periodista creador;

    public Noticia() {
    }


    public Noticia(String id, String titulo, String cuerpo, Imagen imagen, boolean publicado, Date fechaPublicacion, Periodista creador) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.imagen = imagen;
        this.publicado = publicado;
        this.fechaPublicacion = fechaPublicacion;
        this.creador = creador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }


    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Periodista getCreador() {
        return creador;
    }

    public void setCreador(Periodista creador) {
        this.creador = creador;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }
}



