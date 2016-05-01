package br.com.algaworks.pedidovenda.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.algaworks.pedidovenda.model.Produto;

public class ProdutoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	public Produto guardar(Produto produto) {
		return entityManager.merge(produto);
	}

	public Produto proSku(String sku) {
		try{			
			return entityManager.createQuery("from Produto where upper(sku) = :sku",Produto.class)
					.setParameter("sku", sku.toUpperCase())
					.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}


}
