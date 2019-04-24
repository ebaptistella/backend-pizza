package br.com.eurides.example.backend.pizza.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eurides.example.backend.pizza.dto.PedidoDTO;
import br.com.eurides.example.backend.pizza.dto.PedidoRetornoDTO;
import br.com.eurides.example.backend.pizza.util.BackendPizzaConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { BackendPizzaConstant.API_TAGPEDIDO }, protocols = BackendPizzaConstant.PROTOCOLO_HTTP)
@RequestMapping(value = BackendPizzaConstant.PATH_PEDIDO, consumes = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE })
public interface PedidoController {

	@ApiOperation(value = BackendPizzaConstant.PATH_CREATE, response = PedidoRetornoDTO.class)
	@PostMapping(BackendPizzaConstant.PATH_CREATE)
	public abstract ResponseEntity<PedidoRetornoDTO> create(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOMAIN_DTO, required = true) PedidoDTO domainDTO)
			throws Exception;

	@ApiOperation(value = BackendPizzaConstant.PATH_PEDIDO_ADICIONAIS, response = PedidoRetornoDTO.class)
	@PostMapping(BackendPizzaConstant.PATH_PEDIDO_ADICIONAIS)
	public abstract ResponseEntity<PedidoRetornoDTO> adicionais(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOCUMENTO, required = true) String nroDocumento,
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_ADICIONAIS, required = true) List<Long> adicionaisPedido)
			throws Exception;
}
