package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CadastroUsuarioGrupoBean {

	private List<Integer>  gruposUsuarioFiltrados;

	public CadastroUsuarioGrupoBean(){
		setGruposUsuarioFiltrados(new ArrayList<>());
		for (int i = 0; i < 5; i++) {
			getGruposUsuarioFiltrados().add(i);
		}
	}

	public List<Integer> getGruposUsuarioFiltrados() {
		return gruposUsuarioFiltrados;
	}

	public void setGruposUsuarioFiltrados(List<Integer> gruposUsuarioFiltrados) {
		this.gruposUsuarioFiltrados = gruposUsuarioFiltrados;
	}


}
