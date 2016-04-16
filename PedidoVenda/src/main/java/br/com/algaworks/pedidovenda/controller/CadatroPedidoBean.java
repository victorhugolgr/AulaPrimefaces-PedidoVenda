package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CadatroPedidoBean {

	private List<Integer>  itens;

	public CadatroPedidoBean(){
		itens = new ArrayList<>();
                itens.add(1);
	}
	
	public List<Integer> getItens() {
		return itens;
	}
	
}
