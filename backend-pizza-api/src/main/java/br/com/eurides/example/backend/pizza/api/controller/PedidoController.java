package br.com.eurides.example.backend.pizza.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@ApiOperation(value = BackendPizzaConstant.PATH_FIND_BY_ID, response = PedidoDTO.class)
	@GetMapping(BackendPizzaConstant.PATH_FIND_BY_ID)
	public abstract ResponseEntity<PedidoDTO> findById(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_ID, required = true) Long id);

	@ApiOperation(value = BackendPizzaConstant.PATH_FIND_ALL, response = Page.class)
	@GetMapping(BackendPizzaConstant.PATH_FIND_ALL)
	public abstract ResponseEntity<Page<PedidoDTO>> findAll(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_PAGE, required = true) Long page,
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_SIZE, required = true) Long size);

	@ApiOperation(value = BackendPizzaConstant.PATH_CREATE, response = PedidoRetornoDTO.class)
	@PostMapping(BackendPizzaConstant.PATH_CREATE)
	public abstract ResponseEntity<PedidoRetornoDTO> create(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOMAIN_DTO, required = true) PedidoDTO domainDTO)
			throws Exception;

	@ApiOperation(value = BackendPizzaConstant.PATH_UPDATE, response = PedidoDTO.class)
	@PutMapping(BackendPizzaConstant.PATH_UPDATE)
	public abstract ResponseEntity<PedidoDTO> update(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_ID, required = true) Long id,
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOMAIN_DTO, required = true) PedidoDTO domainDTO);

	@ApiOperation(value = BackendPizzaConstant.PATH_DELETE)
	@DeleteMapping(BackendPizzaConstant.PATH_DELETE)
	public abstract ResponseEntity<?> delete(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_ID, required = true) Long id);

	@ApiOperation(value = BackendPizzaConstant.PATH_FIND_BY_DOCUMENTO, response = PedidoDTO.class)
	@GetMapping(BackendPizzaConstant.PATH_FIND_BY_DOCUMENTO)
	public abstract ResponseEntity<PedidoDTO> findByDocumento(
			@ApiParam(value = BackendPizzaConstant.PARAMETRO_DOCUMENTO, required = true) String nroDocumento);
}
