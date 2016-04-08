package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
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
