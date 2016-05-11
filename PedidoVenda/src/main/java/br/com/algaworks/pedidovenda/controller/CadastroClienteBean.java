package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.model.Endereco;
import br.com.algaworks.pedidovenda.model.TipoPessoa;
import br.com.algaworks.pedidovenda.service.ClienteService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private Endereco endereco;
	private Endereco enderecoSelecionado;
	
	private String title;

	@Inject
	private ClienteService clienteService;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		endereco = new Endereco();
		title = cliente.getId() == null ? "Novo Cliente" : cliente.getNome();
	}

	public void salvar() {
		clienteService.salvar(cliente);
		FacesUtil.addInfoMessage("Cliente cadastrado com sucesso!");
	}
	
	public void salvarEndereco(){
		endereco.setCliente(cliente);
		cliente.getEnderecos().add(endereco);
		endereco = new Endereco();
	}
	
	public void excluirEndereco(){
		cliente.getEnderecos().remove(enderecoSelecionado);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

}
