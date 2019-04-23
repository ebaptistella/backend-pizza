package br.com.eurides.example.backend.pizza.dto;

import java.io.Serializable;

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

	@ApiModelProperty(notes = "Status do pedido")
	private String statusPedido;

}
