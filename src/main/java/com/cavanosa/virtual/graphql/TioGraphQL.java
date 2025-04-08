package com.cavanosa.virtual.graphql;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.TioService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import java.util.List;

@Component
public class TioGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver  {

    @Autowired
	private Environment env;
    
    @Autowired
    TioService tioService;

    public Tio tio(Long id) {
        Tio tio = new Tio();
        if(tioService.existsById(id)){
            tio = tioService.getOneById(id).get();
        }
        return tio;
    }

    public List<Tio> getTios() {
        List<Tio> list = tioService.findAll();
        return list;
    }

    public Tio saveTio(TioInput input) {
        Tio tio = new Tio();
        ModelMapper m = new ModelMapper();
        tio = m.map(input,Tio.class);
        if(tioService.save(tio))
            return tio;
        return tio;
    }

    public Boolean deleteTio(Long id) {
        try{
            tioService.delete(id);
            return true;
        }catch(Exception ex){

        }
        return false;
    }
}
