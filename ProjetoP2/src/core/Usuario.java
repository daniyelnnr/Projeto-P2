package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuario implements Comparable<Object>{

	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String dataNasc;
	private String imgAvatar;
	private int pops;
	FeedNoticias feedNoticias = new FeedNoticias();
	ArrayList<Postagem> mural = new ArrayList<Postagem>();
	ArrayList<Usuario> amigos = new ArrayList<Usuario>();
	ArrayList<Usuario> pedidosAmizade = new ArrayList<Usuario>();
	Notificacoes notificacoes = new Notificacoes();
	HashMap<String, String> HistoricoUsuario  = new HashMap<>();

	private ITipoDeUsuario tiposStrategy;

	public Usuario(String nome, String email, String senha, String dataNasc,
			String imgAvatar) {
		
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.imgAvatar = imgAvatar;
		this.dataNasc = dataNasc;
		this.tiposStrategy = new UsuarioNormal();
	}

	public Usuario(String nome, String email, String senha, String dataNasc)
			throws Exception {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.imgAvatar = "resources/default.jpg";
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

	public String getTel() {
		return telefone;
	}
	
	public int getPops(){
		return pops;
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

	public void setTelefone(String novoTelefone) {
		this.telefone = novoTelefone;
	}
	
	public void atribuirPontos(int valor){
		pops += valor;
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

	public boolean aceitaAmizade(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if (usuario.getEmail() == amigo.getEmail()) {
				this.amigos.add(usuario);
				return true;

			}
		}
		throw new Exception(amigo.getNome()
				+ " nao lhe enviou solicitacoes de amizade.");
	}

	public boolean rejeitaAmizade(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if (usuario.getEmail() == amigo.getEmail()) {
				this.amigos.remove(usuario);
				return true;

			}
		}
		throw new Exception(amigo.getNome()
				+ " nao lhe enviou solicitacoes de amizade.");
	}

	public int getQtdAmigos() {
		return this.amigos.size();
	}
	
	public Postagem getPostagemAmigo(String emailAmigo, int indicePost){
		Postagem postagemRequerida = null;
		
		for (Usuario usuario : amigos) {
			if (usuario.getEmail().equals(emailAmigo)) {
				postagemRequerida = usuario.mural.get(indicePost);
			}	
		}
		
		return postagemRequerida;
		
	}
	
	public Usuario getAmigo(int indice){
		return amigos.get(indice);
	}

	public void atualizaPerfil(Validadores validadores, String nomeInformacao, String valor)
			throws Exception {
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

	public void atualizaPerfil( String nomeInformacao, String valor, String velhaSenha) throws Exception {
		if ((nomeInformacao.equalsIgnoreCase("Senha"))
				&& (getSenha().equals(velhaSenha))) {
			setSenha(valor);
		} else {
			throw new Exception("A senha fornecida esta incorreta.");
		}
	}

	public void postarMensagem(Validadores validadores, String conteudo, String data) throws Exception {
		validadores.validarUsuarioLogado(this, "Nao eh possivel postar mensagem. ");
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
		mural.add(novaPostagem);
		
		//this.adicionaFeedAmigo(postagem);
		//this.postagemEmHistorico(postagem);
	}
	
	public void postagemEmHistorico(Postagem postagem){
		//vai pegar a postagem transformar em historico e adicionala ao dicionario
		
	}
	
	public void adicionaFeedAmigo(Postagem postagem){
		//for (Usuario amigo : this.amigos) {
		//	feedatualizado.add(novaPostagem);
		//}
		
		//PS- O ADD DESSE FOR SERA SOBRESCRITO.
	}

	public String getPost(int indice) throws Exception {
		return getMural().get(indice).getMensagem() + " "
				+ getMural().get(indice).getTags() + " ("
				+ getMural().get(indice).getData() + ")";
	
	}

	public String getPost(String atributo, int indice) throws Exception {
		String mensagemRequerida = "";
	
		if (atributo.equalsIgnoreCase("conteudo")) {
			mensagemRequerida = mural.get(indice)
					.getMensagem();
		}
	
		else if (atributo.equalsIgnoreCase("data")) {
			mensagemRequerida = mural.get(indice).getData();
		} else {
			mensagemRequerida = mural.get(indice)
					.getTagsToString();
	
		}
	
		return mensagemRequerida;
	}

	public void curtirPost(BancoDeDados bancodedados, String emailAmigo, int indice) throws Exception {
		Postagem postagem = getPostagemAmigo(emailAmigo,indice);
		postagem.setNewLikes();
		Usuario usuarioAmigo = bancodedados.buscaUsuario(emailAmigo);
		usuarioAmigo.notificacoes.add(getNome() + " curtiu seu post de " + postagem.getData() + ".");
		this.tiposStrategy.curtir(usuarioAmigo, postagem);
		
	}


	//Necessario por enquanto apenas para a UML!
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
