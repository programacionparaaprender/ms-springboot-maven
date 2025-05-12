package com.cavanosa.virtual.applications.usecases.tios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;


@Service
@Transactional
public class ExistsByNombreAndIdNot implements Usecase<Tio, Boolean> {
	TioReportServiceImpl tioReportServiceImpl;


    public ExistsByNombreAndIdNot(TioReportServiceImpl tioReportServiceImpl) {
        this.tioReportServiceImpl = tioReportServiceImpl;
    }
    
    @Override
    public Boolean execute(Tio params) {
    	return this.tioReportServiceImpl.existsByNombreAndIdNot(params.getNombre(), params.getId());
    }
}