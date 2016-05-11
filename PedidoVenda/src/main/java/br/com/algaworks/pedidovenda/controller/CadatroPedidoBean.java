package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.model.EnderecoEntrega;
import br.com.algaworks.pedidovenda.model.FormaPagamento;
import br.com.algaworks.pedidovenda.model.Pedido;
import br.com.algaworks.pedidovenda.model.Usuario;
import br.com.algaworks.pedidovenda.repository.ClienteRepository;
import br.com.algaworks.pedidovenda.repository.UsuarioRepository;
import br.com.algaworks.pedidovenda.service.CadastroPedidoService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadatroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;
	@Inject
	private ClienteRepository clienteRepository;
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	private Pedido pedido;
	private List<Usuario> vendedores;

	@PostConstruct
	public void init(){
		if(FacesUtil.isNotPostBack()){
			vendedores = usuarioRepository.vendedores();
		}
	}
	
	public CadatroPedidoBean() {
		limpar();
	}

	public void salvar(){
		this.pedido = this.cadastroPedidoService.salvar(pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}
	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public List<Cliente> completarCliente(String nome){
		return clienteRepository.porNome(nome);
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public FormaPagamento[] getFormasPagamento(){
		return FormaPagamento.values();
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Usuario> vendedores) {
		this.vendedores = vendedores;
	}

}
