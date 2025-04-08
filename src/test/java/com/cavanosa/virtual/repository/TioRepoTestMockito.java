package com.cavanosa.virtual.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.repository.TioRepository;
import com.cavanosa.virtual.service.TioService;
import com.cavanosa.virtual.service.impl.TioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.junit.jupiter.api.Order;


import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TioRepoTestMockito {
    
    @Mock 
    private TioRepository tioRepository;
  
    @InjectMocks
    private TioServiceImpl tioService;

 
    @BeforeEach 
    void setUp()
    {
       
        
    }


    @Test
    @Order(4)
     void createTioTestSame()
    {
        Tio tio = new Tio(2l,"amiya", "amiya@gmail.com", "123456");
        Tio esperado = new Tio(2l,"amiya", "amiya@gmail.com", "123456");
        when(tioRepository.save(tio)).thenReturn(esperado);
        Tio resultado = tioRepository.save(tio);
        //comparando objetos
        assertSame(esperado, resultado);
    }

    @Test
    @Order(3)
    void createTioTest()
    {
        Tio tio = new Tio("amiya", "amiya@gmail.com", "123456");
        Tio esperado = new Tio(2l,"amiya", "amiya@gmail.com", "123456");
        //when(tioRepository.save(tio)).thenReturn(esperado);
        when(tioRepository.save(any(Tio.class))).then(new Answer<Tio>(){
            Long secuencia = 2l;
            @Override
            public Tio answer(InvocationOnMock invocation) throws Throwable{
                Tio tio = invocation.getArgument(0);
                tio.setId(secuencia++);
                return tio;
            }
        });
        Tio resultado = tioRepository.save(tio);
        assertEquals(esperado, resultado);
        verify(tioRepository).save(tio);
        verify(tioRepository).save(any(Tio.class));
    }

    @Test
    @Order(2)
    void testCrearNombreNullException() {
        
        when(tioRepository.findByNombre(isNull())).thenThrow(new IllegalArgumentException());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tioRepository.findByNombre(null);
        });
        assertEquals(IllegalArgumentException.class, exception.getClass());
        verify(tioRepository).findByNombre(isNull());

    }

    @Test
    @Order(1)
    void obtenerUnTioPorId(){
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findById(2l)).thenReturn(optTio);
        Optional<Tio> resultado = tioRepository.findById(2l);
        assertTrue(resultado.isPresent());
    }
    
    @Test
    void obtenerTioPorNombre() {
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findByNombre("amiya")).thenReturn(optTio);
        Optional<Tio> resultado = tioRepository.findByNombre("amiya");
        assertTrue(resultado.isPresent());
    }
    
    @Test
    void obtenerTioPorNombreVerify() {
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findByNombre("amiya")).thenReturn(optTio);
        Optional<Tio> resultado = tioRepository.findByNombre("amiya");
        assertTrue(resultado.isPresent());
        //que se haya ejecutado una vez
        verify(tioRepository, times(1)).findByNombre("amiya");
        // que se haya ejecutado al menos una vez
        verify(tioRepository, atLeast(1)).findByNombre("amiya");
        // que se haya ejecutado al menos una vez
        verify(tioRepository, atLeastOnce()).findByNombre("amiya");
        // que se haya ejecutado a lo mucho una sola vez
        verify(tioRepository, atMost(1)).findByNombre("amiya");
        // que se haya ejecutado a lo mucho una sola vez
        verify(tioRepository, atMostOnce()).findByNombre("amiya");
    }

    @Test
    void obtenerTioPorNombreVerificarInteracciones() {
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findByNombre("amiya")).thenReturn(optTio);
        Optional<Tio> resultado = tioRepository.findByNombre("amiya");
        assertTrue(resultado.isPresent());
        //que se haya ejecutado una vez
        verify(tioRepository, times(1)).findByNombre("amiya");
        //nunca se ejecuto
        verify(tioRepository, never()).findByEmail("amiya@gmail.com");
        verify(tioRepository, never()).findById(2l);
        
        //da error por que si hay una interacción
        //verifyNoInteractions(tioRepository);

     
    }

    @Test
    void obtenerLosTiosPorId(){
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findById(2l)).thenReturn(optTio);
        Optional<Tio> resultado = tioRepository.findById(2l);
        assertTrue(resultado.isPresent());
        verify(tioRepository).findById(argThat(arg -> arg != null && arg > 0 && arg < 8));
    }

    @Test
    void obtenerLosTiosPorIdMatchers() {
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findById(anyLong())).thenReturn(optTio);
        Optional<Tio> resultado = tioRepository.findById(2l);
        assertTrue(resultado.isPresent());
        //no da error por que el valor buscado cumple con la norma
        verify(tioRepository).findById(anyLong());

    }

    public static class MiArgsMatchers implements ArgumentMatcher<Integer>{

        private Integer argument;

        @Override
        public boolean matches(Integer argument) {
            this.argument = argument;
            return argument != null && argument > 0;
        }

        @Override
        public String toString() {
            return argument + " debe ser un entero positivo";
        }
    }

    @Test
    void testDoThrowGuardarTio() {
        Tio tio = new Tio(2l,"amiya","amiya@gmail.com","123456");
        doThrow(IllegalArgumentException.class).when(tioRepository).save(any());

        assertThrows(IllegalArgumentException.class, () -> {
            tioRepository.save(tio);
        });
    }

    @Test
    void testDoAnswerObtenerTio() {
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findById(2l)).thenReturn(optTio);
        Optional<Tio> resultado = tioRepository.findById(2l);
        assertTrue(resultado.isPresent());
        /*
        doAnswer(invocation -> {
            int id = invocation.getArgument(0);
            return id == 2? optTio: Optional.empty();
        }).when(tioRepository).findById(2l);
        Optional<Tio> resultado2 = tioService.getOneById(2l);
        assertTrue(resultado2.isPresent());
         */
    }

    @Test
    void testDoAnswerGuardarTio() {
        Tio tio = new Tio("amiya", "amiya@gmail.com", "123456");
        Tio esperado = new Tio(2l,"amiya", "amiya@gmail.com", "123456");
        //when(tioRepository.save(tio)).thenReturn(esperado);
        doAnswer(new Answer<Tio>(){
            Long secuencia = 2l;
            @Override
            public Tio answer(InvocationOnMock invocation) throws Throwable{
                Tio tio = invocation.getArgument(0);
                tio.setId(secuencia++);
                return tio;
            }
        }).when(tioRepository).save(any(Tio.class));

        Tio resultado = tioRepository.save(tio);
        assertEquals(esperado, resultado);
        verify(tioRepository).save(tio);
        verify(tioRepository).save(any(Tio.class));

    }

        /*
    @Test
    void testDoCallRealMethodObtenerTios() {
        Tio tio = new Tio(2, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findById(2)).thenReturn(optTio);
        doCallRealMethod().when(tioRepository).findById(2);
        Tio resultado2 = tioRepository.findById(2).get();
        assertEquals("amiya", resultado2.getNombre());

    }
*/

    @Test
    void testSpyObtenerTios() {
        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        TioRepository tioRepository = spy(TioRepository.class);
        TioService tioService = new TioServiceImpl(tioRepository);
        doReturn(optTio).when(tioRepository).findById(2l);
        Optional<Tio> optTio2 = tioService.getOneById(2l);
        assertTrue(optTio2.isPresent());
        Tio resultado = optTio2.get();
        assertEquals(2, resultado.getId());
        assertEquals("amiya", resultado.getNombre());
        
        verify(tioRepository).findById(anyLong());
    }

    @Test
    void obtenerTodosLosTios(){
        List<Tio> lista = new LinkedList<Tio>();
        Tio tio1 = new Tio(1l,"ejemplo13711","ejemplo13711@gmail.com","123456");
        Tio tio2 = new Tio(2l,"amiya","amiya@gmail.com","123456");
        Tio tio3 = new Tio(7l,"ejemplo13712","ejemplo13712@gmail.com","123456");
        lista.add(tio1);
        lista.add(tio2);
        lista.add(tio3);
        when(tioRepository.findAll()).thenReturn(lista);
        List<Tio> esperado = tioRepository.findAll();
        assertEquals(esperado, lista);
        assertEquals(esperado.size(), 3);
        verify(tioRepository).findAll();
    }

    @Test
    void testOrdenDeInvocaciones() {
        Tio tio1 = new Tio(1l,"ejemplo13711","ejemplo13711@gmail.com","123456");
        Optional<Tio> optTio1 = Optional.of(tio1);
        when(tioRepository.findById(1l)).thenReturn(optTio1);
        Optional<Tio> resultado1 = tioService.getOneById(1l);


        Tio tio = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Optional<Tio> optTio = Optional.of(tio);
        when(tioRepository.findById(2l)).thenReturn(optTio);
        Optional<Tio> resultado2 = tioService.getOneById(2l);
        
        InOrder inOrder = Mockito.inOrder(tioRepository);
        inOrder.verify(tioRepository).findById(1l);
        inOrder.verify(tioRepository).findById(2l);

    }
    

}
