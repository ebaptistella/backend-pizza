package br.com.eurides.example.backend.pizza.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@ApiOperation(value = BackendPizzaConstant.PATH_CREATE, response = Void.class)
	@PostMapping(BackendPizzaConstant.PATH_CREATE)
	public abstract ResponseEntity<Void> create(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOMAIN_DTO, required = true) PedidoDTO domainDTO)
			throws Exception;

	@ApiOperation(value = BackendPizzaConstant.PATH_PEDIDO_ADICIONAIS, response = Void.class)
	@PostMapping(BackendPizzaConstant.PATH_PEDIDO_ADICIONAIS)
	public abstract ResponseEntity<Void> adicionais(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOCUMENTO, required = true) String nroDocumento,
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_ADICIONAIS, required = true) List<Long> adicionaisPedido)
			throws Exception;

	@ApiOperation(value = BackendPizzaConstant.PATH_FIND_BY_DOCUMENTO, response = PedidoRetornoDTO.class)
	@GetMapping(BackendPizzaConstant.PATH_FIND_BY_DOCUMENTO)
	public abstract ResponseEntity<PedidoRetornoDTO> findByDocumento(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOCUMENTO, required = true) String nroDocumento)
			throws Exception;
}
