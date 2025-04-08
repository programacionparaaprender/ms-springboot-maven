package com.cavanosa.virtual.controller;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.repository.TioRepository;
import com.cavanosa.virtual.service.TioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;
import com.cavanosa.virtual.dto.TioDto;

//@WebMvcTest(controllers = TioController.class)
//@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient(timeout = "20000")
@ExtendWith(MockitoExtension.class)
class TioController2Test {

    @Mock 
    private TioRepository tioRepository;
    
    //@Autowired
    //private MockMvc mvc;

    @MockBean
    private TioService cuentaService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testDetalle() {
        String url
          = "http://localhost:8762/tio/detalle/{id}";
        RestTemplate restTemplate = mock(RestTemplate.class);
        Tio tio1 = new Tio(1l,"ejemplo13711","ejemplo13711@gmail.com","123456");
        ResponseEntity<Tio> entity = ResponseEntity.ok(tio1);
        when(restTemplate.getForEntity(url, Tio.class, 1)).thenReturn(entity);
        
        ResponseEntity<Tio> response
          = restTemplate.getForEntity(url, Tio.class, 1);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    void testListar() {
        String url = "http://localhost:8762/tio/lista";
        RestTemplate restTemplate = mock(RestTemplate.class);
        List<Tio> lista = new LinkedList<Tio>();
        Tio tio1 = new Tio(1l,"ejemplo13711","ejemplo13711@gmail.com","123456");
        Tio tio2 = new Tio(2l, "amiya", "amiya@gmail.com", "123456");
        Tio tio3 = new Tio(7l, "ejemplo13712", "ejemplo13712@gmail.com", "123456");
        Tio tio4 = new Tio(8l, "ejemplo13713", "ejemplo13713@gmail.com", "123456");
        lista.add(tio1);
        lista.add(tio2);
        lista.add(tio3);
        lista.add(tio4);
        ResponseEntity<List<Tio>> entity = ResponseEntity.ok(lista);
        when(restTemplate.exchange(url, HttpMethod.GET, null, 
        new ParameterizedTypeReference<List<Tio>>() {
        })).thenReturn(entity);

        ResponseEntity<List<Tio>> response
            = restTemplate.exchange(url, HttpMethod.GET, null, 
            new ParameterizedTypeReference<List<Tio>>() {
            });
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testGuardar() {
        // Given
        String url = "http://localhost:8762/tio/nuevo";
        RestTemplate restTemplate = mock(RestTemplate.class);
        Tio tio = new Tio("amiya", "amiya@gmail.com", "123456");
        TioDto tioDto = new TioDto("amiya", "amiya@gmail.com", "123456");
        ResponseEntity<Tio> entity = ResponseEntity.ok(tio);
        HttpEntity<?> requestEntity = new HttpEntity<>(tioDto);
        when(restTemplate.exchange(url, HttpMethod.POST, requestEntity, Tio.class)).thenReturn(entity);
        ResponseEntity<Tio> response
        = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Tio.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

   /*  
    @Test
    void testDetalle() {
        // Given
        Tio tio1 = new Tio(1,"luis","luis@ejemplo.com","1234567890");
        Optional<Tio> optTio = Optional.of(tio1);
        when(cuentaService.getOneById(1)).thenReturn(optTio);
        when(cuentaService.getFindById(1)).thenReturn(tio1);
        final ResultActions result = mvc.perform(get("/tio/detalle/{id}", 1)
        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("luis"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("luis@ejemplo.com"));


        verify(cuentaService).getOneById(1);
    }

    @Test
    void testListar() throws Exception {
        // Given
       
        List<Tio> lista = new LinkedList<Tio>();
        Tio tio1 = new Tio(1,"ejemplo13711","ejemplo13711@gmail.com","123456");
        Tio tio2 = new Tio(2, "amiya", "amiya@gmail.com", "123456");
        Tio tio3 = new Tio(7, "ejemplo13712", "ejemplo13712@gmail.com", "123456");
        Tio tio4 = new Tio(8, "ejemplo13713", "ejemplo13713@gmail.com", "123456");
        lista.add(tio1);
        lista.add(tio2);
        lista.add(tio3);
        lista.add(tio4);
        when(cuentaService.findAll()).thenReturn(lista);
        // When


        mvc.perform(get("/tio/lista").contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("ejemplo13711"))
                .andExpect(jsonPath("$[1].nombre").value("amiya"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().json(objectMapper.writeValueAsString(lista)));

        verify(cuentaService).findAll();
    }

    @Test
    void testGuardar() throws Exception {
        // Given
        
        Tio tio = new Tio("amiya", "amiya@gmail.com", "123456");
        //when(tioRepository.save(tio)).thenReturn(esperado);
        when(cuentaService.save(tio)).then(new Answer<Tio>(){
            int secuencia = 2;
            @Override
            public Tio answer(InvocationOnMock invocation) throws Throwable{
                Tio tio = invocation.getArgument(0);
                tio.setId(secuencia++);
                return tio;
            }
        });

        //new ResponseEntity<List<Tio>>(list, HttpStatus.CREATED)
        // when
        mvc.perform(post("/tio/nuevo").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tio)))
        // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.nombre", is("amiya")));
        verify(cuentaService).save(tio);

    }
*/ }

