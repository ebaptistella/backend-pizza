package br.com.eurides.example.backend.pizza.repository.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@SequenceGenerator(name = "gen_pizzaadicional", sequenceName = "gen_pizzaadicional", allocationSize = 1)
@Table(name = "tb_pizzatempopreparo")
public class PizzaAdicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(generator = "gen_pizzaadicional", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tx_adicionalnome", nullable = false)
	private String adicionalNome;

	@Column(name = "nr_tempoadicional", nullable = true)
	private Long tempoAdicional;

	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "cd_pizzaadicional", insertable = false, updatable = false)
	private PizzaAdicionalPreco precoAdicional;
}