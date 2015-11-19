package core;

import java.util.ArrayList;

public class Controller {

	Usuario usuarioLogado = null;
	AuxiliarAmizade amizade = new AuxiliarAmizade();
	AuxiliarOperacoes operacoes = new AuxiliarOperacoes();
	AuxiliarValidadores validadores = new AuxiliarValidadores();
	BancoDeDados bancodedados = new BancoDeDados();
	BancoHashtags bancoHashtags = BancoHashtags.getInstance();


	// LOGIN, SINGUP, LOGOUT
	public String cadastraUsuario(String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario,
			String imgAvatar) throws Exception {
		return operacoes.cadastraUsuario(getValidadores(), getBancodedados(), nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario, imgAvatar);
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario)
			throws Exception {
		return operacoes.cadastraUsuario(getValidadores(), getBancodedados(), nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario);
	}

	public void removeUsuario(String email) {
		this.bancodedados.removeUsuario(email);
	}

	public boolean login(String emailUsuario, String senhaUsuario) throws Exception {
		return operacoes.login(this, emailUsuario, senhaUsuario);
	}

	public void logout() throws Exception {
		operacoes.logout(this);
	}

	// BANCO DE DADOS
	public String getInfoUsuario(String nomeInformacao, String emailUsuario) throws Exception {
		return this.bancodedados.getInfoUsuario(nomeInformacao, emailUsuario);

	}

	public String getInfoUsuarioLogado(String nomeInformacao) throws Exception {
		return this.bancodedados.getInfoUsuario(nomeInformacao, this.usuarioLogado.getEmail());

	}

	public void atualizaPerfil(String nomeInformacao, String valor) throws Exception {
		usuarioLogado.atualizaPerfil(getValidadores(), nomeInformacao, valor);
	}

	public void atualizaPerfil(String nomeInformacao, String valor, String velhaSenha) throws Exception {
		usuarioLogado.atualizaPerfil(nomeInformacao, valor, velhaSenha);
	}

	// AMIZADE
	public void aceitaAmizade(String email) throws Exception {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.aceitaAmizade(getUsuarioLogado(), usuario, email);
	}

	public void adicionaAmigo(String email) {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.adicionaAmigo(getUsuarioLogado(), usuario, email);
	}

	public void removeAmigo(String email) {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.removeAmigo(getUsuarioLogado(), usuario, email);
	}

	public void rejeitaAmizade(String email) throws Exception {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.rejeitaAmizade(getUsuarioLogado(), usuario, email);
	}

	// NOTIFICACOES
	public int getNotificacao() {
		return this.usuarioLogado.notificacoes.getNotificacoes();
	}

	public String getNextInformacao() throws Exception {
		return this.usuarioLogado.notificacoes.getNextNotificacao();
	}
	// POSTAGEM & GET CONTEUDO QTD AMIGOS

	public void curtirPost(String emailAmigo, int indice) throws Exception {
		Usuario usuarioAmigo = bancodedados.buscaUsuario(emailAmigo);
		usuarioLogado.curtirPost(usuarioAmigo, emailAmigo, indice);
	}

	public void rejeitarPost(String emailUsuario, int post) throws Exception {
		Usuario usuarioAmigo = bancodedados.buscaUsuario(emailUsuario);
		usuarioLogado.descurtirPost(usuarioAmigo, emailUsuario, post); // tirar
	}

	public void postarMensagem(String conteudo, String data) throws Exception {
		ArrayList<String> hashtags = this.bancoHashtags.pegaHastags(conteudo);
		validadores.validarUsuarioLogado(this.usuarioLogado, "Nao eh possivel postar mensagem. ");
		usuarioLogado.postarMensagem(conteudo, data, hashtags);
	}

	public String getPost(int indice) throws Exception {
		return usuarioLogado.getPost(indice);
	}

	public String getPost(String atributo, int indice) throws Exception {
		return usuarioLogado.getPost(atributo, indice);
	}

	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}

	public String getConteudo(int indice, int post) throws Exception {
		return this.usuarioLogado.getMural().get(post).getConteudo(indice);
	}

	public int getPopsPost(int post) {
		return usuarioLogado.getMural().get(post).getPops();
	}
	
	public int qtdCurtidasDePost(int post) throws Exception {
		return this.usuarioLogado.getPostagem(post).getLikes();
	}
	
	public int qtdDescurtidasDePost(int post) throws Exception {
		return this.usuarioLogado.getPostagem(post).getDeslikes();
	}

	// POPS & RANKINGS
	public int getPopsUsuario(String emailUsuario) throws Exception {
		if (this.usuarioLogado != null) {
			throw new Exception("Erro na consulta de Pops. Um usuarix ainda esta logadx.");
		}
		Usuario usuario = this.bancodedados.buscaUsuario(emailUsuario);
		return usuario.getPops();
	}

	public int getPopsUsuario() {
		return this.usuarioLogado.getPops();
	}

	public String atualizaTrendingTopics() {
		return this.bancoHashtags.getTrendingTopics();
	}

	public String atualizaRanking() {
		return this.bancodedados.ordenaUsuario();
	}

	public void adicionaPops(int pops) {
		this.usuarioLogado.atribuirPontos(pops);
	}

	public String getPopularidade() {
		return this.usuarioLogado.getPopularidade();
	}


	// GETTERS SETTERS
	public BancoDeDados getBancodedados() {
		return bancodedados;
	}

	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public AuxiliarValidadores getValidadores() {
		return validadores;
	}

	public void exportaPostagem(int indiceDoPost) throws Exception {
		this.usuarioLogado.exportaPostagem(indiceDoPost);
	}

	public String getPostFeedNoticiasMaisPopulares(int post) {
		return this.usuarioLogado.feedNoticias.getFeedPopularidade(post);
	}
//refatorar
	public String getPostFeedNoticiasRecentes(int post) {
		return this.usuarioLogado.feedNoticias.getFeedTempo(post);
	}

	public void atualizaFeed() {
		this.usuarioLogado.atualizaFeed();
	}
	

}
