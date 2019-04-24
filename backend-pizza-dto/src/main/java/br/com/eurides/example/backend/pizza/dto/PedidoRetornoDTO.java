package br.com.eurides.example.backend.pizza.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
@AllArgsConstructor
public class PedidoRetornoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Tamanho da pizza")
	private String pizzaTamanho;

	@ApiModelProperty(notes = "Sabor da pizza")
	private String pizzaSabor;

	@ApiModelProperty(notes = "Valor total")
	private BigDecimal valorTotal;

	@ApiModelProperty(notes = "Tempo de preparo")
	private Long tempoPreparo;

	@ApiModelProperty(notes = "Adicionais")
	private List<String> adicionais;
}
