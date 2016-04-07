package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
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
