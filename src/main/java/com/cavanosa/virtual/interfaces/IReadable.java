package com.cavanosa.virtual.interfaces;

import java.util.List;
import java.util.Optional;


public interface IReadable<T> {
	public Optional<T> get(Long id);
    public List<T> getAll();
}
