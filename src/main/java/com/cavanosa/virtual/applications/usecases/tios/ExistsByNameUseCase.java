package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;


@Service
@Transactional
public class ExistsByNameUseCase  implements Usecase<String, Boolean> {
	TioReportServiceImpl tioReportServiceImpl;


    public ExistsByNameUseCase(TioReportServiceImpl tioReportServiceImpl) {
        this.tioReportServiceImpl = tioReportServiceImpl;
    }
    
    @Override
    public Boolean execute(String params) {
    	return this.tioReportServiceImpl.existsByNombre(params);
    }
}
