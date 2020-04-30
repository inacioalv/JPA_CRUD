package com.Divsoft.Resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Divsoft.Entidade.Cliente;

import com.Divsoft.Service.ClienteService;

@RestController
@RequestMapping(value = "/Cliente" )
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Cliente>> findAll(
			@RequestParam(value = "page" ,defaultValue = "0") Integer page, 
			@RequestParam(value = "size" ,defaultValue = "24") Integer size,
			@RequestParam(value = "direction" ,defaultValue = "ASC") String direction, 
			@RequestParam(value = "orderBy" ,defaultValue = "nome") String orderBy){
		
		Page<Cliente> pageOBJ= clienteService.findAll(page, size, direction, orderBy);
		return ResponseEntity.ok().body(pageOBJ);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public Cliente buscaCliente(@PathVariable Long id) {
		return clienteService.BuscaCliente(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMINISTRADOR') || hasAnyRole('PARTICIPANTE')")
	public ResponseEntity<Void>save(@Valid @RequestBody Cliente cliente){
		Cliente obj= clienteService.Salvar(cliente);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Void> UpdateCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id ){
		cliente.setId(id);
		clienteService.Update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Void> DeletarCliente(@Valid @PathVariable Long id){
		clienteService.Deletar(id);
		return ResponseEntity.noContent().build();
		
	}

}
