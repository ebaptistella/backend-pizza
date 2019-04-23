package br.com.eurides.example.backend.pizza.service;

import java.util.Optional;

import br.com.eurides.example.backend.pizza.dto.PedidoDTO;
import br.com.eurides.example.backend.pizza.dto.PedidoRetornoDTO;

public interface PedidoService {

	public abstract Optional<PedidoRetornoDTO> create(PedidoDTO domainDTO) throws Exception;

}
