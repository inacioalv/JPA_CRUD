package com.Divsoft.Entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class Debito implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Column(nullable = false)
	private int valor;
	
	@Column(nullable =true)
	private String Descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date data;
	
	
	public Debito() {}
	
	public Debito(int valor, String descricao, Date data) {
		super();
		this.valor = valor;
		Descricao = descricao;
		this.data = data;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	
	
	
	
}
