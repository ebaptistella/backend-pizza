package br.com.eurides.example.backend.pizza.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.eurides.example.backend.pizza.dto.PedidoDTO;
import br.com.eurides.example.backend.pizza.dto.PedidoRetornoDTO;
import br.com.eurides.example.backend.pizza.repository.ClienteRepository;
import br.com.eurides.example.backend.pizza.repository.PedidoRepository;
import br.com.eurides.example.backend.pizza.repository.PizzaSaborRepository;
import br.com.eurides.example.backend.pizza.repository.PizzaTamanhoRepository;
import br.com.eurides.example.backend.pizza.repository.domain.Cliente;
import br.com.eurides.example.backend.pizza.repository.domain.Pedido;
import br.com.eurides.example.backend.pizza.repository.domain.PedidoItem;
import br.com.eurides.example.backend.pizza.repository.domain.PizzaSabor;
import br.com.eurides.example.backend.pizza.repository.domain.PizzaTamanho;
import br.com.eurides.example.backend.pizza.service.PedidoService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PedidoServiceImpl implements PedidoService {

	@Lazy
	@Autowired
	private PedidoRepository pedidoRepository;

	@Lazy
	@Autowired
	private ClienteRepository clienteRepository;

	@Lazy
	@Autowired
	private PizzaTamanhoRepository pizzaTamanhoRepository;

	@Lazy
	@Autowired
	private PizzaSaborRepository pizzaSaborRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Optional<PedidoRetornoDTO> create(PedidoDTO domainDTO) throws Exception {
		log.debug("==>Executando o método create:", domainDTO);

		Cliente clienteDomain = adquirirCliente(domainDTO.getNumeroDocumento());
		validarPedidoAberto(clienteDomain);

		Pedido pedidoDomain = persistirPedido(domainDTO, clienteDomain);

		return Optional.of(new PedidoRetornoDTO("Pedido em andamento"));
	}

	private Pedido persistirPedido(PedidoDTO domainDTO, Cliente clienteDomain) throws Exception {
		Pedido pedidoDomain = new Pedido();
		pedidoDomain.setCliente(clienteDomain);
		pedidoDomain.setDataPedido(new Date());
		pedidoDomain.setItens(adquirirPedidoItem(domainDTO));

		Long tempoPedido = pedidoDomain.getItens().stream().mapToLong(PedidoItem::getTempoPreparo).sum();
		pedidoDomain.setTempoPedido(tempoPedido);

		BigDecimal valorPedido = pedidoDomain.getItens().stream().map(PedidoItem::getPreco).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		pedidoDomain.setValorPedido(valorPedido);

		return pedidoRepository.save(pedidoDomain);
	}

	private List<PedidoItem> adquirirPedidoItem(PedidoDTO domainDTO) throws Exception {
		List<PedidoItem> itens = new ArrayList<PedidoItem>();

		PizzaTamanho pizzaTamanho = adquirirPizzaTamanho(domainDTO.getPizzaTamanho());
		PizzaSabor pizzaSabor = adquirirPizzaSabor(domainDTO.getPizzaTamanho());

		BigDecimal itemPreco = pizzaTamanho.getPizzaPreco().getValorPizza();
		Long itemTempoPreparo = pizzaTamanho.getPizzaTempoPreparo().getTempoPreparo();
		if ((pizzaSabor.getTempoAdicional() != null) && (pizzaSabor.getTempoAdicional() > 0L)) {
			itemTempoPreparo = itemTempoPreparo + pizzaSabor.getTempoAdicional();
		}

		PedidoItem item = new PedidoItem();
		item.setPizzaSabor(pizzaSabor);
		item.setPizzaTamanho(pizzaTamanho);
		item.setPreco(itemPreco);
		item.setTempoPreparo(itemTempoPreparo);

		itens.add(item);

		return itens;
	}

	private PizzaSabor adquirirPizzaSabor(Long codigoPizzaSabor) throws Exception {
		Optional<PizzaSabor> pizzaSaborDomain = pizzaSaborRepository.findById(codigoPizzaSabor);
		if (!pizzaSaborDomain.isPresent()) {
			throw new Exception("Sabor " + codigoPizzaSabor + " para pizza não encontrado!");
		}

		return pizzaSaborDomain.get();
	}

	private PizzaTamanho adquirirPizzaTamanho(Long codigoPizzaTamanho) throws Exception {
		Optional<PizzaTamanho> pizzaTamanhoDomain = pizzaTamanhoRepository.findById(codigoPizzaTamanho);
		if (!pizzaTamanhoDomain.isPresent()) {
			throw new Exception("Tamanho " + codigoPizzaTamanho + " para pizza não encontrado!");
		}

		return pizzaTamanhoDomain.get();
	}

	private Cliente adquirirCliente(String numeroDocumento) throws Exception {
		Optional<Cliente> clienteDomain = clienteRepository.findFirstByNumeroDocumentoEquals(numeroDocumento);

		if (!clienteDomain.isPresent()) {
			throw new Exception("Cliente com número de documento " + numeroDocumento + " não encontrado!");
		}

		return clienteDomain.get();
	}

	private void validarPedidoAberto(Cliente clienteDomain) throws Exception {
		List<Pedido> pedidosAbertos = pedidoRepository.findByClienteEqualsAndDataEntregaIsNull(clienteDomain);
		if (pedidosAbertos.size() > 0) {
			throw new Exception("Cliente com número de documento " + clienteDomain.getNumeroDocumento()
					+ " já possui um pedido em andamento!");
		}
	}

}
