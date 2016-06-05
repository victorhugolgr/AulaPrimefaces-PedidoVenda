package br.com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.algaworks.pedidovenda.model.Grupo;
import br.com.algaworks.pedidovenda.service.NegocioException;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class GrupoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Grupo> grupos(){
		return manager.createQuery("from Grupo",Grupo.class).getResultList();
	}
	
	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}

	@Transactional
	public void remover(Grupo grupo) {
		try {
			grupo = porId(grupo.getId());
			manager.remove(grupo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Grupo não pode ser excluído!");
		}
	}

}
