package br.com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.algaworks.pedidovenda.model.Pedido;
import br.com.algaworks.pedidovenda.model.StatusPedido;
import br.com.algaworks.pedidovenda.repository.PedidoRepository;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPedidoService cadastroPedidoService;

	@Inject
	private PedidoRepository pedidoRepository;
	
	@Inject
	private EstoqueService estoqueService;
	
	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);

		if (pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com o status " + pedido.getStatus().getDescricao());
		}
		
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(StatusPedido.EMITIDO);
		
		pedido = this.pedidoRepository.guardar(pedido);
		
		return pedido;
	}
	
}
