package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.impl.TioRemovableServiceImpl;

@Service
@Transactional
public class DeleteTioUseCase implements Usecase<Long, Tio> {
	TioRemovableServiceImpl tioRemovableServiceImpl;


    public DeleteTioUseCase(TioRemovableServiceImpl tioRemovableServiceImpl) {
        this.tioRemovableServiceImpl = tioRemovableServiceImpl;
    }
    
    @Override
    public Tio execute(Long params) {
    	this.tioRemovableServiceImpl.delete(params);
    	return new Tio(); 
    }
}
