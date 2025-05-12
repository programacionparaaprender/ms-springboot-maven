package com.cavanosa.virtual.applications.usecases.tios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;


@Service
@Transactional
public class GetTioUseCase  implements Usecase<Long, Tio> {
	TioReportServiceImpl tioReportServiceImpl;


    public GetTioUseCase(TioReportServiceImpl tioReportServiceImpl) {
        this.tioReportServiceImpl = tioReportServiceImpl;
    }
    
    @Override
    public Tio execute(Long params) {
    	Tio tio = this.tioReportServiceImpl.getOneById(params).get();
    	return tio;
    }
}