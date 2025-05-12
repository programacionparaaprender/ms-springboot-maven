package com.cavanosa.domain.port.in.tios;

public interface Usecase<S, T> {
	public T execute(S params);
}