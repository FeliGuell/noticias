package com.egg.noticias.repository;

import com.egg.noticias.entity.Noticia;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface NoticiaRepository extends JpaRepository<Noticia, String> {

    List<Noticia> findByTituloContainingIgnoreCase(String keyword);

    @Query("UPDATE Noticia n SET n.publicado = :publicado WHERE n.id = :id")
    @Modifying
    public void updatePublishedStatus(String id, Boolean publicado);
}
