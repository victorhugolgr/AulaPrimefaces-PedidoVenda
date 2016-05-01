package br.com.algaworks.pedidovenda.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Produto;
import br.com.algaworks.pedidovenda.repository.ProdutoRepository;
import br.com.algaworks.pedidovenda.repository.filter.ProdutoFilter;

@Named
@RequestScoped
public class PesquisaProdutosBean {

	@Inject
	private ProdutoRepository produtoRepository;

	private ProdutoFilter filter;

	private List<Produto> produtosFiltrados;

	public PesquisaProdutosBean(){
		System.out.println("construtor lista produto");
		filter = new ProdutoFilter();
	}
	
	public void pesquisar() {
		produtosFiltrados = produtoRepository.filtrados(filter);
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFilter() {
		return filter;
	}

}
