package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.impl.TioServiceImpl;
import com.cavanosa.virtual.service.impl.TioWriterServiceImpl;

@Service
@Transactional
public class CreateTioUseCase  implements Usecase<Tio, Tio> {
	
	@Autowired
	TioServiceImpl tioWriterServiceImpl;


    public CreateTioUseCase(TioServiceImpl tioWriterServiceImpl) {
        this.tioWriterServiceImpl = tioWriterServiceImpl;
    }
    
    @Override
    public Tio execute(Tio params) {
    	return this.tioWriterServiceImpl.create(params);
    }
}
