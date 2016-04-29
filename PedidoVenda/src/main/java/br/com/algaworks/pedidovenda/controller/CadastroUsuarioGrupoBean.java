package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Usuario;

@Named
@RequestScoped
public class CadastroUsuarioGrupoBean {

	private Usuario usuario;

	private List<Integer> gruposUsuarioFiltrados;

	public CadastroUsuarioGrupoBean() {
		usuario = new Usuario();

		setGruposUsuarioFiltrados(new ArrayList<>());
		for (int i = 0; i < 5; i++) {
			getGruposUsuarioFiltrados().add(i);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Integer> getGruposUsuarioFiltrados() {
		return gruposUsuarioFiltrados;
	}

	public void setGruposUsuarioFiltrados(List<Integer> gruposUsuarioFiltrados) {
		this.gruposUsuarioFiltrados = gruposUsuarioFiltrados;
	}

}
