package br.com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.algaworks.pedidovenda.model.Categoria;
import br.com.algaworks.pedidovenda.repository.CategoriaRepository;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class CategoriaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaRepository repository;

	@Transactional
	public Categoria salvar(Categoria categoria) {
		return repository.guardar(categoria);
	}
	
	
	
}
