package br.com.eurides.example.backend.pizza.repository.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SequenceGenerator(name = "gen_pizzatamanho", sequenceName = "gen_pizzatamanho", allocationSize = 1)
@Table(name = "tb_pizzatamanho")
public class PizzaTamanho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(generator = "gen_pizzatamanho", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tx_tamanhonome", length = 200, nullable = false)
	private String nome;

	@OneToOne(mappedBy = "pizzaTamanho")
	private PizzaPreco pizzaPreco;

	@OneToOne(mappedBy = "pizzaTamanho")
	private PizzaTempoPreparo pizzaTempoPreparo;

}
