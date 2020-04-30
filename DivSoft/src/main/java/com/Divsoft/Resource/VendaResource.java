package com.Divsoft.Resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.Divsoft.Entidade.Venda;
import com.Divsoft.Service.VendaService;

@RestController
@RequestMapping(value = "/Venda")
public class VendaResource {
	
	@Autowired
	private VendaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Venda> Lista(){
		return service.Lista();
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public Venda busca(@PathVariable Long id) {
		return service.Busca(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMINISTRADOR') || hasAnyRole('PARTICIPANTE')")
	public ResponseEntity<Void>save(@Valid @RequestBody Venda venda){
		Venda obj= service.save(venda);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR') || hasAnyRole('PARTICIPANTE')")
	public ResponseEntity<Void> Update(@Valid @RequestBody Venda venda, @PathVariable Long id ){
		venda.setId(id);
		service.Update(venda);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR') || hasAnyRole('PARTICIPANTE')")
	public ResponseEntity<Void> Deletar(@Valid @PathVariable Long id){
		service.Deletar(id);
		return ResponseEntity.noContent().build();
		
	}

}
