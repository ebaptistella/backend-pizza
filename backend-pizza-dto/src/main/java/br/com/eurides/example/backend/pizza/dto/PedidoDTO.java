package br.com.eurides.example.backend.pizza.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Número de documento")
	@NotNull(message = "Número de documento não pode ser nulo.")
	@Length(min = 14, max = 14, message = "Número de documento deve conter exatamente 14 caracteres (CPF com formatação).")
	private String numeroDocumento;

	@ApiModelProperty(notes = "Tamanho da pizza")
	@NotNull(message = "Tamanho da pizza não pode ser nulo.")
	@Positive(message = "Tamanho da pizza não pode ser menor que 1 (um).")
	private Long pizzaTamanho;

	@ApiModelProperty(notes = "Sabor da pizza")
	@NotNull(message = "Sabor da pizza não pode ser nulo.")
	@Positive(message = "Sabor da pizza não pode ser menor que 1 (um).")
	private Long pizzaSabor;
}
