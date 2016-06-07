package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Pedido;
import br.com.algaworks.pedidovenda.service.EmissaoPedidoService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@RequestScoped
public class EmissaoPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoPedidoService emissaoPedidoService;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	
	
	public void emitirPedido(){
		this.pedido.removerItemVazio();
		
		try{
			this.pedido = this.emissaoPedidoService.emitir(this.pedido);
			
			//lançar im evento CDI
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
			
			
			FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
		}finally{
			this.pedido.adicionarItemVazio();
		}
		
	}
}
