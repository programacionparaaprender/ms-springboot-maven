package com.cavanosa.virtual.service;

import com.cavanosa.virtual.entity.Tio;
import java.util.List;
import java.util.Optional;


public interface TioService {

    public List<Tio> findAll();
    public Optional<Tio> getOneById(Long id);
    public Tio getFindById(Long id);
    public Optional<Tio> getOneByNombre(String nombre);
    public Optional<Tio> getOneByEmail(String email);
    public boolean save(Tio tio);
    public void delete(Long id);
    public boolean existsById(Long id);
    public boolean existsByNombre(String nombre);
    public boolean exixtsByEmail(String email);
}
