package com.cavanosa.virtual.interfaces;

import java.util.List;
import java.util.Optional;

import com.cavanosa.virtual.entity.Tio;

public interface IReadableTio<T>{
	public Optional<T> get(Long id);
    public List<T> getAll();
	public boolean existsById(Long id);
    public boolean existsByNombre(String nombre);
    public boolean existsByEmail(String email);
    public Optional<Tio> getOneById(Long id);
    public Optional<Tio> getOneByNombre(String nombre);
    public Optional<Tio> getOneByEmail(String email);
    public boolean existsByIdAndNombre(Long id, String nombre);
    public boolean existsByNombreAndIdNot(String nombre, Long id);
}
