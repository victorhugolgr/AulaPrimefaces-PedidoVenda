package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
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
