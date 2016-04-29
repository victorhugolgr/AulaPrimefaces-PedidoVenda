package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.model.Endereco;
import br.com.algaworks.pedidovenda.model.TipoPessoa;

@Named
@RequestScoped
public class CadastroClienteBean {

	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();
	private List<Integer> enderecoClienteFiltrados;

	public CadastroClienteBean() {
		setEnderecoClienteFiltrados(new ArrayList<>());
		for (int i = 0; i < 5; i++) {
			getEnderecoClienteFiltrados().add(i);
		}
	}

	public TipoPessoa[] listaTipoPessoa() {
		return TipoPessoa.values();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Integer> getEnderecoClienteFiltrados() {
		return enderecoClienteFiltrados;
	}

	public void setEnderecoClienteFiltrados(List<Integer> enderecoClienteFiltrados) {
		this.enderecoClienteFiltrados = enderecoClienteFiltrados;
	}

}
