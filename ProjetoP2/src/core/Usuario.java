package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuario implements Comparable<Usuario> {

	private String nome;
	private String email;
	private String senha;
	private String dataNasc;
	private String imgAvatar;
	private int pops;
	private ITipoDeUsuario tiposStrategy;

	Notificacoes notificacoes = new Notificacoes();
	FeedNoticias feedNoticias = new FeedNoticias();
	HashMap<String, String> HistoricoUsuario = new HashMap<>();
	ArrayList<Postagem> mural = new ArrayList<Postagem>();
	ArrayList<Usuario> amigos = new ArrayList<Usuario>();
	ArrayList<Usuario> pedidosAmizade = new ArrayList<Usuario>();

	public Usuario(String nome, String email, String senha, String dataNasc, String imgAvatar) {

		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.imgAvatar = imgAvatar;
		this.dataNasc = dataNasc;
		this.tiposStrategy = new UsuarioNormal();
	}

	public Usuario(String nome, String email, String senha, String dataNasc) throws Exception {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.imgAvatar = "resources/default.jpg";
	}

	public void atribuirPontos(int valor) {
		pops += valor;
		this.tiposStrategy = TipoDeUsuarioFactory.getInstance().createTipoDeUsuarioStrategy(getPops());
	}

	public boolean aceitaAmizade(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if (usuario.getEmail().equals(amigo.getEmail())) {
				this.amigos.add(usuario);
				return true;

			}
		}
		throw new Exception(amigo.getNome() + " nao lhe enviou solicitacoes de amizade.");
	}

	public boolean rejeitaAmizade(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if (usuario.getEmail().equals(amigo.getEmail())) {
				this.amigos.remove(usuario);
				return true;

			}
		}
		throw new Exception(amigo.getNome() + " nao lhe enviou solicitacoes de amizade.");
	}

	public Postagem getPostagemAmigo(String emailAmigo, int indicePost) {
		Postagem postagemRequerida = null;

		for (Usuario usuario : amigos) {
			if (usuario.getEmail().equals(emailAmigo)) {
				postagemRequerida = usuario.mural.get(indicePost);
			}
		}

		return postagemRequerida;

	}

	public void atualizaPerfil(AuxiliarValidadores validadores, String nomeInformacao, String valor) throws Exception {
		validadores.validarUsuarioLogado(this, "");

		if (nomeInformacao.equalsIgnoreCase("Nome")) {
			if (valor.equals("")) {
				throw new Exception("Nome dx usuarix nao pode ser vazio.");
			}
			setNome(valor);
		} else if (nomeInformacao.equalsIgnoreCase("foto")) {
			setFoto(valor);
		} else if (nomeInformacao.equalsIgnoreCase("E-mail")) {
			if (validadores.validaEmail(valor) == false) {
				throw new Exception("Formato de e-mail esta invalido.");
			}
			setEmail(valor);
		} else if (nomeInformacao.equalsIgnoreCase("Data de Nascimento")) {
			validadores.validaData(valor);
			setData(valor);
		} else {
			throw new Exception();
		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor, String velhaSenha) throws Exception {
		if ((nomeInformacao.equalsIgnoreCase("Senha")) && (getSenha().equals(velhaSenha))) {
			setSenha(valor);
		} else {
			throw new Exception("A senha fornecida esta incorreta.");
		}
	}

	public void postarMensagem(String conteudo, String data, ArrayList<String> hashtags) throws Exception {
		int index = conteudo.length() - 1;
		if (conteudo.contains("#"))
			index = conteudo.indexOf("#");
		if (conteudo.indexOf("#") == 0) {
			index++;
		}

		String msg = conteudo.substring(0, index - 1);
		if (msg.length() >= 200)
			throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		Postagem novaPostagem = new Postagem(msg, hashtags, data);
		mural.add(novaPostagem);
		adicionaFeedDosAmigos(this.amigos, novaPostagem);
	}

	private void adicionaFeedDosAmigos(ArrayList<Usuario> amigos, Postagem novaPostagem) {
		for (Usuario usuario : amigos) {
			usuario.adicionaAoFeed(novaPostagem, this.tiposStrategy);
		}
	}

	private void adicionaAoFeed(Postagem novaPostagem, ITipoDeUsuario tipoDeUsuario) {
		this.feedNoticias.adicionaPostagem(novaPostagem, tipoDeUsuario);
	}

	public void postagemEmHistorico(Postagem postagem) {
		// vai pegar a postagem transformar em historico e adicionala ao
		// dicionario
	}

	public String getPost(int indice) throws Exception {
		return getMural().get(indice).getMensagem() + " " + getMural().get(indice).getTagsEspaco() + " ("
				+ getMural().get(indice).getData() + ")";
	}

	public String getPost(String atributo, int indice) throws Exception {
		String mensagemRequerida = "";

		if (atributo.equalsIgnoreCase("conteudo")) {
			mensagemRequerida = mural.get(indice).getMensagem();
		}

		else if (atributo.equalsIgnoreCase("data")) {
			mensagemRequerida = mural.get(indice).getData();
		} else if (atributo.equalsIgnoreCase("hashtags")) {
			mensagemRequerida = mural.get(indice).getTagsVirgula();
		}

		return mensagemRequerida;
	}

	public void curtirPost(Usuario usuarioAmigo, String emailAmigo, int indice) throws Exception {
		Postagem postagem = getPostagemAmigo(emailAmigo, indice);
		postagem.setNewLikes();
		usuarioAmigo.notificacoes.add(getNome() + " curtiu seu post de " + postagem.getData() + ".");
		this.tiposStrategy.curtir(usuarioAmigo, postagem);

	}

	public void descurtirPost(Usuario usuarioAmigo, String emailAmigo, int indice) throws Exception {
		Postagem postagem = getPostagemAmigo(emailAmigo, indice);
		postagem.setNewDeslikes();
		usuarioAmigo.notificacoes.add(getNome() + " curtiu seu post de " + postagem.getData() + ".");
		this.tiposStrategy.descurtir(usuarioAmigo, postagem);

	}

	public Postagem getPostagem(int indice) throws Exception {
		if (indice > this.getMural().size()) {
			throw new Exception(String.format("Post #%d nao existe. Usuarix possui apenas %d post(s).", indice,
					this.getMural().size()));
		}
		return this.getMural().get(indice);
	}

	@Override
	public int compareTo(Usuario outroUsuario) {
		if (outroUsuario.getPops() < this.getPops()) {
			return -1;
		}
		if (outroUsuario.getPops() > this.getPops()) {
			return 1;
		}
		if (outroUsuario.getPops() == this.getPops()) {
			return outroUsuario.getNome().compareTo(this.getNome());
		} else {
			return 0;
		}
	}

	public int getQtdAmigos() {
		return this.amigos.size();
	}

	public String getPopularidade() {
		return this.tiposStrategy.getTipoPopularidade();
	}

	public String getFoto() {
		return imgAvatar;
	}

	public ArrayList<Postagem> getMural() {
		return mural;
	}

	public void setMural(ArrayList<Postagem> mural) {
		this.mural = mural;
	}

	public ArrayList<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(ArrayList<Usuario> amigos) {
		this.amigos = amigos;
	}

	public void adicionaAmigo(Usuario amigo) {
		this.amigos.add(amigo);
	}

	public void removeAmigo(Usuario amigo) {
		this.amigos.remove(amigo);
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getData() {
		return dataNasc;
	}

	public int getPops() {
		return this.pops;
	}

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}

	public void setFoto(String novaFoto) {
		this.imgAvatar = novaFoto;
	}

	public void setData(String novaData) {
		this.dataNasc = novaData;
	}

	public Usuario getAmigo(int indice) {
		return amigos.get(indice);
	}

}
