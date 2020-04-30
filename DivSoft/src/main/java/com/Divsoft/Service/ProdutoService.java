package com.Divsoft.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.Divsoft.Repository.ProdutoRepository;

import com.Divsoft.Entidade.Produto;
import com.Divsoft.Exception.DataIntegrityException;
import com.Divsoft.Exception.objectNotFoundException;



@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	

	
	public List<Produto>Lista(){
		return repository.findAll();
	}
	
	public Produto Busca(Long id) {
		Optional<Produto> opt = repository.findById(id);
		return opt.orElseThrow(()-> new objectNotFoundException("Evento não encontrado.Id:"+id));
	}
	
	
	public Produto save(Produto obj){
		obj.setData(new Date());
		return repository.save(obj);
	}
	
	public Produto Update(Produto produto) {
		Produto objUpdate= Busca(produto.getId());
		produto.setData(objUpdate.getData());
		return repository.save(produto);
	}
	
	public void Deletar(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar um evento com atividades vinculadas a ele.");
		}
		
	}
	
	
	
	
}
