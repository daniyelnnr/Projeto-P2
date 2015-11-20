package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

/**
 * Projeto LP2 - 2014.2
 * 
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 *         Classe que representa o usuario da rede social.
 *
 */
public class Usuario implements Comparable<Usuario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	private String senha;
	private String dataNasc;
	private String imgAvatar;
	private int pops;
	private ITipoDeUsuario tiposStrategy;

	Notificacoes notificacoes = new Notificacoes();
	FeedNoticias feedNoticias;
	HashMap<String, String> HistoricoUsuario = new HashMap<>();
	ArrayList<Postagem> mural = new ArrayList<Postagem>();
	ArrayList<Usuario> amigos = new ArrayList<Usuario>();
	ArrayList<Usuario> pedidosAmizade = new ArrayList<Usuario>();

	public Usuario(String nome, String email, String senha, String dataNasc,
			String imgAvatar) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.imgAvatar = imgAvatar;
		this.dataNasc = dataNasc;
		this.tiposStrategy = new UsuarioNormal();
		this.feedNoticias = new FeedNoticias();
	}

	public Usuario(String nome, String email, String senha, String dataNasc)
			throws Exception {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.imgAvatar = "resources/default.jpg";
	}

	/**
	 * Metodo responsavel por permitir que o usuario aceite solicitações de
	 * amizade.
	 * 
	 * @param amigo
	 * @return
	 * @throws Exception
	 */
	public boolean aceitaAmizade(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if (usuario.getEmail().equals(amigo.getEmail())) {
				this.amigos.add(usuario);
				return true;

			}
		}
		throw new Exception(amigo.getNome()
				+ " nao lhe enviou solicitacoes de amizade.");
	}

	/**
	 * Metodo responsavel por permitir que o usuario rejeite solicitações de
	 * amizade.
	 * 
	 * @param amigo
	 * @return
	 * @throws Exception
	 */
	public boolean rejeitaAmizade(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if (usuario.getEmail().equals(amigo.getEmail())) {
				this.amigos.remove(usuario);
				return true;

			}
		}
		throw new Exception(amigo.getNome()
				+ " nao lhe enviou solicitacoes de amizade.");
	}

	/**
	 * Metodo que retorna um post especifico de um amigo do proprio usuario.
	 * 
	 * @param emailAmigo
	 * @param indicePost
	 * @return
	 */
	public Postagem getPostagemAmigo(String emailAmigo, int indicePost) {
		Postagem postagemRequerida = null;

		for (Usuario usuario : amigos) {
			if (usuario.getEmail().equals(emailAmigo)) {
				postagemRequerida = usuario.mural.get(indicePost);
			}
		}

		return postagemRequerida;

	}

	/**
	 * Metodo que atualiza as informacoes do usuario, passando um avatar de
	 * imagem.
	 * 
	 * @param validadores
	 * @param nomeInformacao
	 * @param valor
	 * @throws Exception
	 */
	public void atualizaPerfil(AuxiliarValidadores validadores,
			String nomeInformacao, String valor) throws Exception {
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

	/**
	 * Metodo que atualiza as informacoes do usuario, sem passar um avatar de
	 * imagem.
	 * 
	 * @param nomeInformacao
	 * @param valor
	 * @param velhaSenha
	 * @throws Exception
	 */
	public void atualizaPerfil(String nomeInformacao, String valor,
			String velhaSenha) throws Exception {
		if ((nomeInformacao.equalsIgnoreCase("Senha"))
				&& (getSenha().equals(velhaSenha))) {
			setSenha(valor);
		} else {
			throw new Exception("A senha fornecida esta incorreta.");
		}
	}

	/**
	 * Metodo que permite o usuario postar mensagem.
	 * 
	 * @param conteudo
	 * @param data
	 * @param hashtags
	 * @throws Exception
	 */
	public void postarMensagem(String conteudo, String data,
			ArrayList<String> hashtags) throws Exception {
		int index = conteudo.length() - 1;
		if (conteudo.contains("#"))
			index = conteudo.indexOf("#");
		if (conteudo.indexOf("#") == 0) {
			index++;
		}

		String msg = conteudo.substring(0, index - 1);
		if (!conteudo.contains("#") && !conteudo.contains("</"))
			msg = conteudo;
		if (msg.length() >= 200)
			throw new Exception(
					"Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		Postagem novaPostagem = new Postagem(msg, hashtags, data);
		mural.add(novaPostagem);
	}

	/**
	 * Retorna um post especifico.
	 * 
	 * @param indice
	 * @return
	 * @throws Exception
	 */
	public String getPost(int indice) throws Exception {
		return getMural().get(indice).getMensagem() + " "
				+ getMural().get(indice).getTagsEspaco() + " ("
				+ getMural().get(indice).getData() + ")";
	}


	/**
	 * Retorna uma parte especifica do post.
	 * 
	 * @param atributo
	 * @param indice
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Permite o usuario curtir posts de amigos.
	 * 
	 * @param usuarioAmigo
	 * @param emailAmigo
	 * @param indice
	 * @throws Exception
	 */
	public void curtirPost(Usuario usuarioAmigo, String emailAmigo, int indice)
			throws Exception {
		Postagem postagem = getPostagemAmigo(emailAmigo, indice);
		postagem.setNewLikes();
		usuarioAmigo.notificacoes.add(getNome() + " curtiu seu post de "
				+ postagem.getData() + ".");
		this.tiposStrategy.curtir(usuarioAmigo, postagem);

	}

	/**
	 * Permite o usuario descurtir posts de amigos.
	 * 
	 * @param usuarioAmigo
	 * @param emailAmigo
	 * @param indice
	 * @throws Exception
	 */
	public void descurtirPost(Usuario usuarioAmigo, String emailAmigo,
			int indice) throws Exception {
		Postagem postagem = getPostagemAmigo(emailAmigo, indice);
		postagem.setNewDeslikes();
		usuarioAmigo.notificacoes.add(getNome() + " curtiu seu post de "
				+ postagem.getData() + ".");
		this.tiposStrategy.descurtir(usuarioAmigo, postagem);

	}

	/**
	 * Retorna uma mensagem do mural.
	 * 
	 * @param indice
	 * @return
	 * @throws Exception
	 */
	public Postagem getPostagem(int indice) throws Exception {
		if (indice > this.getMural().size()) {
			throw new Exception(String.format(
					"Post #%d nao existe. Usuarix possui apenas %d post(s).",
					indice, this.getMural().size()));
		}
		return this.getMural().get(indice);
	}

	/**
	 * Exporta as postagens para arquivos.
	 * 
	 * @throws Exception
	 */
	public void exportaPostagem() throws Exception {
		File destFile = new File("arquivos/");
		if (!destFile.exists()) {
			destFile.mkdir();
		}

		if (this.mural.size() == 0) {
			throw new Exception(
					"Erro ao baixar posts. O usuario nao possui posts.");
		}
		String export = "";
		export = formataSaidaDoBaixarPost(export);
		String arquivo = "arquivos/posts_" + this.email + ".txt";
		FileWriter fw = new FileWriter(arquivo, false);
		BufferedWriter out = new BufferedWriter(fw);
		try {
			out.write(export);
		} finally {
			out.close();
		}

	}

	private String formataSaidaDoBaixarPost(String export) throws Exception {
		for (int indiceDoPost = 0; indiceDoPost < this.mural.size(); indiceDoPost++) {

			Postagem postagem = getMural().get(indiceDoPost);
			export += (String.format("Post #%d - %s \n", indiceDoPost + 1,
					postagem.getDataOutroFormato()));
			export += "Conteudo:\n";
			if (postagem.getMensagem().contains("</")) {
				export += String.format("%s\n", postagem.getMensagem()
						.substring(0, postagem.getMensagem().indexOf("<")));
			} else {
				export += String.format("%s\n", postagem.getMensagem());
			}
			if (!postagem.getConteudo().isEmpty()) {
				String conteudo = postagem.getMensagem().substring(
						postagem.getMensagem().indexOf("<"),
						postagem.getMensagem().lastIndexOf(">") + 1);

				for (String string : conteudo.split(" ")) {
					export += String.format("%s\n", string);
				}

			}

			for (String tag : postagem.getArrayTags()) {
				export += tag;
				export += " ";
			}

			if (!postagem.getArrayTags().isEmpty())
				export += "\n";

			if (indiceDoPost == this.mural.size() - 1) {
				export += String.format("+Pop: %d", postagem.getPops());
			} else {
				export += String.format("+Pop: %d\n\n\n", postagem.getPops());
			}

		}
		return export;
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

	/**
	 * Retorna o total de posts do usuario.
	 * 
	 * @return
	 */
	public int getTotalPosts() {
		int totalPosts = mural.size();
		return totalPosts;
	}

	/**
	 * Metodo responsavel por atribuir pontos de popularidade.
	 * 
	 * @param valor
	 */
	public void atribuirPontos(int valor) {
		pops += valor;
		this.tiposStrategy = TipoDeUsuarioFactory.getInstance()
				.createTipoDeUsuarioStrategy(getPops());
	}

	/**
	 * Metodo responsavel por retornar a quantidade de amigos.
	 * 
	 * @return
	 */
	public int getQtdAmigos() {
		return this.amigos.size();
	}

	/**
	 * Metodo que retorna a popularidade do usuario.
	 * 
	 * @return
	 */
	public String getPopularidade() {
		return this.tiposStrategy.getTipoPopularidade();
	}

	/**
	 * Metodo que retorna a foto do usario.
	 * 
	 * @return
	 */
	public String getFoto() {
		return imgAvatar;
	}

	/**
	 * Metodo responsavel por retornar o mural do usuario.
	 * 
	 * @return
	 */
	public ArrayList<Postagem> getMural() {
		return mural;
	}

	/**
	 * Metodo responsavel por aplicar um novo valor ao mural do usuario.
	 * 
	 * @param mural
	 */
	public void setMural(ArrayList<Postagem> mural) {
		this.mural = mural;
	}

	/**
	 * Metodo que retorno a lista de amigos de um usuario.
	 * 
	 * @return
	 */
	public ArrayList<Usuario> getAmigos() {
		return amigos;
	}

	/**
	 * Aplica novos valores a lista de amigos de um usuario.
	 * 
	 * @param amigos
	 */
	public void setAmigos(ArrayList<Usuario> amigos) {
		this.amigos = amigos;
	}

	/**
	 * Permite que o usuario adicione amigo.
	 * 
	 * @param amigo
	 */
	public void adicionaAmigo(Usuario amigo) {
		this.amigos.add(amigo);
	}

	/**
	 * Permite que o usuario remova amigos.
	 * 
	 * @param amigo
	 */
	public void removeAmigo(Usuario amigo) {
		this.amigos.remove(amigo);
	}

	/**
	 * Retorna o email do Usuario.
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Retorna a senha do usuario.
	 * 
	 * @return
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Retorna o nome do Usuario.
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna a data de nascimento do Usuario.
	 * 
	 * @return
	 */
	public String getData() {
		return dataNasc;
	}

	/**
	 * Retorna os pontos de popularidade do usuario.
	 * 
	 * @return
	 */
	public int getPops() {
		return this.pops;
	}

	/**
	 * Atribui um novo valor de senha para o usuario.
	 * 
	 * @param novaSenha
	 */
	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	/**
	 * Atribui um novo nome para o usuario.
	 * 
	 * @param novoNome
	 */
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	/**
	 * Atribui um novo valor de nome para o usuario.
	 * 
	 * @param novoEmail
	 */
	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}

	/**
	 * Atribui um novo valor de foto para o usuario.
	 * 
	 * @param novaFoto
	 */
	public void setFoto(String novaFoto) {
		this.imgAvatar = novaFoto;
	}

	/**
	 * Atribui um novo valor de data de nascimento para o usuario.
	 * 
	 * @param novaData
	 */
	public void setData(String novaData) {
		this.dataNasc = novaData;
	}

	/**
	 * Retorna a quantidade de amigos do usuario.
	 * 
	 * @param indice
	 * @return
	 */
	public Usuario getAmigo(int indice) {
		return amigos.get(indice);
	}

	/**
	 * Atualiza o feed de noticias do usuario.
	 */
	public void atualizaFeed() {
		this.feedNoticias.atualizaFeed(this.amigos);
	}

}
