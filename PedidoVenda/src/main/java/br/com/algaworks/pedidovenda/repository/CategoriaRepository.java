package br.com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.algaworks.pedidovenda.model.Categoria;
import br.com.algaworks.pedidovenda.service.NegocioException;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class CategoriaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Categoria> listarCategoriaRaizes() {
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}

	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}
	
	public Categoria guardar(Categoria categoria){
		return manager.merge(categoria);
	}
	
	@Transactional
	public void remove(Categoria categoria){
		try {
			categoria = porId(categoria.getId());
			manager.remove(categoria);
			
			manager.flush();
			
		} catch (PersistenceException e) {
			throw new NegocioException("Categoria não pode ser exculuída!");
		}
	}
	
	public List<Categoria> subcategoriasDe(Categoria categoriaPai){
		return manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> filtradas(String descricaoCategoria) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);
		
		if(StringUtils.isNotBlank(descricaoCategoria)){
			criteria.add(Restrictions.ilike("descricao", descricaoCategoria, MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("descricao")).list();
	}

}
