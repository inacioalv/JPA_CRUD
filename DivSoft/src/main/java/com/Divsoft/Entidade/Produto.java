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
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "validade",nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date validade;
	
	@Column(name = "descricao",nullable = false)
	@NotBlank(message = "Campo Obrigatorio")
	@Length(max=200 ,min = 3, message = "O nome deve ter entre 3 e 200 caracteres")
	private String descricao;
	
	@Column(name = "quantidade",nullable = false)
	private int quantidade;
	
	@Column(name = "valor",nullable = false)
	private float valor;
	
	@ManyToOne
	@JoinColumn(name = "estoque" , nullable = false)
	private Estoque estoque;
	
	@OneToMany(mappedBy = "id.produto")
	private Set<ProdutoVenda> produtovenda= new HashSet<>();

	
	
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return validade;
	}
	public void setData(Date data) {
		this.validade = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	public Set<ProdutoVenda> getProdutovenda() {
		return produtovenda;
	}
	public void setProdutovenda(Set<ProdutoVenda> produtovenda) {
		this.produtovenda = produtovenda;
	}
	
	
	public List<Venda> getvenda(){
		List<Venda> vendas = new ArrayList<Venda>();
		for(ProdutoVenda o :produtovenda) {
			vendas.add(o.getVenda());
		}
		return vendas;
	}
	
	public Produto () {}
	
	public Produto(Long id, Date validade, String descricao, int quantidade, float valor, Estoque estoque) {
		super();
		this.id = id;
		this.validade = validade;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.estoque = estoque;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + quantidade;
		result = prime * result + ((validade == null) ? 0 : validade.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
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
		Produto other = (Produto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (estoque == null) {
			if (other.estoque != null)
				return false;
		} else if (!estoque.equals(other.estoque))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (validade == null) {
			if (other.validade != null)
				return false;
		} else if (!validade.equals(other.validade))
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}
	
	
	
	
	
	

}
