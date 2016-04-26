package br.test.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import br.com.algaworks.pedidovenda.model.Cliente;
import br.com.algaworks.pedidovenda.model.Endereco;
import br.com.algaworks.pedidovenda.model.Grupo;
import br.com.algaworks.pedidovenda.model.TipoPessoa;
import br.com.algaworks.pedidovenda.model.Usuario;

public class ConexaoTeste {

	@Test
	public void deve_cadastrar_cliente_endereco() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager entityManager = factory.createEntityManager();

		EntityTransaction trx = entityManager.getTransaction();

		trx.begin();

		Cliente cliente = new Cliente();

		cliente.setNome("João das Couves2");
		cliente.setEmail("joao@coves.com.br");
		cliente.setDocumentoReceitaFederal("123.123.112-00");
		cliente.setTipo(TipoPessoa.FISICA);

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua Falcao");
		endereco.setNumero("123");
		endereco.setCidade("Marituba");
		endereco.setUf("PA");
		endereco.setCep("6606-160");
		endereco.setCliente(cliente);

		cliente.getEnderecos().add(endereco);

		entityManager.persist(cliente);

		trx.commit();

		Assert.assertEquals(1, 1);
	}

	@Test
	public void deve_cadastrar_usuario_grupo() throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Usuario usuario = new Usuario();
		usuario.setNome("Maria");
		usuario.setEmail("maria@abadia.com");
		usuario.setSenha("123");

		Grupo grupo = new Grupo();
		grupo.setNome("Vendedores");
		grupo.setDescricao("Vendedores da empresa");

		usuario.getGrupos().add(grupo);

		manager.persist(usuario);

		trx.commit();
	}

}
