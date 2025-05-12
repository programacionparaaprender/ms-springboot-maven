package com.cavanosa.virtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.exceptions.ElementoVacio;
import com.cavanosa.virtual.interfaces.IWriteableTio;
import com.cavanosa.virtual.repository.TioRepository;


@Service
public class TioWriterServiceImpl implements IWriteableTio<Tio, Tio> {
	@Autowired
    TioRepository tioRepository;

    
    public TioWriterServiceImpl(TioRepository tioRepository) {
        this.tioRepository = tioRepository;
    }
	@Override
	public boolean update(Tio model) {
		int result;
		result = this.tioRepository.actualizarTioPorId(model.getId(),
                model.getNombre(),
                model.getEmail(),
                model.getPassword());
		tioRepository.flush(); 
		return (result > 0)?true:false;
	}

	@Override
	public Tio create(Tio model) {
	    if(model.getNombre().isEmpty() || model.getEmail().isEmpty() || model.getPassword().isEmpty()) {
	        throw new ElementoVacio("Debe ingresar nombre, email y password");
	    }

	    if(tioRepository.existsByNombre(model.getNombre()) || tioRepository.existsByEmail(model.getEmail())) {
	        return null;
	    }
	    Tio tio = tioRepository.save(model);
	    tioRepository.flush(); 
	    return tio;
	}

}
