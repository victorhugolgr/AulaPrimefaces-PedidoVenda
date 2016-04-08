package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
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
