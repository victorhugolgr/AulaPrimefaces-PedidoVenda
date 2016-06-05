package br.com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.TransactionException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.repository.filter.ClienteFilter;
import br.com.algaworks.pedidovenda.service.NegocioException;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class ClienteRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Cliente guardar(Cliente cliente){
		return manager.merge(cliente);
	}
	
	public Cliente porId(Long id){
		return manager.find(Cliente.class, id);
	}
	
	@Transactional
	public void remove(Cliente cliente){
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (TransactionException e) {
			throw new NegocioException("Cliente não pode ser excluído!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter clienteFilter){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if(StringUtils.isNotBlank(clienteFilter.getNome())){
			criteria.add(Restrictions.ilike("nome", clienteFilter.getNome(),MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(clienteFilter.getCnpjCpf())){
			criteria.add(Restrictions.eq("documentoReceitaFederal", clienteFilter.getCnpjCpf()));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public List<Cliente> porNome(String nome){
		return this.manager
				.createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
	
}
