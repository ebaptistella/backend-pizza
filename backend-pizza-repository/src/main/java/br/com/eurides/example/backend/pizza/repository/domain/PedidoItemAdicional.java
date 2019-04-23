package br.com.eurides.example.backend.pizza.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@SequenceGenerator(name = "gen_pedidoitemadicional", sequenceName = "gen_pedidoitemadicional", allocationSize = 1)
@Table(name = "tb_pedidoitemadicional")
public class PedidoItemAdicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(generator = "gen_pedidoitemadicional", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "cd_pedidoitem", nullable = false)
	private PedidoItem pedidoItem;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_pizzaadicional")
	private PizzaAdicional adicional;

	@Column(name = "vl_precoadicional", nullable = false)
	private BigDecimal precoAdicional;

	@Column(name = "nr_tempopreparoadicional", nullable = true)
	private Long tempoPreparoAdicional;

}