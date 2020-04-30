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


import com.Divsoft.Entidade.Produto;
import com.Divsoft.Service.ProdutoService;

@RestController
@RequestMapping(value = "/Produto")
public class ProdutoResource {
	
	
	@Autowired
	private ProdutoService produtoService;
	

	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> Lista(){
		return produtoService.Lista();
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public Produto busca(@PathVariable Long id) {
		return produtoService.Busca(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMINISTRADOR') || hasAnyRole('PARTICIPANTE')")
	public ResponseEntity<Void>save(@Valid @RequestBody Produto produto){
		Produto obj= produtoService.save(produto);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR') || hasAnyRole('PARTICIPANTE')")
	public ResponseEntity<Void> Update(@Valid @RequestBody Produto produto, @PathVariable Long id ){
		produto.setId(id);
		produtoService.Update(produto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR') || hasAnyRole('PARTICIPANTE')")
	public ResponseEntity<Void> Deletar(@Valid @PathVariable Long id){
		produtoService.Deletar(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	

}
