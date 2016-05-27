package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.algaworks.pedidovenda.model.Categoria;
import br.com.algaworks.pedidovenda.repository.CategoriaRepository;
import br.com.algaworks.pedidovenda.service.CategoriaService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaService categoriaService;
	
	@Inject
	private CategoriaRepository categoriaRepository;
	
	private Categoria categoria;
	private List<Categoria> categoriaRaizes;
	
	@PostConstruct
	public void init(){
		String parametroID;
		parametroID = FacesUtil.getParamentro("categoria");
		
		if(StringUtils.isNotBlank(parametroID)){
			categoria = categoriaRepository.porId(new Long(parametroID));
		}
		
		if(categoria == null){
			limpar();
		}
		
		categoriaRaizes = categoriaRepository.listarCategoriaRaizes();
	}
	
	public void limpar(){
		categoria = new Categoria();
		categoriaRaizes = categoriaRepository.listarCategoriaRaizes();
	}
	
	public void salvar(){
		categoria = categoriaService.salvar(categoria);
		FacesUtil.addInfoMessage("Categoria salva com sucesso!");
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}

	public void setCategoriaRaizes(List<Categoria> categoriasPai) {
		this.categoriaRaizes = categoriasPai;
	}
}
