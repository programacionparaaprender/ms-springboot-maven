package com.cavanosa.virtual.service.impl;

import org.springframework.stereotype.Service;
import com.cavanosa.virtual.dto.EjemploResponse;
import com.cavanosa.virtual.service.EjemploService;


@Service
public class EjemploServiceImpl implements EjemploService {

	@Override
	public EjemploResponse get(String datos) {
		EjemploResponse response = new EjemploResponse();
		response.setTexto(datos);
		return response;
	}

	@Override
	public EjemploResponse get() {
		EjemploResponse response = new EjemploResponse();
		response.setTexto("Response");
		return response;
	}

	@Override
	public EjemploResponse put(String name) {
		EjemploResponse response = new EjemploResponse();
		response.setTexto(name);
		return response;
	}

}
