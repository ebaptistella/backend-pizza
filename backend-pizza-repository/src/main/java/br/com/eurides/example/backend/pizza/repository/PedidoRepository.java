package br.com.eurides.example.backend.pizza.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.eurides.example.backend.pizza.repository.domain.Cliente;
import br.com.eurides.example.backend.pizza.repository.domain.Pedido;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {

	public abstract List<Pedido> findByClienteEqualsAndDataEntregaIsNull(Cliente codigoCliente);

}
