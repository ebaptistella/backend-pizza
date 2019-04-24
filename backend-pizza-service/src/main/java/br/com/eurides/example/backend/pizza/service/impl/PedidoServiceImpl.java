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
import br.com.eurides.example.backend.pizza.repository.PizzaAdicionalRepository;
import br.com.eurides.example.backend.pizza.repository.PizzaSaborRepository;
import br.com.eurides.example.backend.pizza.repository.PizzaTamanhoRepository;
import br.com.eurides.example.backend.pizza.repository.domain.Cliente;
import br.com.eurides.example.backend.pizza.repository.domain.Pedido;
import br.com.eurides.example.backend.pizza.repository.domain.PedidoAdicional;
import br.com.eurides.example.backend.pizza.repository.domain.PedidoItem;
import br.com.eurides.example.backend.pizza.repository.domain.PizzaAdicional;
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

	@Lazy
	@Autowired
	private PizzaAdicionalRepository pizzaAdicionalRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void create(PedidoDTO domainDTO) throws Exception {
		log.debug("==>Executando o método create: {}", domainDTO);

		Cliente clienteDomain = adquirirCliente(domainDTO.getNumeroDocumento());
		validarPedidoAberto(clienteDomain);

		persistirPedido(domainDTO, clienteDomain);
	}

	private Pedido persistirPedido(PedidoDTO domainDTO, Cliente clienteDomain) throws Exception {
		Pedido pedidoDomain = new Pedido();
		pedidoDomain.setCliente(clienteDomain);
		pedidoDomain.setDataPedido(new Date());
		pedidoDomain.setItens(adquirirPedidoItem(pedidoDomain, domainDTO));

		Long tempoPedido = pedidoDomain.getItens().stream().mapToLong(PedidoItem::getTempoPreparo).sum();
		pedidoDomain.setTempoPedido(tempoPedido);

		BigDecimal valorPedido = pedidoDomain.getItens().stream().map(PedidoItem::getPreco).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		pedidoDomain.setValorPedido(valorPedido);

		return pedidoRepository.save(pedidoDomain);
	}

	private List<PedidoItem> adquirirPedidoItem(Pedido pedidoDomain, PedidoDTO domainDTO) throws Exception {
		List<PedidoItem> itens = new ArrayList<PedidoItem>();

		PizzaTamanho pizzaTamanho = adquirirPizzaTamanho(domainDTO.getPizzaTamanho());
		PizzaSabor pizzaSabor = adquirirPizzaSabor(domainDTO.getPizzaTamanho());

		BigDecimal itemPreco = pizzaTamanho.getPizzaPreco().getValorPizza();
		Long itemTempoPreparo = pizzaTamanho.getPizzaTempoPreparo().getTempoPreparo();
		if ((pizzaSabor.getTempoAdicional() != null) && (pizzaSabor.getTempoAdicional() > 0L)) {
			itemTempoPreparo = itemTempoPreparo + pizzaSabor.getTempoAdicional();
		}

		PedidoItem item = new PedidoItem();
		item.setPedido(pedidoDomain);
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

	@Override
	public void fecharPedido(String nroDocumento) throws Exception {
		log.debug("==>Executando o método fecharPedido: {}", nroDocumento);

		Cliente clienteDomain = adquirirCliente(nroDocumento);
		List<Pedido> pedidosAbertos = pedidoRepository.findByClienteEqualsAndDataEntregaIsNull(clienteDomain);
		pedidosAbertos.forEach(p -> {
			p.setDataEntrega(new Date());
		});
		pedidoRepository.saveAll(pedidosAbertos);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void adicionais(String nroDocumento, List<Long> adicionaisPedido) throws Exception {
		log.debug("==>Executando o método adicionais: {}", adicionaisPedido);

		Cliente clienteDomain = adquirirCliente(nroDocumento);
		Optional<Pedido> pedidoAberto = pedidoRepository.findFirst1ByClienteEqualsAndDataEntregaIsNull(clienteDomain);
		if (!pedidoAberto.isPresent()) {
			throw new Exception("Cliente com número de documento " + clienteDomain.getNumeroDocumento()
					+ " não possui pedido em andamento!");
		}

		pedidoAberto.get().setAdicionais(adquirirItensAdicionais(pedidoAberto.get(), adicionaisPedido));

		Long tempoPedido = pedidoAberto.get().getTempoPedido();
		BigDecimal valorPedido = pedidoAberto.get().getValorPedido();

		if (pedidoAberto.get().getAdicionais() != null) {
			tempoPedido = tempoPedido + pedidoAberto.get().getAdicionais().stream()
					.map(PedidoAdicional::getTempoPreparoAdicional).reduce(0L, Long::sum);

			valorPedido = valorPedido.add(pedidoAberto.get().getAdicionais().stream()
					.map(PedidoAdicional::getPrecoAdicional).reduce(BigDecimal.ZERO, BigDecimal::add));
		}
		pedidoAberto.get().setTempoPedido(tempoPedido);
		pedidoAberto.get().setValorPedido(valorPedido);

		pedidoRepository.save(pedidoAberto.get());
	}

	private List<PedidoAdicional> adquirirItensAdicionais(Pedido pedidoDomain, List<Long> adicionaisPedido)
			throws Exception {
		List<PedidoAdicional> listPedidoAdicional = new ArrayList<PedidoAdicional>();

		for (Long adicional : adicionaisPedido) {
			PizzaAdicional pizzaAdicional = pizzaAdicionalRepository.findById(adicional)
					.orElseThrow(() -> new Exception("Adicional " + adicional + " não foi encontrado."));

			PedidoAdicional pedidoAdicional = new PedidoAdicional();
			pedidoAdicional.setAdicional(pizzaAdicional);
			pedidoAdicional.setPedido(pedidoDomain);

			BigDecimal precoAdicional = BigDecimal.ZERO;
			if (pizzaAdicional.getPrecoAdicional().getValorAdicional() != null) {
				precoAdicional = pizzaAdicional.getPrecoAdicional().getValorAdicional();
			}
			pedidoAdicional.setPrecoAdicional(precoAdicional);

			Long tempoPreparoAdicional = 0L;
			if (pizzaAdicional.getTempoAdicional() != null) {
				tempoPreparoAdicional = pizzaAdicional.getTempoAdicional();
			}
			pedidoAdicional.setTempoPreparoAdicional(tempoPreparoAdicional);

			listPedidoAdicional.add(pedidoAdicional);
		}

		return listPedidoAdicional;
	}

	@Override
	public Optional<PedidoRetornoDTO> findByDocumento(String nroDocumento) throws Exception {
		log.debug("==>Executando o método findByDocumento: {}", nroDocumento);

		Cliente clienteDomain = adquirirCliente(nroDocumento);

		Optional<Pedido> pedidoDomainOptional = pedidoRepository
				.findFirst1ByClienteEqualsAndDataEntregaIsNull(clienteDomain);
		if (!pedidoDomainOptional.isPresent()) {
			return Optional.empty();
		}

		Pedido pedidoDomain = pedidoDomainOptional.get();
		String pizzaTamanho = pedidoDomain.getItens().stream().findFirst().get().getPizzaTamanho().getNome();
		String pizzaSabor = pedidoDomain.getItens().stream().findFirst().get().getPizzaSabor().getNome();
		BigDecimal valorTotal = pedidoDomain.getValorPedido();
		Long tempoPreparo = pedidoDomain.getTempoPedido();
		List<String> adicionais = new ArrayList<String>();
		pedidoDomain.getAdicionais().stream().forEach(a -> {
			adicionais.add(a.getAdicional().getAdicionalNome());
		});

		return Optional.of(new PedidoRetornoDTO(pizzaTamanho, pizzaSabor, valorTotal, tempoPreparo, adicionais));
	}

}
