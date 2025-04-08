package com.cavanosa.virtual.controller;

import java.util.List;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
//import org.springframework.web.reactive.function.BodyInserters;
import org.junit.jupiter.api.Order;
import com.cavanosa.virtual.entity.Tio;


@SpringBootTest
@AutoConfigureWebTestClient(timeout = "20000")
public class TioControllerTest {

    @Autowired
    private WebTestClient webTestClient;

  
    @BeforeAll
    public static void setUp() {
   
    }

    /* @Test
    @Order(1)
    public void indexGetTest() {
        List<Tio> lista = new LinkedList<Tio>();
        Tio tio1 = new Tio(1,"ejemplo13711","ejemplo13711@gmail.com","123456");
        Tio tio2 = new Tio(2,"amiya","amiya@gmail.com","123456");
        Tio tio3 = new Tio(7,"ejemplo13712","ejemplo13712@gmail.com","123456");
        lista.add(tio1);
        lista.add(tio2);
        lista.add(tio3);

        webTestClient.get().uri("/tio/lista")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(Tio.class).consumeWith(response ->{
                List<Tio> data = (List<Tio>)response.getResponseBody();
                Assertions.assertEquals(data, lista);
            });

        this.webClient.get()
            .uri("/dsfds/v1/fds")
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeadersKey.dsfssfds,IDUU)
  
            .exchange()
            .expectStatus().isOk()
            .expectBody(request.class)
        .isEqualTo(response.get("get")); 
    } */

    /* @Test
    @Order(2)
    public void indexPostTest() {
         this.webClient.post()
            .uri("/ejemplos/v1/prueba")
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(request.get(POST)))
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.CREATED)
            .expectBody(response.class)
            .isEqualTo(response.get(POST)); 
    } */

}