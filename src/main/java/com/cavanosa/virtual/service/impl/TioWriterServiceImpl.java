package com.cavanosa.virtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.exceptions.ElementoVacio;
import com.cavanosa.virtual.interfaces.IWriteable;
import com.cavanosa.virtual.repository.TioRepository;


@Service
@Transactional
public class TioWriterServiceImpl implements IWriteable<Tio> {
	@Autowired
    TioRepository tioRepository;

    
    public TioWriterServiceImpl(TioRepository tioRepository) {
        this.tioRepository = tioRepository;
    }
	@Override
	public boolean update(Tio model) {
		return true;
		
	}

	@Override
	public boolean create(Tio model) {
		if(model.getNombre().length() == 0 || model.getEmail().length() == 0 || model.getPassword().length() == 0) {
            throw new ElementoVacio("Debe ingresar nombre, email y password");
        }
        if(tioRepository.existsByNombre(model.getNombre()) || tioRepository.existsByEmail(model.getEmail())) {
            return false;
        }
        tioRepository.save(model);
        return true;
		
	}

}
