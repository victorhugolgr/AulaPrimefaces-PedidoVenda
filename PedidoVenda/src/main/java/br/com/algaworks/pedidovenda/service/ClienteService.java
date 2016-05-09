package br.com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.repository.ClienteRepository;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class ClienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente){
		return clienteRepository.guardar(cliente);
	}
}
