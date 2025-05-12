package com.cavanosa.virtual.controller;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.applications.usecases.tios.CreateTioUseCase;
import com.cavanosa.virtual.applications.usecases.tios.DeleteTioUseCase;
import com.cavanosa.virtual.applications.usecases.tios.ExistsByEmailUseCase;
import com.cavanosa.virtual.applications.usecases.tios.ExistsByIdAndNombre;
import com.cavanosa.virtual.applications.usecases.tios.ExistsByIdUseCase;
import com.cavanosa.virtual.applications.usecases.tios.ExistsByNameUseCase;
import com.cavanosa.virtual.applications.usecases.tios.ExistsByNombreAndIdNot;
import com.cavanosa.virtual.applications.usecases.tios.GetTioUseCase;
import com.cavanosa.virtual.applications.usecases.tios.ListTioUseCase;
import com.cavanosa.virtual.applications.usecases.tios.UpdateTioUseCase;
import com.cavanosa.virtual.dto.Mensaje;
import com.cavanosa.virtual.dto.TioDto;
import com.cavanosa.virtual.service.TioService;
import com.cavanosa.virtual.service.impl.TioRemovableServiceImpl;
import com.cavanosa.virtual.service.impl.TioReportServiceImpl;
import com.cavanosa.virtual.service.impl.TioWriterServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tio") //localhost:8080/usuarios
@CrossOrigin
@Controller
public class TioController {

	
	@Autowired
	CreateTioUseCase createTioUseCase;
	
	@Autowired
	ExistsByNameUseCase existsByNameUseCase;
	
	@Autowired
	ExistsByEmailUseCase existsByEmailUseCase;
	
	@Autowired
	ListTioUseCase listTioUseCase;
	
	@Autowired
	GetTioUseCase getTioUseCase;
	
	@Autowired
	ExistsByIdUseCase existsByIdUseCase;
	
	@Autowired
	UpdateTioUseCase updateTioUseCase;
	
	@Autowired
	DeleteTioUseCase deleteTioUseCase;
	
	@Autowired
	ExistsByIdAndNombre existsByIdAndNombre;
	
	@Autowired
	ExistsByNombreAndIdNot existsByNombreAndIdNot;
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ResponseEntity<?> saveTio(@Valid @RequestBody TioDto tioDto, BindingResult bindingResult){
	    try {
	        if(existsByNameUseCase.execute(tioDto.getNombre()))
	            return getMensaje("ya existe ese nombre", HttpStatus.BAD_REQUEST);
	        if(existsByEmailUseCase.execute(tioDto.getEmail()))
	            return getMensaje("ya existe ese email", HttpStatus.BAD_REQUEST);

	        Tio tio = new Tio(tioDto.getNombre(), tioDto.getEmail(), tioDto.getPassword());
	        Tio savedTio = createTioUseCase.execute(tio);

	        if(savedTio != null) {
	            return new ResponseEntity<>(savedTio, HttpStatus.CREATED);
	        } else {
	            return getMensaje("no se pudo guardar el tio", HttpStatus.BAD_REQUEST);
	        }
	    } catch(Exception e) {
	        return getMensaje("error en base de datos", HttpStatus.BAD_REQUEST);
	    }
	}
    
    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Tio>> lista(){
        List<Tio> list = listTioUseCase.execute(new Tio());
        return new ResponseEntity<List<Tio>>(list, HttpStatus.OK);
    }

   
    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        if(!existsByIdUseCase.execute(id))
            return getMensaje("no existe", HttpStatus.NOT_FOUND);
        Tio tio = getTioUseCase.execute(id);
        ResponseEntity<Tio> resultado = ResponseEntity.ok(tio);
        return resultado;
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody TioDto tioDto, BindingResult bindingResult){
        //if(bindingResult.hasErrors())
        //    return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        try{
        	return logeo(tioDto);		
        }catch(Exception e){
        	return getMensaje("error en base de datos", HttpStatus.BAD_REQUEST);
        }

    }
    
    @PostMapping("/logeo")
    private ResponseEntity<?> logeo(TioDto tioDto){
        List<Tio> list2 = listTioUseCase.execute(new Tio());
        	List<Tio> list = new java.util.LinkedList<Tio>();
        	for(Tio temp: list2) {
        		boolean uno = temp.getNombre().equalsIgnoreCase(tioDto.getNombre());
        		boolean dos = temp.getPassword().equalsIgnoreCase(tioDto.getPassword());
        		if(uno && dos) {
        			list.add(temp);
        		}
        	}
            if(list.size() > 0) {
            	return new ResponseEntity<List<Tio>>(list, HttpStatus.OK);
            }else
            	return getMensaje("usuario no existe", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Mensaje> getMensaje(String mensaje, HttpStatus status){
        Mensaje msj = new Mensaje(mensaje);
        ResponseEntity<Mensaje> entity = new ResponseEntity<Mensaje>(msj, status);
        return entity;
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody TioDto tioDto, BindingResult bindingResult, @PathVariable("id") Long id){
    	if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        if(!existsByIdUseCase.execute(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(existsByNameUseCase.execute(tioDto.getNombre()))
            return new ResponseEntity(new Mensaje("ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        if(existsByEmailUseCase.execute(tioDto.getEmail()))
            return new ResponseEntity(new Mensaje("ya existe ese email"), HttpStatus.BAD_REQUEST);
        Tio tio = getTioUseCase.execute(id);
        tio.setNombre(tioDto.getNombre());
        tio.setEmail(tioDto.getEmail());
        tio.setPassword(tioDto.getPassword());
        if(updateTioUseCase.execute(tio)) {
        	return new ResponseEntity(new Mensaje("tio actualizado"), HttpStatus.OK);
        }else {
        	return new ResponseEntity(new Mensaje("tio no actualizado"), HttpStatus.OK);
        }
        
    }

    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteTio(@PathVariable("id") Long id){
        if(!existsByIdUseCase.execute(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        deleteTioUseCase.execute(id);
        return new ResponseEntity(new Mensaje("tio eliminado"), HttpStatus.OK);
    }
}
