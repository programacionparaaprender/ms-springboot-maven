package com.cavanosa.virtual.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.repository.TioRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegracionJpaTest {
    @Autowired
    TioRepository tioRepository;

    @Test
    void testFindById() {
        Optional<Tio> tioOpt = tioRepository.findById(1l);
        assertTrue(tioOpt.isPresent());
        assertEquals("luis", tioOpt.orElseThrow().getNombre());
    }

    @Test
    void testFindByNombre() {
        Optional<Tio> tioOpt = tioRepository.findByNombre("luis");
        assertTrue(tioOpt.isPresent());
        assertEquals("luis", tioOpt.orElseThrow().getNombre());
    }

    @Test
    void testFindByPersonaThrowException() {
        Optional<Tio> tioOpt = tioRepository.findByNombre("Rod");
        assertThrows(NoSuchElementException.class, tioOpt::orElseThrow);
        assertFalse(tioOpt.isPresent());
    }

    @Test
    void testFindAll() {
        List<Tio> cuentas = tioRepository.findAll();
        assertFalse(cuentas.isEmpty());
        assertEquals(3, cuentas.size());
    }

    @Test
    void testSave() {
        // Given
        Tio tioPepe = new Tio("Pepe", "Pepe@ejemplo.com", "1234567890");

        // When
        Tio tioResult = tioRepository.save(tioPepe);
//        Cuenta cuenta = cuentaRepository.findByPersona("Pepe").orElseThrow();
//        Cuenta cuenta = cuentaRepository.findById(save.getId()).orElseThrow();

        // Then
        assertEquals("Pepe", tioResult.getNombre());
//        assertEquals(3, cuenta.getId());
    }

    @Test
    void testUpdate() {
        // Given
        Tio tioPepe = new Tio("Pepe123", "Pepe123@ejemplo.com", "1234567890");

        // When
        Tio tioResult = tioRepository.save(tioPepe);
        //Cuenta cuenta = cuentaRepository.findByPersona("Pepe").orElseThrow();
//        Cuenta cuenta = cuentaRepository.findById(save.getId()).orElseThrow();

        // Then
        assertEquals("Pepe123", tioResult.getNombre());
//        assertEquals(3, cuenta.getId());

    }

    @Test
    void testDelete() {
        assertEquals(3, tioRepository.findAll().size());
        Optional<Tio> tioOpt = tioRepository.findById(3l);
        if(tioOpt.isPresent()){
            Tio tioPepe = tioOpt.get();
            assertEquals("elias", tioPepe.getNombre());
            tioRepository.delete(tioPepe);    
        }

        assertEquals(2, tioRepository.findAll().size());
    }
}
