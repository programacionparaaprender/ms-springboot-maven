package com.cavanosa.virtual.applications.usecases.tios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.domain.port.in.tios.Usecase;
import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;
import com.cavanosa.virtual.service.impl.TioServiceImpl;

@Service
@Transactional
public class ListTioUseCase  implements Usecase<Tio, List<Tio>> {
	
	@Autowired
	TioServiceImpl tioReportServiceImpl;


    public ListTioUseCase(TioServiceImpl tioReportServiceImpl) {
        this.tioReportServiceImpl = tioReportServiceImpl;
    }
    
    @Override
    public List<Tio> execute(Tio params) {
    	return this.tioReportServiceImpl.getAll();
    }
}