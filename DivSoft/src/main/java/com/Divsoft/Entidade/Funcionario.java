package com.Divsoft.Entidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.Divsoft.Enums.PerfilAcesso;
import com.Divsoft.Service.validators.FuncionarioInsert;

@Entity
@FuncionarioInsert
public class Funcionario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome",nullable = false)
	@NotBlank(message = "Campo Obrigatorio")
	@Length(max=60 ,min = 10, message = "O nome deve ter entre 10 e 60 caracteres")
	private String nome;
	
	@Column(name = "login",length = 80, nullable = false,unique = false)
	@NotBlank(message = "Campo Obrigatorio")
	@Email(message = "Informe um E-mail válido")
	private String email;
	
	@Column(name = "senha", nullable = false)
	private  String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@NotNull(message = "E necessário informa um perfil")
	@Size(min = 1,message = "Informe o perfil")
	@CollectionTable
	private Set<Integer> perfis = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void addPerfil(PerfilAcesso perfilAcesso) {
		perfis.add(perfilAcesso.getCodigo());
	}
	
	public Set<PerfilAcesso> getPerfis(){
		return perfis.stream().map(x -> PerfilAcesso.toEnum(x)).collect(Collectors.toSet());
	}
	
	
	
	
	public Funcionario() {}
	
	
	
	public Funcionario(Long id, String nome, String login, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = login;
		this.senha = senha;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	

}
