package br.com.eurides.example.backend.pizza.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@SequenceGenerator(name = "gen_pedidoitem", sequenceName = "gen_pedidoitem", allocationSize = 1)
@Table(name = "tb_pedidoitem")
public class PedidoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(generator = "gen_pedidoitem", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "vl_preco", nullable = false)
	private BigDecimal preco;

	@Column(name = "nr_tempopreparo", nullable = false)
	private Long tempoPreparo;

	@JoinColumn(name = "cd_pizzatamanho", referencedColumnName = "id", nullable = false, insertable = true, updatable = true)
	@OneToOne
	private PizzaTamanho pizzaTamanho;

	@JoinColumn(name = "cd_pizzasabor", referencedColumnName = "id", nullable = false, insertable = true, updatable = true)
	@OneToOne
	private PizzaSabor pizzaSabor;

	@JoinColumn(name = "cd_pedido", referencedColumnName = "id", nullable = false, insertable = true, updatable = true)
	@ManyToOne
	private Pedido pedido;

}