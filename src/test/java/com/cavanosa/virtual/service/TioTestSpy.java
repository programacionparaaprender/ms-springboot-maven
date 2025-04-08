package com.cavanosa.virtual.service;

import com.cavanosa.virtual.repository.TioRepository;
import com.cavanosa.virtual.service.impl.TioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TioTestSpy {
    
    @Spy
    private TioRepository tioRepository;
  
    @InjectMocks
    private TioServiceImpl tioService;

 
    @BeforeEach 
    void setUp()
    {
       
        
    }

    /* @Test
    void testSpyObtenerTios() {
        Tio tio = new Tio(2, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        TioRepository tioRepository = spy(TioRepository.class);
        TioService tioService = new TioService(tioRepository);
        doReturn(optTio).when(tioRepository).findById(2);
        Optional<Tio> optTio2 = tioService.getOneById(2);
        assertTrue(optTio2.isPresent());
        Tio resultado = optTio2.get();
        assertEquals(2, resultado.getId());
        assertEquals("amiya", resultado.getNombre());
        
        verify(tioRepository).findById(anyInt());
    } */
}
