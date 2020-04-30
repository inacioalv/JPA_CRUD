package com.Divsoft.Entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estoque  implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Quantidade_entrou",nullable = false)
	private int entrada;
	@Column(name = "Quantidade_saio",nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private int saida;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Data_entrada_Produto",nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private  Date dataentrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Data_saida_Produto",nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date datasaida;
	
	
	@OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Produto> ListaProduto= new ArrayList<Produto>();
	
	
	
	
	public Date getDataentrada() {
		return dataentrada;
	}
	public void setDataentrada(Date dataentrada) {
		this.dataentrada = dataentrada;
	}
	public Date getDatasaida() {
		return datasaida;
	}
	public void setDatasaida(Date datasaida) {
		this.datasaida = datasaida;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getEntrada() {
		return entrada;
	}
	public void setEntrada(int entrada) {
		this.entrada = entrada;
	}
	public int getSaida() {
		return saida;
	}
	public void setSaida(int saida) {
		this.saida = saida;
	}
	
	
	
	public List<Produto> getProduto() {
	List<Produto> produtos = new ArrayList<Produto>();
	for(Produto o: ListaProduto){
		o.setEstoque(null);
		produtos.add(o);
		
	}
		return produtos;
	}
	
	
	public void setListaProduto(List<Produto> listaProduto) {
		ListaProduto = listaProduto;
	}
	
	public Estoque() {}
	
	public Estoque(Long id, int entrada, int saida, Date dataentrada, Date datasaida) {
		super();
		this.id = id;
		this.entrada = entrada;
		this.saida = saida;
		this.dataentrada = dataentrada;
		this.datasaida = datasaida;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataentrada == null) ? 0 : dataentrada.hashCode());
		result = prime * result + ((datasaida == null) ? 0 : datasaida.hashCode());
		result = prime * result + entrada;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + saida;
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
		Estoque other = (Estoque) obj;
		if (dataentrada == null) {
			if (other.dataentrada != null)
				return false;
		} else if (!dataentrada.equals(other.dataentrada))
			return false;
		if (datasaida == null) {
			if (other.datasaida != null)
				return false;
		} else if (!datasaida.equals(other.datasaida))
			return false;
		if (entrada != other.entrada)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saida != other.saida)
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
