package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Produto;
import br.com.algaworks.pedidovenda.repository.ProdutoRepository;
import br.com.algaworks.pedidovenda.repository.filter.ProdutoFilter;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository produtoRepository;
	
	private Produto produtoSelecionado;
	
	private ProdutoFilter filter;

	private List<Produto> produtosFiltrados;

	public PesquisaProdutosBean(){
		System.out.println("construtor lista produto");
		filter = new ProdutoFilter();
	}
	
	public void excluir(){
		produtoRepository.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
		
		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() + " excluído com sucesso!");
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

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoselecionado) {
		this.produtoSelecionado = produtoselecionado;
	}

}
