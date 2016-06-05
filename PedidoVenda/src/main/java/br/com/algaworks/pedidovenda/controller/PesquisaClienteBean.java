package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.repository.ClienteRepository;
import br.com.algaworks.pedidovenda.repository.filter.ClienteFilter;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Cliente>  clientesFiltrados;

	private ClienteFilter clienteFilter;
	
	private Cliente clienteSelecionado;
	
	@Inject
	private ClienteRepository clienteRepository;
	
	
	@PostConstruct
	public void init(){
		clienteFilter = new ClienteFilter();
		pesquisar();
	}

	public void pesquisar() {
		clientesFiltrados = clienteRepository.filtrados(clienteFilter);
	}
	
	public void excluirCliente(){
		clienteRepository.remove(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);
	}
	
	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public ClienteFilter getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
}
