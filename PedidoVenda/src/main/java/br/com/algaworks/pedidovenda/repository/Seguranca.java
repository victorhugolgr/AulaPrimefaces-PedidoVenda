package br.com.algaworks.pedidovenda.repository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.algaworks.pedidovenda.security.UsuarioSistema;

@Named
@RequestScoped
public class Seguranca {

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}

		return nome;
	}

	private UsuarioSistema getUsuarioLogado() {

		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if(auth != null && auth.getPrincipal() != null){
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}

}
