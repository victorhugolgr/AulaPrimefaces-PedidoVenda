package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Pedido;

@Named
@ViewScoped
public class CadatroPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pedido pedido;
	private List<Integer>  itens;

	public CadatroPedidoBean(){
		setPedido(new Pedido());
		itens = new ArrayList<>();
                itens.add(1);
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Integer> getItens() {
		return itens;
	}
	
}
