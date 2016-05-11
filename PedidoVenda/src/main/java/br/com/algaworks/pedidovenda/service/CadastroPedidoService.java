package br.com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.algaworks.pedidovenda.model.Pedido;
import br.com.algaworks.pedidovenda.model.StatusPedido;
import br.com.algaworks.pedidovenda.repository.PedidoRepository;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public Pedido salvar(Pedido pedido){
		if(pedido.isNovo()){
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		return this.pedidoRepository.guardar(pedido);
	}
}
