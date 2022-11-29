package com.egg.noticias.repository;

import com.egg.noticias.entity.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodistaRepository extends JpaRepository<Periodista, String> {
}
