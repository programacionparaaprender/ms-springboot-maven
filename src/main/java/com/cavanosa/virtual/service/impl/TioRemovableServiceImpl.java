package com.cavanosa.virtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cavanosa.virtual.interfaces.IRemovable;
import com.cavanosa.virtual.repository.TioRepository;

@Service
public class TioRemovableServiceImpl implements IRemovable{

	@Autowired
    TioRepository tioRepository;

    
    public TioRemovableServiceImpl(TioRepository tioRepository) {
        this.tioRepository = tioRepository;
    }
	
    @Override
    public void delete(Long id){
        tioRepository.deleteById(id);
    }


}
