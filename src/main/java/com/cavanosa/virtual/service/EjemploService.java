package com.cavanosa.virtual.service;


import com.cavanosa.virtual.dto.EjemploResponse;

public interface EjemploService {
	EjemploResponse get(String datos);
	EjemploResponse get();
	EjemploResponse put(String name);
}
