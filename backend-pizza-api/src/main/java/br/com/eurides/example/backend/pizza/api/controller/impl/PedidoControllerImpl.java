package br.com.eurides.example.backend.pizza.api.controller.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eurides.example.backend.pizza.api.controller.PedidoController;
import br.com.eurides.example.backend.pizza.dto.PedidoDTO;
import br.com.eurides.example.backend.pizza.dto.PedidoRetornoDTO;
import br.com.eurides.example.backend.pizza.service.PedidoService;
import br.com.eurides.example.backend.pizza.util.BackendPizzaConstant;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PedidoControllerImpl implements PedidoController {

	@Lazy
	@Autowired
	private PedidoService pedidoService;

	@Override
	public ResponseEntity<PedidoDTO> findById(
			@RequestParam(value = BackendPizzaConstant.PARAMETRO_ID, required = true) Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Page<PedidoDTO>> findAll(Long page, Long size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PedidoRetornoDTO> create(@RequestBody(required = true) @Validated PedidoDTO domainDTO)
			throws Exception {
		log.debug("==>Executando o método create: ", domainDTO);

		Optional<PedidoRetornoDTO> pedidoRetornoDTO = pedidoService.create(domainDTO);
		return new ResponseEntity<>(pedidoRetornoDTO.get(), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PedidoDTO> update(Long id, PedidoDTO domainDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PedidoDTO> findByDocumento(String nroDocumento) {
		// TODO Auto-generated method stub
		return null;
	}

}
