package com.cavanosa.virtual.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class QueryGraphQL implements GraphQLQueryResolver {

    public String hello() {
        return "Hello GraphQL";  // Esto nunca debe ser null
    }

    public int soma(int a, int b) {
        return a + b;  // Esto devolverá la suma de los parámetros
    }
}
