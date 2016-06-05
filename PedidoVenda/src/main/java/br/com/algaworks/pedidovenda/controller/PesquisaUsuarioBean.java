package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Usuario;
import br.com.algaworks.pedidovenda.repository.UsuarioRepository;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Usuario>  usuariosFiltrados;
	
	private String nomeFiltro;
	
	private Usuario usuarioSelecionado;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	
	@PostConstruct
	public void init(){
		pesquisar();
	}

	public void pesquisar() {
		usuariosFiltrados = usuarioRepository.filtrados(nomeFiltro);
	}
	
	public void excluirUsuario(){
		usuarioRepository.remove(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		FacesUtil.addInfoMessage(String.format("O usuário '%s' foi excluído com sucesso!",usuarioSelecionado.getNome()));
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

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
}
