package br.com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.model.EnderecoEntrega;
import br.com.algaworks.pedidovenda.model.FormaPagamento;
import br.com.algaworks.pedidovenda.model.ItemPedido;
import br.com.algaworks.pedidovenda.model.Pedido;
import br.com.algaworks.pedidovenda.model.Produto;
import br.com.algaworks.pedidovenda.model.Usuario;
import br.com.algaworks.pedidovenda.repository.ClienteRepository;
import br.com.algaworks.pedidovenda.repository.ProdutoRepository;
import br.com.algaworks.pedidovenda.repository.UsuarioRepository;
import br.com.algaworks.pedidovenda.service.CadastroPedidoService;
import br.com.algaworks.pedidovenda.util.jsf.FacesUtil;
import br.com.algaworks.pedidovenda.validation.SKU;

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
	@Inject
	private ProdutoRepository produtoRepository;
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	private Produto produtoLinhaEditavel;
	private String sku;

	@PostConstruct
	public void init(){
		if(FacesUtil.isNotPostBack()){
			vendedores = usuarioRepository.vendedores();
			
			this.pedido.adicionarItemVazio();
			
			this.recalcularPedido();
		}
	}
	
	public CadatroPedidoBean() {
		limpar();
	}

	public void salvar(){
		this.pedido.removerItemVazio();
		
		try{
			this.pedido = this.cadastroPedidoService.salvar(this.pedido);
			FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
		}finally{
			this.pedido.adicionarItemVazio();
		}		
		
	}
	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public List<Produto> completarProduto(String nome){
		return this.produtoRepository.porNome(nome);
	}
	
	public void carregarProdutoLinhaEditavel(){
		ItemPedido item = this.pedido.getItens().get(0);
		
		if(this.produtoLinhaEditavel != null){
			if(this.existeItemComProduto(this.produtoLinhaEditavel)){
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			}else {				
				item.setProduto(produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				this.pedido.recalcularValorTotal();
			}
		}
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for(ItemPedido item : this.getPedido().getItens()){
			if(produto.equals(item.getProduto())){
				existeItem = true;
				break;
			}
		}
		
		return false;
	}

	public void carregarProdutoPorSku(){
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtoRepository.proSku(sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	public List<Cliente> completarCliente(String nome){
		return clienteRepository.porNome(nome);
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha){
		if(item.getQuantidade() < 1){
			if(linha == 0){
				item.setQuantidade(1);
			}else{
				this.pedido.getItens().remove(linha);
			}
		}
			
		this.pedido.recalcularValorTotal();
	}
	
	public void recalcularPedido(){		
		if(this.pedido != null){
			this.pedido.recalcularValorTotal();
		}
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
	
	public boolean isEditando(){
		return this.pedido.getId() != null;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}
	
	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}
