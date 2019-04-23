package br.com.eurides.example.backend.pizza.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.eurides.example.backend.pizza.repository.domain.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

	public abstract Optional<Cliente> findFirstByNumeroDocumentoEquals(String numeroDocumento);

}
