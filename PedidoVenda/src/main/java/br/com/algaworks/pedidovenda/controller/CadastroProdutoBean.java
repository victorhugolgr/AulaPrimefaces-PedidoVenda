package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.algaworks.pedidovenda.model.Categoria;
import br.com.algaworks.pedidovenda.model.Produto;
import br.com.algaworks.pedidovenda.repository.CategoriaRepository;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriaRepository;

	private Produto produto;

	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategorias;

	public CadastroProdutoBean() {
		produto = new Produto();
	}

	@PostConstruct
	public void inicializar() {
		if(FacesUtil.isNotPostBack()){
			categoriasRaizes = categoriaRepository.listarCategoriaRaizes();
		}
	}
	
	public void carregarSubcategorias(){
		subCategorias = categoriaRepository.subcategoriasDe(categoriaPai);
	}

	public void salvar() {
		System.out.println("Categoria pai selecionada: " + categoriaPai.getDescricao());
		System.out.println("SubCategoria pai selecionada: " + produto.getCategoria().getDescricao());
	}

	public Produto getProduto() {
		return produto;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}

	public void setSubCategorias(List<Categoria> subCategoriasRaizes) {
		this.subCategorias = subCategoriasRaizes;
	}

}