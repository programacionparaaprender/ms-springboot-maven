package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;

@Service
@Transactional
public class ExistsByEmailUseCase  implements Usecase<String, Boolean> {
	TioReportServiceImpl tioReportServiceImpl;


    public ExistsByEmailUseCase(TioReportServiceImpl tioReportServiceImpl) {
        this.tioReportServiceImpl = tioReportServiceImpl;
    }
    
    @Override
    public Boolean execute(String params) {
    	return this.tioReportServiceImpl.existsByEmail(params);
    }
}
