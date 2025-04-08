package com.cavanosa.virtual.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.exceptions.ElementoVacio;
import com.cavanosa.virtual.repository.TioRepository;
import com.cavanosa.virtual.service.*;

@Service
@Transactional
public class TioServiceImpl implements TioService{

    @Autowired
    TioRepository tioRepository;

    
    public TioServiceImpl(TioRepository tioRepository) {
        this.tioRepository = tioRepository;
    }
    
    @Cacheable("tios")
    @Transactional(readOnly = true)
    @Override
    public List<Tio> findAll(){
        return tioRepository.findAll();
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
    public boolean save(Tio tio){
        if(tio.getNombre().length() == 0 || tio.getEmail().length() == 0 || tio.getPassword().length() == 0) {
            throw new ElementoVacio("Debe ingresar nombre, email y password");
        }
        if(tioRepository.existsByNombre(tio.getNombre()) || tioRepository.existsByEmail(tio.getEmail())) {
            return false;
        }
        tioRepository.save(tio);
        return true;
    }

    @Override
    public void delete(Long id){
        tioRepository.deleteById(id);
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
    public boolean exixtsByEmail(String email){
        return tioRepository.existsByEmail(email);
    }

    @Override
    public Tio getFindById(Long id) {
        Optional<Tio> tioOpt = tioRepository.findById(id);
        if(tioOpt.isPresent()){
            return tioOpt.get();
        }
        return null;
    }
}