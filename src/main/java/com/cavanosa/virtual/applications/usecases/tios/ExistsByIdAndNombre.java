package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;

@Service
@Transactional
public class ExistsByIdAndNombre implements Usecase<Tio, Boolean> {
	TioReportServiceImpl tioReportServiceImpl;


    public ExistsByIdAndNombre(TioReportServiceImpl tioReportServiceImpl) {
        this.tioReportServiceImpl = tioReportServiceImpl;
    }
    
    @Override
    public Boolean execute(Tio params) {
    	return this.tioReportServiceImpl.existsByIdAndNombre(params.getId(), params.getNombre());
    }
}