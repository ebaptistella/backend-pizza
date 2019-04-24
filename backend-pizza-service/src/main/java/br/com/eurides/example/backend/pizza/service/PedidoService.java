package br.com.eurides.example.backend.pizza.service;

import java.util.List;
import java.util.Optional;

import br.com.eurides.example.backend.pizza.dto.PedidoDTO;
import br.com.eurides.example.backend.pizza.dto.PedidoRetornoDTO;

public interface PedidoService {

	public abstract void create(PedidoDTO domainDTO) throws Exception;

	public abstract void fecharPedido(String nroDocumento) throws Exception;

	public abstract void adicionais(String nroDocumento, List<Long> adicionaisPedido) throws Exception;

	public abstract Optional<PedidoRetornoDTO> findByDocumento(String nroDocumento) throws Exception;

}
