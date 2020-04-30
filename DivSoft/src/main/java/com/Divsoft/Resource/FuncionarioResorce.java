package com.Divsoft.Resource;



import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Divsoft.Entidade.Funcionario;
import com.Divsoft.Service.FuncionarioService;

@RestController
@RequestMapping(value = "/Funcionario")
public class FuncionarioResorce {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Funcionario> listaFuncionario(){
		return funcionarioService.ListaFuncionario();
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public Funcionario BuscaFuncionario(@PathVariable Long id) {
		return funcionarioService.buscaFuncionario(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void>save(@Valid @RequestBody Funcionario funcionario){
		Funcionario obj= funcionarioService.save(funcionario);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}" )
	public ResponseEntity<Void>update(@Valid @RequestBody Funcionario funcionario,@PathVariable Long id){
		funcionario.setId(id);
		funcionarioService.update(funcionario);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		funcionarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
