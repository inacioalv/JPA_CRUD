package com.Divsoft.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Divsoft.Entidade.Venda;
import com.Divsoft.Exception.objectNotFoundException;
import com.Divsoft.Repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository repository;
	
	public List<Venda>Lista(){
		return repository.findAll();
	}
	
	public Venda Busca(Long id) {
		Optional<Venda> opt = repository.findById(id);
		return opt.orElseThrow(()-> new objectNotFoundException("Evento não encontrado.Id:"+id));
	}
	
	
	public Venda save(Venda obj) {
		obj.setData(new Date());
		return repository.save(obj);
	}
	
	public Venda Update(Venda obj) {
		Venda objUpdate= Busca(obj.getId());
		obj.setData(objUpdate.getData());
		return repository.save(obj);
	}
	
	public void Deletar(Long id) {
		repository.deleteById(id);
	}

}
