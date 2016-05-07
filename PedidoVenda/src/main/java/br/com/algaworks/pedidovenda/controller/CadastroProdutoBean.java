package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.algaworks.pedidovenda.model.Categoria;
import br.com.algaworks.pedidovenda.model.Produto;
import br.com.algaworks.pedidovenda.repository.CategoriaRepository;
import br.com.algaworks.pedidovenda.service.CadastroProdutoService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Inject
	private CadastroProdutoService cadastroProdutoService;

	private Produto produto;

	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategorias;

	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {

		if (produto == null) {
			limpar();
		}
		categoriasRaizes = categoriaRepository.listarCategoriaRaizes();

		if (this.categoriaPai != null) {
			carregarSubcategorias();
		}

	}

	public void carregarSubcategorias() {
		subCategorias = categoriaRepository.subcategoriasDe(categoriaPai);
	}

	private void limpar() {
		produto = new Produto();
		produto.setCategoria(new Categoria());
		categoriaPai = null;
		subCategorias = new ArrayList<>();
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public boolean isEditando() {
		if (this.produto == null) {
			return false;
		}
		return this.produto.getId() != null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
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