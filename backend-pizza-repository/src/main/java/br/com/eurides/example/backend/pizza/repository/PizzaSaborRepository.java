package br.com.eurides.example.backend.pizza.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.eurides.example.backend.pizza.repository.domain.PizzaSabor;

@Repository
public interface PizzaSaborRepository extends PagingAndSortingRepository<PizzaSabor, Long> {

}
