package br.com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.algaworks.pedidovenda.model.Categoria;

public class CategoriaRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Categoria> listarCategoriaRaizes(){
		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}
	
	public Categoria porId(Long id){
		return manager.find(Categoria.class, id);
	}

}
