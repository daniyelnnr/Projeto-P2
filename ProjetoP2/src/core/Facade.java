package core;

import exceptions.ErroAtualizacao;
import exceptions.ErroCadastro;
import exceptions.ErroLogin;

public class Facade {

	Controller controle = new Controller();
	Controller sistema = null;

	public void iniciaSistema() {
		sistema = controle;
	}

	public void fechaSistema() throws Exception {
		if (sistema.getUsuarioLogado() != null) {
			throw new Exception(
					"Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}

		else {
			this.sistema = null;
		}
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar)
			throws Exception {
		sistema.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario, imgAvatar);

		return emailUsuario;
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) throws Exception {
		try {
			sistema.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario,
					dataNasUsuario);
			return emailUsuario;
		} catch (Exception e) {
			throw new ErroCadastro(e.getMessage());
		}
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario)
			throws Exception {
		return sistema.getInfoUsuario(nomeInformacao, emailUsuario);

	}

	public void adicionaAmigo(String email) {
		sistema.adicionaAmigo(email);
	}

	public void removeAmigo(String email) {
		sistema.removeAmigo(email);
	}

	public void aceitaAmizade(String email) throws Exception {
		sistema.aceitaAmizade(email);
	}

	public void rejeitaAmizade(String email) throws Exception {
		sistema.rejeitaAmizade(email);
	}

	public int getNotificacoes() {
		return sistema.getNotificacao();

	}

	public int getQtdAmigos() {
		return sistema.getQtdAmigos();
	}

	public String getNextNotificacao() throws Exception {
		return sistema.getNextInformacao();
	}

	public String getInfoUsuario(String nomeInformacao) throws Exception {
		return sistema.getInfoUsuarioLogado(nomeInformacao);
	}

	public void login(String emailUsuario, String senhaUsuario)
			throws Exception {
		try {
			sistema.login(emailUsuario, senhaUsuario);
		} catch (Exception e) {
			throw new ErroLogin(e.getMessage());
		}
	}

	public void logout() throws Exception {
		sistema.logout();

	}

	public void removeUsuario(String email) {
		sistema.removeUsuario(email);
	}

	public String getNome(String email) throws Exception {
		String nomeUsuario = sistema.getNome(email);
		if (nomeUsuario == null) {
			throw new Exception("O usuario com email " + email
					+ " nao esta cadastrado.");
		} else {
			return nomeUsuario;
		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor)
			throws Exception {
		if (sistema.getUsuarioLogado() == null) {
			throw new Exception(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
		try {
			sistema.atualizaPerfil(nomeInformacao, valor);
		} catch (Exception e) {
			throw new ErroAtualizacao(e.getMessage());
		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor,
			String velhaSenha) throws Exception {
		if (sistema.getUsuarioLogado() == null) {
			throw new Exception(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
		try {
			sistema.atualizaPerfil(nomeInformacao, valor, velhaSenha);
		} catch (Exception e) {
			throw new ErroAtualizacao(e.getMessage());
		}
	}

	public Usuario buscaUsuario(String emailUsuario) throws Exception {
		return sistema.buscaUsuario(emailUsuario);

	}

	public void criaPost(String conteudo, String data) throws Exception {
		sistema.postarMensagem(conteudo, data);
	}

	public String getPost(int indice) throws Exception {
		return sistema.getPost(indice);
	}

	public String getPost(String atributo, int indice) throws Exception {
		return sistema.getPost(atributo, indice);
	}

	public void curtirPost(String emailAmigo, int indicePost) throws Exception {
		sistema.curtirPost(emailAmigo, indicePost);
	}

}
