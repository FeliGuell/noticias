package com.egg.noticias.repository;

import com.egg.noticias.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombre")
    public Usuario buscarPorNombreUsuario(@Param("nombre")String nombre);

    @Modifying
    @Query("UPDATE Usuario u SET dtype = 'Periodista' WHERE u.id = :id")
    public void updateDtypePeriodista(@Param("id") String id);

    @Modifying
    @Query("UPDATE Usuario u SET dtype = 'Usuario' WHERE u.id = :id")
    public void updateDtypeUsuario(@Param("id") String id);

}
