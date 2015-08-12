package core;

import java.util.ArrayList;

public class Controller {

	private Validadores validadores = new Validadores();
	private Usuario usuarioLogado = null;
	BancoDeDados bancodedados = new BancoDeDados();

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar)
			throws Exception {
		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (validadores.validaData(dataNasUsuario)
				&& validadores.validaEmail(emailUsuario)) {
			validadores.validaData(dataNasUsuario);
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario, imgAvatar);
			bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}

	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) throws Exception {

		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (validadores.validaData(dataNasUsuario)
				&& validadores.validaEmail(emailUsuario)) {
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario);
			bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}

	}

	public void removeUsuario(String email) {
		this.bancodedados.removeUsuario(email);
	}

	public boolean login(String emailUsuario, String senhaUsuario)
			throws Exception {
		if (this.usuarioLogado == null) {
			Usuario user = this.bancodedados.buscaUsuario(emailUsuario);
			if (user == null) {
				throw new Exception("Um usuarix com email " + emailUsuario
						+ " nao esta cadastradx.");
			}

			else if (user.getSenha().equals(senhaUsuario)) {
				usuarioLogado = user;
				return true;
			}

			else {
				throw new Exception("Senha invalida.");
			}
		} else {
			throw new Exception("Um usuarix ja esta logadx: "
					+ this.usuarioLogado.getNome() + ".");
		}
	}

	public void logout() throws Exception {
		if (this.usuarioLogado != null) {
			this.usuarioLogado = null;
		} else {
			throw new Exception(
					"Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		}
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario)
			throws Exception {
		return this.bancodedados.getInfoUsuario(nomeInformacao, emailUsuario);

	}

	public String getInfoUsuarioLogado(String nomeInformacao) throws Exception {
		return this.bancodedados.getInfoUsuario(nomeInformacao,
				this.usuarioLogado.getEmail());

	}

	public void aceitaAmizade(String email) throws Exception {
		Usuario usuario = this.bancodedados.buscaUsuario(email);
		this.usuarioLogado.aceitaAmizade(usuario);
		usuario.adicionaAmigo(usuarioLogado);
		usuario.notificacoes.add(this.usuarioLogado.getNome()
				+ " aceitou sua amizade.");
	}

	public void adicionaAmigo(String email) {
		Usuario usuario = this.bancodedados.buscaUsuario(email);
		usuario.notificacoes.add(this.usuarioLogado.getNome()
				+ " quer sua amizade.");
		usuario.pedidosAmizade.add(this.usuarioLogado);
	}

	public void removeAmigo(String email) {
		Usuario usuario = this.bancodedados.buscaUsuario(email);
		usuario.notificacoes.add(this.usuarioLogado.getNome()
				+ " removeu a sua amizade.");
		usuario.amigos.remove(this.usuarioLogado);
		this.usuarioLogado.removeAmigo(usuario);
	}

	public void rejeitaAmizade(String email) throws Exception {
		Usuario usuario = this.bancodedados.buscaUsuario(email);
		if (usuario == null) {
			throw new Exception("O usuario " + email
					+ " nao esta cadastrado no +pop.");
		}
		this.usuarioLogado.rejeitaAmizade(usuario);
		usuario.notificacoes.add(this.usuarioLogado.getNome()
				+ " rejeitou sua amizade.");

	}

	public int getNotificacao() {
		return this.usuarioLogado.notificacoes.getNotificacoes();
	}

	public String getNextInformacao() throws Exception {
		return this.usuarioLogado.notificacoes.getNextNotificacao();
	}

	public void curtirPost(String emailAmigo, int indice) throws Exception {
		Postagem postagem = this.usuarioLogado.getPostagemAmigo(emailAmigo,
				indice);
		postagem.setNewLikes();

		Usuario usuarioAmigo = this.bancodedados.buscaUsuario(emailAmigo);

		usuarioAmigo.notificacoes.add(this.usuarioLogado.getNome()
				+ " curtiu seu post de " + postagem.getData() + ".");

	}

	public void atualizaPerfil(String nomeInformacao, String valor)
			throws Exception {

		if (usuarioLogado == null) {
			throw new Exception("Nenhum usuarix esta logadx no +pop.");
		}

		if (nomeInformacao.equalsIgnoreCase("Nome")) {
			if (valor.equals("")) {
				throw new Exception("Nome dx usuarix nao pode ser vazio.");
			}
			this.usuarioLogado.setNome(valor);
		} else if (nomeInformacao.equalsIgnoreCase("foto")) {
			this.usuarioLogado.setFoto(valor);
		} else if (nomeInformacao.equalsIgnoreCase("E-mail")) {
			if (validadores.validaEmail(valor) == false) {
				throw new Exception("Formato de e-mail esta invalido.");
			}
			this.usuarioLogado.setEmail(valor);
		} else if (nomeInformacao.equalsIgnoreCase("Data de Nascimento")) {
			validadores.validaData(valor);
			this.usuarioLogado.setData(valor);
		} else {
			throw new Exception();
		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor,
			String velhaSenha) throws Exception {
		if ((nomeInformacao.equalsIgnoreCase("Senha"))
				&& (this.usuarioLogado.getSenha().equals(velhaSenha))) {
			this.usuarioLogado.setSenha(valor);
		} else {
			throw new Exception("A senha fornecida esta incorreta.");
		}
	}

	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public void postarMensagem(String conteudo, String data) throws Exception {
		// olhar isso mais tarde de usuarioLogado
		if (this.usuarioLogado == null) {
			throw new Exception(
					"Nao eh possivel postar mensagem. Nenhum usuario esta logado no +pop.");
		}
		int index = conteudo.indexOf("#");
		String msg = conteudo.substring(0, index - 1);
		if (msg.length() >= 200)
			throw new Exception(
					"Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		String resto = conteudo.substring(index, conteudo.length());
		ArrayList<String> hashtags = new ArrayList<String>();
		for (String hashtag : resto.split(" ")) {
			if (!hashtag.startsWith("#"))
				throw new Exception(
						"Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '"
								+ hashtag + "'.");
			hashtags.add(hashtag);
		}
		Postagem novaPostagem = new Postagem(msg, hashtags, data);
		this.usuarioLogado.mural.add(novaPostagem);
	}

	public String getPost(int indice) throws Exception {
		return this.usuarioLogado.getMural().get(indice).getMensagem() + " "
				+ this.usuarioLogado.getMural().get(indice).getTags() + " ("
				+ this.usuarioLogado.getMural().get(indice).getData() + ")";

	}

	public String getPost(String atributo, int indice) throws Exception {
		String mensagemRequerida = "";

		if (atributo.equalsIgnoreCase("conteudo")) {
			mensagemRequerida = this.usuarioLogado.mural.get(indice)
					.getMensagem();
		}

		else if (atributo.equalsIgnoreCase("data")) {
			mensagemRequerida = this.usuarioLogado.mural.get(indice).getData();
		} else {
			mensagemRequerida = this.usuarioLogado.mural.get(indice)
					.getTagsToString();

		}

		return mensagemRequerida;
	}

	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}

	public String getConteudo(int indice, int post) throws Exception {
		return this.usuarioLogado.getMural().get(post).getConteudo(indice);
	}

}
