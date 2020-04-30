package com.Divsoft.Entidade;

import java.io.Serializable;

public class CredenciaisAcesso  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
	public CredenciaisAcesso() {
	}
	
	public CredenciaisAcesso(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	

}
