package com.Divsoft.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Divsoft.Entidade.Funcionario;
import com.Divsoft.Repository.FuncionarioRepository;
import com.Divsoft.security.FucionarioSecurity;


@Service
public class FuncionarioSecurityService implements UserDetailsService  {
	
	@Autowired
	private FuncionarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Funcionario funcionario = repository.findByEmail(email);
		if(funcionario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new FucionarioSecurity(funcionario.getId(),funcionario.getemail(),funcionario.getSenha(),funcionario.getPerfis());
		
		
	}

}
