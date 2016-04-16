package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CadastroClienteEnderecoBean {

	private List<Integer>  enderecoClienteFiltrados;

	public CadastroClienteEnderecoBean(){
		setEnderecoClienteFiltrados(new ArrayList<>());
		for (int i = 0; i < 5; i++) {
			getEnderecoClienteFiltrados().add(i);
		}
	}

	public List<Integer> getEnderecoClienteFiltrados() {
		return enderecoClienteFiltrados;
	}

	public void setEnderecoClienteFiltrados(List<Integer> enderecoClienteFiltrados) {
		this.enderecoClienteFiltrados = enderecoClienteFiltrados;
	}

}
