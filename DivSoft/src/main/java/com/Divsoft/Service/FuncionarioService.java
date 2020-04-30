package com.Divsoft.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Divsoft.Entidade.Funcionario;
import com.Divsoft.Exception.objectNotFoundException;
import com.Divsoft.Repository.FuncionarioRepository;


@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public  List<Funcionario>  ListaFuncionario() {
		 return funcionarioRepository.findAll();
	}
	
	public Funcionario buscaFuncionario(Long id) {
		Optional<Funcionario> opt= funcionarioRepository.findById(id);
		return opt.orElseThrow(()-> new objectNotFoundException("Evento não encontrado.Id:"+id));
	}
	
	public Funcionario save(Funcionario obj) {
		obj.setSenha(encoder.encode(obj.getSenha()));
		return funcionarioRepository.save(obj);
	}
	
	public Funcionario update(Funcionario obj) {
		return funcionarioRepository.save(obj);
	}
	
	public void deleteById(Long id) {
		funcionarioRepository.deleteById(id);
	}

}
