package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PesquisaClienteBean {

	private List<Integer>  clientesFiltrados;

	public PesquisaClienteBean(){
		clientesFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			clientesFiltrados.add(i);
		}
	}
	
	public List<Integer> getClientesFiltrados() {
		return clientesFiltrados;
	}
	
}
