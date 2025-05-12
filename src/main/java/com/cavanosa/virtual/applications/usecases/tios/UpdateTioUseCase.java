package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.impl.TioWriterServiceImpl;


@Service
@Transactional
public class UpdateTioUseCase  implements Usecase<Tio, Boolean> {
	TioWriterServiceImpl tioWriterServiceImpl;


    public UpdateTioUseCase(TioWriterServiceImpl tioWriterServiceImpl) {
        this.tioWriterServiceImpl = tioWriterServiceImpl;
    }
    
    @Override
    public Boolean execute(Tio params) {
    	return this.tioWriterServiceImpl.update(params);
    }
}