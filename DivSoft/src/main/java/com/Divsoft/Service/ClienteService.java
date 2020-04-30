package com.Divsoft.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.Divsoft.Entidade.Cliente;
import com.Divsoft.Entidade.Venda;
import com.Divsoft.Exception.objectNotFoundException;
import com.Divsoft.Repository.ClienteRepository;
import com.Divsoft.Repository.VendaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private VendaRepository repository;
	
	
	public Page<Cliente>findAll(Integer page, Integer size,String direction, String orderBy){
		PageRequest pageRequest= PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente BuscaCliente(Long id) {
		Optional<Cliente> opt = clienteRepository.findById(id);
		return opt.orElseThrow(()-> new objectNotFoundException("Evento não encontrado.Id:"+id));
	}
	
	
	public Cliente Salvar(Cliente obj) {
		if(obj.getvenda()!=null) {
			for(Venda o :obj.getvenda()) {
				o.setCliente(obj);
			}
		}
		return clienteRepository.save(obj);
	}
	
	
	public Cliente Update(Cliente obj) {
		Cliente objUpdate= BuscaCliente(obj.getId());
		List<Venda> vendadeleta = new ArrayList<Venda>();
		if(obj.getvenda()!=null) {
			for(Venda o :obj.getvenda()) {
				o.setCliente(obj);
			}
			
			for(Venda vendaUpdate:objUpdate.getvenda()) {
				boolean isPresent = false;
				for(Venda o :obj.getvenda()) {
					if(vendaUpdate.getId().equals(o.getId())) {
						isPresent= true;
					}
				}
				if(!isPresent) {
					vendadeleta.add(vendaUpdate);
				}
			}
		}
		obj=clienteRepository.save(obj);
		repository.deleteAll(vendadeleta);
		return obj;
	}

	
	public void Deletar(Long id) {
		clienteRepository.deleteById(id);
	}

}
