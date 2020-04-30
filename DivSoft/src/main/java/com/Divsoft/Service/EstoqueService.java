package com.Divsoft.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.Divsoft.Entidade.Estoque;
import com.Divsoft.Entidade.Produto;
import com.Divsoft.Exception.DataIntegrityException;
import com.Divsoft.Exception.objectNotFoundException;
import com.Divsoft.Repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	
	public List<Estoque> BuscaTodos(){
		return estoqueRepository.findAll();
	}
	
	public Estoque BuscaId(Long id) {
		Optional<Estoque> obj = estoqueRepository.findById(id);
		return obj.orElseThrow(()-> new objectNotFoundException("Evento não encontrado.Id:"+id));
	}
	
	public Estoque Salvar(Estoque obj) {
		if(obj.getProduto()!=null) {
			for(Produto o :obj.getProduto()) {
				o.setEstoque(obj);
			}
		}
		return estoqueRepository.save(obj);
	}
	
	
	public Estoque Atualizar(Estoque  obj) {
		return estoqueRepository.save(obj);
	}
	
	
	public void Deletar(Long id) {
		try {
			estoqueRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar um Estoque com Produtos vinculados a ele.");
		}
	}
	
	
}
