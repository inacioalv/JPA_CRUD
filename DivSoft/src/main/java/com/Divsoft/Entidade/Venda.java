package com.Divsoft.Entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Venda implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 60, name = "nome_cliente",nullable = false)
	@NotBlank(message = "Campo Obricatório")
	@Length(max=60 ,min = 10, message = "O nome deve ter entre 10 e 60 caracteres")
	private String nome_cliente;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data",nullable = true)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date data;
	
	@Column(name = "quantidade",nullable = false)
	private int quantidade;
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "id.venda")
	private Set<ProdutoVenda> produtovenda = new HashSet<>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public Set<ProdutoVenda> getProdutovenda() {
		return produtovenda;
	}

	public void setProdutovenda(Set<ProdutoVenda> produtovenda) {
		this.produtovenda = produtovenda;
	}
	
	
	public List<Produto> getproduto(){
		List<Produto> produtos = new ArrayList<Produto>();
		for(ProdutoVenda o:produtovenda) {
			produtos.add(o.getProduto());
		}
		return produtos;
	}

	public Venda() {}
	
	public Venda(Long id, String nome_cliente, Date data, int quantidade,Cliente cliente) {
		super();
		this.id = id;
		this.nome_cliente = nome_cliente;
		this.data = data;
		this.quantidade = quantidade;
		this.cliente=cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome_cliente == null) ? 0 : nome_cliente.hashCode());
		result = prime * result + quantidade;
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
		Venda other = (Venda) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome_cliente == null) {
			if (other.nome_cliente != null)
				return false;
		} else if (!nome_cliente.equals(other.nome_cliente))
			return false;
		if (quantidade != other.quantidade)
			return false;
		return true;
	}

	
	
	
	
	
	

}
