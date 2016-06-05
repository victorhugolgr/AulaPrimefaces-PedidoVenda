package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Categoria;
import br.com.algaworks.pedidovenda.repository.CategoriaRepository;
import br.com.algaworks.pedidovenda.service.CategoriaService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriaBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaRepository categoriaRepository;
	
	
	private List<Categoria> categorias;
	
	private Categoria categoriaSelecionada;
	
	private String descricaoCategoria;
	
	public void pesquisar(){
		categorias = categoriaRepository.filtradas(descricaoCategoria);
	}
	
	public void excluir(){
		categoriaRepository.remove(categoriaSelecionada);
		
		categorias.remove(categoriaSelecionada);
		
		FacesUtil.addInfoMessage("Categoria Excluída com sucesso!");
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String nomeCategoria) {
		this.descricaoCategoria = nomeCategoria;
	}



	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}



	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}
	
}
