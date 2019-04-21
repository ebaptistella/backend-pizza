package br.com.eurides.example.backend.pizza.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "stock"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cd_pizzaadicional", unique = true, nullable = false)
	private Long codigoPizzaAdicional;

	@Column(name = "vl_precoadicional", nullable = false)
	private BigDecimal valorAdicional;
}