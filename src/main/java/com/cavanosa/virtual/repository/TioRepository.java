package com.cavanosa.virtual.repository;

import com.cavanosa.virtual.entity.Tio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TioRepository extends JpaRepository<Tio, Long> {
    Optional<Tio> findByNombre(String nombre);
    Optional<Tio> findByEmail(String email);
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE Tio t SET t.nombre = :nombre, t.email = :email, t.password = :password WHERE t.id = :id")
    int actualizarTioPorId(@Param("id") Long id,
                           @Param("nombre") String nombre,
                           @Param("email") String email,
                           @Param("password") String password);
    boolean existsByIdAndNombre(Long id, String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
}
