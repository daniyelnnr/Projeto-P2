package core;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class Testes {

	@Test
	public void testCadastroUsuarios() {
		try {
			Facade facade = new Facade();
			// Rever metodos duplicados para cadastraUsuario;
			String id1 = facade.cadastraUsuario("Fatima Bernardes",
					"fatima@email.com.br", "will_S2", "17/09/1962",
					"resources/fatima.jpg");
			String id2 = facade.cadastraUsuario("Madonna", "madonna@email.com",
					"iamawesome", "16/08/1958", "resources/madonna.jpg");
			String id3 = facade.cadastraUsuario("Jo Soares", "jo@uol.com.br",
					"!soares!", "16/01/1938");
			// cadastraUsuario retorna uma String: o email do usuario
			// cadastraUsuario com duas formas: com e sem img do avatar.

			Assert.assertEquals("Fatima Bernardes", facade.buscaUsuario(id1)
					.getNome());
			// buscaUsuario procura um usuario pelo seu email
			Assert.assertEquals("Madonna", facade.buscaUsuario(id2).getNome());
			Assert.assertEquals("Jo Soares", facade.buscaUsuario(id3).getNome());

			facade.buscaUsuario("alguem@email.com").getNome();
			// vai gerar um Exception pois nao ha usuario cadastrado com esse
			// email

		} catch (Exception e) {
			Assert.assertEquals(
					"Nao foi possivel realizar login. O usuario com email alguem@email.com nao esta cadastrado.",
					e.getMessage());
		}
	}

	@Test
	public void testLoginUsuarios() {
		try {
			Facade facade = new Facade();
			
			facade.login("fatima@email.com.br", "will_S2");
			//metodo void, nao ha necessidade de retorno
			
			//um usuario esta logado no momento
			Assert.assertEquals("Fatima Bernardes", facade.getInfoUsuarioLogado("Nome"));
			//internamente vai haver um if com o que ele pode ou nao chamar
			//ex: "nome" vai retornar o usuario.getNome()
			
			Assert.assertEquals("resources/fatima.jpg", facade.getInfoUsuarioLogado("Foto"));
			
		} catch (Exception e) {
			Assert.fail();
		}

	}
}
