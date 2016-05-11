package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Pedido;
import br.com.algaworks.pedidovenda.model.StatusPedido;
import br.com.algaworks.pedidovenda.repository.PedidoRepository;
import br.com.algaworks.pedidovenda.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository pedidoRepository;
	
	private PedidoFilter filtro;
	private List<Pedido>  pedidosFiltrados;
	
	
	public PesquisaPedidoBean(){
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}
	
	public void pesquisar(){
		pedidosFiltrados = pedidoRepository.filtrados(filtro);
	}
	
	public StatusPedido[] getStatus(){
		return StatusPedido.values();
	}
	
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}
	
}
