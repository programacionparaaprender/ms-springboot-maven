package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;

@Service
@Transactional
public class ExistsByIdUseCase implements Usecase<Long, Boolean> {
	TioReportServiceImpl tioReportServiceImpl;


    public ExistsByIdUseCase(TioReportServiceImpl tioReportServiceImpl) {
        this.tioReportServiceImpl = tioReportServiceImpl;
    }
    
    @Override
    public Boolean execute(Long params) {
    	return this.tioReportServiceImpl.existsById(params);
    }
}