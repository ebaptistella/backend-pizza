package br.com.eurides.example.backend.pizza.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tb_pizzaadicionalpreco")
public class PizzaAdicionalPreco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_pizzaadicional", nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "pizzaAdicional"))
	private Long codigoPizzaAdicional;

	@Column(name = "vl_precoadicional", nullable = false)
	private BigDecimal valorAdicional;

	@OneToOne
	@PrimaryKeyJoinColumn
	private PizzaAdicional pizzaAdicional;
}