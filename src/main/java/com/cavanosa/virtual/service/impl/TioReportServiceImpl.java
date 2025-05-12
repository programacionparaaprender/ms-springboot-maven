package com.cavanosa.virtual.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.virtual.repository.TioRepository;
import com.cavanosa.virtual.interfaces.IReadableTio;
import com.cavanosa.virtual.entity.Tio;

@Service
public class TioReportServiceImpl implements IReadableTio<Tio>{
	@Autowired
    TioRepository tioRepository;

    
    public TioReportServiceImpl(TioRepository tioRepository) {
        this.tioRepository = tioRepository;
    }
	
    @Transactional(readOnly = true)
    @Override
    public Optional<Tio> get(Long id) {
    	return tioRepository.findById(id);
    }
    
    @Cacheable("tios")
    @Transactional(readOnly = true)
    @Override
    public List<Tio> getAll(){
    	tioRepository.flush();
    	return tioRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Long id){
        return tioRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByNombre(String nombre){
        return tioRepository.existsByNombre(nombre);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email){
        return tioRepository.existsByEmail(email);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Optional<Tio> getOneById(Long id){
        return tioRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Optional<Tio> getOneByNombre(String nombre){
        return tioRepository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Tio> getOneByEmail(String email){
        return tioRepository.findByEmail(email);
    }
    
    @Override
    public boolean existsByIdAndNombre(Long id, String nombre){
        return tioRepository.existsByIdAndNombre(id, nombre);
    }
    
    @Override
    public boolean existsByNombreAndIdNot(String nombre, Long id) {
    	return tioRepository.existsByNombreAndIdNot(nombre, id);
    }
    
}
