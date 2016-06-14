package br.com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.TransactionException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.algaworks.pedidovenda.model.Grupo;
import br.com.algaworks.pedidovenda.model.Usuario;
import br.com.algaworks.pedidovenda.service.NegocioException;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	@Transactional
	public void remove(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (TransactionException e) {
			throw new NegocioException("Usuário não pode ser excluído!");
		}
	}

	public List<Grupo> grupos(Usuario usuario) {
		List<Grupo> gruposUsuario;
		try {
			gruposUsuario = (ArrayList<Grupo>) manager.createQuery("from Grupo where Usuario = :usuario", Grupo.class)
					.setParameter("usuario", usuario).getResultList();
			return gruposUsuario;
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(String nomeFiltro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (StringUtils.isNotBlank(nomeFiltro)) {
			criteria.add(Restrictions.ilike("nome", nomeFiltro, MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public List<Usuario> vendedores() {
		return this.manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;

		try{			
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		}catch(NoResultException e){
			//nenum usuário encontrado com o e-mail informado.
		}

		return usuario;
	}
}
