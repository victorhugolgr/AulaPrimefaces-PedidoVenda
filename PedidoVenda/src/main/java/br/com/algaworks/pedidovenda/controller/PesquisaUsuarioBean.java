package br.com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PesquisaUsuarioBean {

	private List<Integer>  usuariosFiltrados;

	public PesquisaUsuarioBean(){
		usuariosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			usuariosFiltrados.add(i);
		}
	}
	
	public List<Integer> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
	
}
