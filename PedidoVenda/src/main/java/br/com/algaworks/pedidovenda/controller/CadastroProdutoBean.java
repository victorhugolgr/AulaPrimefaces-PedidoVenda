package br.com.algaworks.pedidovenda.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.service.NegocioException;

@Named
@RequestScoped
public class CadastroProdutoBean {

	public void salvar(){
		throw new NegocioException("Erro teste não implementado");
	}
	
}
