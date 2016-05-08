package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Usuario;
import br.com.algaworks.pedidovenda.repository.UsuarioRepository;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Usuario>  usuariosFiltrados;
	
	private String nomeFiltro;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@PostConstruct
	public void init(){
		pesquisar();
	}

	public void pesquisar() {
		usuariosFiltrados = usuarioRepository.filtrados(nomeFiltro);
	}
	
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}
	
}
