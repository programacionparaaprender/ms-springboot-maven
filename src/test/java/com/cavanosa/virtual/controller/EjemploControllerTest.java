package com.cavanosa.virtual.controller;

import com.cavanosa.virtual.dto.EjemploRequest;
import com.cavanosa.virtual.dto.EjemploResponse;
import com.cavanosa.virtual.service.EjemploService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import org.springframework.context.annotation.Bean;


@WebFluxTest(EjemploController.class)
public class EjemploControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EjemploService ejemploService;

    @TestConfiguration
    static class EjemploServiceTestConfig {
        @Bean
        public EjemploService ejemploService() {
            return Mockito.mock(EjemploService.class);
        }
    }
    
    @Test
    void testIndexGet() {
        EjemploResponse response = new EjemploResponse();
        response.setTexto("Test message");

        Mockito.when(ejemploService.get(Mockito.anyString()))
                .thenReturn(response);

        webTestClient.get()
                .uri("/ejemplo/v1/ejemplo")
                .exchange()
                .expectStatus().isOk()
                .expectBody(EjemploResponse.class)
                .value(resp -> resp.getTexto().equals("Test message"));
    }

    @Test
    void testIndexGetById() {
        String id = "123";
        EjemploResponse response = new EjemploResponse();
        response.setTexto("ID: " + id);

        Mockito.when(ejemploService.get(id)).thenReturn(response);

        webTestClient.get()
                .uri("/ejemplo/v1/ejemplo/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EjemploResponse.class)
                .value(resp -> resp.getTexto().equals("ID: " + id));
    }

    @Test
    void testIndexPost() {
        EjemploRequest request = new EjemploRequest();
        request.setName("Nuevo nombre");

        EjemploResponse response = new EjemploResponse();
        response.setTexto("Guardado: Nuevo nombre");

        Mockito.when(ejemploService.put("Nuevo nombre")).thenReturn(response);

        webTestClient.post()
                .uri("/ejemplo/v1/ejemplo")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EjemploResponse.class)
                .value(resp -> resp.getTexto().equals("Guardado: Nuevo nombre"));
    }
}
