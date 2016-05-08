package br.com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.algaworks.pedidovenda.model.Usuario;
import br.com.algaworks.pedidovenda.repository.UsuarioRepository;
import br.com.algaworks.pedidovenda.util.jpa.Transactional;

public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		return usuarioRepository.guardar(usuario);
	}
	
}
