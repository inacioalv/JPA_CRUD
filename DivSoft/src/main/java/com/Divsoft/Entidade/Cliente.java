package com.Divsoft.Entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
public class Cliente  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( length = 60 , name = "nome",nullable = false)
	@NotBlank(message = "Obrigatóri ter o nome")
	@Length(max=60 ,min = 10, message = "O nome deve ter entre 10 e 60 caracteres")
	private String nome;
	
	@Column(name = "endereco",nullable = false)
	@NotBlank(message = "Obrigatóri ter o endereço")
	@Length(max=60 ,min = 10, message = "O endereço deve ter entre 10 e 60 caracteres")
	private String endereco;
	
	@Column(name = "telefone",nullable = false)
	@NotNull(message = "Informe o telefone")
	private int telefone;
	
	
	@Column(name = "cpf",nullable = false)
	@CPF(message = "Informe um CPF valido")
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Data_cadastro_Cliente",nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date DataCadastro;
	
	@Embedded
	private Debito debito;
	
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Venda> listavenda= new ArrayList<Venda>();

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Date getDataCadastro() {
		return DataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		DataCadastro = dataCadastro;
	}
	
	public Debito getDebito() {
		return debito;
	}
	
	public void setDebito(int valor, String descricao, Date data) {
		this.debito = new Debito( valor,descricao,data);
	}
	
	public void setDebito(Debito debito) {
		this.debito = debito;
	}
	
	public List<Venda> getvenda() {
		List<Venda> vendas = new ArrayList<Venda>();
		for(Venda o: listavenda){
			o.setCliente(null);
			vendas.add(o);}
		return vendas;
	}
	
	public void setListavenda(List<Venda> listavenda) {
		this.listavenda = listavenda;
	}
	
	
	
	public Cliente() {}
	
	public Cliente(Long id, String nome, String endereco, int telefone, String cpf,Date DataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cpf = cpf;
		this.DataCadastro=DataCadastro;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DataCadastro == null) ? 0 : DataCadastro.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((debito == null) ? 0 : debito.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listavenda == null) ? 0 : listavenda.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + telefone;
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
		Cliente other = (Cliente) obj;
		if (DataCadastro == null) {
			if (other.DataCadastro != null)
				return false;
		} else if (!DataCadastro.equals(other.DataCadastro))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (debito == null) {
			if (other.debito != null)
				return false;
		} else if (!debito.equals(other.debito))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listavenda == null) {
			if (other.listavenda != null)
				return false;
		} else if (!listavenda.equals(other.listavenda))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone != other.telefone)
			return false;
		return true;
	}
	
	
	

}
