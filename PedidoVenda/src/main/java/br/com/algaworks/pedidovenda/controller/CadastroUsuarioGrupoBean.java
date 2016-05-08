package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Grupo;
import br.com.algaworks.pedidovenda.model.Usuario;
import br.com.algaworks.pedidovenda.repository.GrupoRepository;
import br.com.algaworks.pedidovenda.repository.UsuarioRepository;
import br.com.algaworks.pedidovenda.service.UsuarioService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioGrupoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	@Inject
	private UsuarioService usuarioService;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private GrupoRepository grupoRepository;

	private List<Grupo> gruposUsuarioFiltrados;

	private Grupo grupoSelecionado;

	public CadastroUsuarioGrupoBean() {
		if (usuario == null) {
			limpar();
		}

	}

	@PostConstruct
	public void init() {
		
		if (usuario == null) {
			gruposUsuarioFiltrados = usuarioRepository.grupos(usuario);
		}

	}

	private void limpar() {
		usuario = new Usuario();
	}

	public void salvar() {
		this.usuario = usuarioService.salvar(usuario);
		limpar();
		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
	}

	public void removeGrupo() {
		grupoRepository.remover(grupoSelecionado);
		gruposUsuarioFiltrados.remove(grupoSelecionado);
		FacesUtil.addInfoMessage("Grupo excluído com sucesso!");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		System.out.println("setUsuario");
	}

	public List<Grupo> getGruposUsuarioFiltrados() {
		return gruposUsuarioFiltrados;
	}

	public void setGruposUsuarioFiltrados(List<Grupo> gruposUsuarioFiltrados) {
		this.gruposUsuarioFiltrados = gruposUsuarioFiltrados;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

}
