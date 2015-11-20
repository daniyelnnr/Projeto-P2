package core;

import java.util.ArrayList;

/**
 * Projeto LP2 - 2014.2
 * 
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 *         Classe Controller, responsavel por delegar chamadas atraves de
 *         forwarding e fazer uma ponte da Facade com as demais classes.
 *
 */
public class Controller {

	Usuario usuarioLogado = null;
	AuxiliarAmizade amizade = new AuxiliarAmizade();
	AuxiliarOperacoes operacoes = new AuxiliarOperacoes();
	AuxiliarValidadores validadores = new AuxiliarValidadores();
	BancoDeDados bancodedados = new BancoDeDados();
	BancoHashtags bancoHashtags = BancoHashtags.getInstance();


	/**
	 * 
	 * 	Metodo responsavel por realizar o cadastro de novos usuarios, recebendo um Avatar de Imagem.
	 * 	Pode lançar uma execao na hora da criacao do usuario.
	 * 
	 * @param nomeUsuario
	 * @param emailUsuario
	 * @param senhaUsuario
	 * @param dataNasUsuario
	 * @param imgAvatar
	 * @return
	 * @throws Exception
	 */
	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar)
			throws Exception {
		return operacoes.cadastraUsuario(getValidadores(), getBancodedados(),
				nomeUsuario, emailUsuario, senhaUsuario, dataNasUsuario,
				imgAvatar);
	}
	
	/**
	 * Metodo responsavel por realizar o cadastro de novos usuario, contudo diferente do anterior, nao recebe um Avata de Imagem.
	 * @param nomeUsuario
	 * @param emailUsuario
	 * @param senhaUsuario
	 * @param dataNasUsuarioo
	 * @return
	 * @throws Exception
	 */
	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) throws Exception {
		return operacoes.cadastraUsuario(getValidadores(), getBancodedados(),
				nomeUsuario, emailUsuario, senhaUsuario, dataNasUsuario);
	}
	
	/**
	 * Remove um usuario do banco de dados da rede. A busca é feita atraves do email do usuario que se deseja remover.
	 * @param email
	 */
	public void removeUsuario(String email) {
		this.bancodedados.removeUsuario(email);
	}
	
	/**
	 * Metodo responsavel por realizar o login dos usuario na rede social.
	 * @param emailUsuario
	 * @param senhaUsuario
	 * @return
	 * @throws Exception
	 */
	public boolean login(String emailUsuario, String senhaUsuario)
			throws Exception {
		return operacoes.login(this, emailUsuario, senhaUsuario);
	}
	
	/**
	 * Metodo responsavel por realizar o logout dos usuarios na rede social.
	 * @throws Exception
	 */
	public void logout() throws Exception {
		operacoes.logout(this);
	}
	
	/**
	 * Pesquisa informacoes de usuarios, atraves do nome da informacao desejada e do email do usuario.
	 * @param nomeInformacao
	 * @param emailUsuario
	 * @return
	 * @throws Exception
	 */
	// BANCO DE DADOS
	public String getInfoUsuario(String nomeInformacao, String emailUsuario)
			throws Exception {
		return this.bancodedados.getInfoUsuario(nomeInformacao, emailUsuario);

	}
	
	/**
	 * Pesquisa informacoes de usuarios logados, nesse caso requerindo apenas o nome da informacao desejada.
	 * @param nomeInformacao
	 * @return
	 * @throws Exception
	 */
	public String getInfoUsuarioLogado(String nomeInformacao) throws Exception {
		return this.bancodedados.getInfoUsuario(nomeInformacao,
				this.usuarioLogado.getEmail());

	}

	/**
	 * Metodo responsavel por atualizar as informacoes de usuarios.
	 * @param nomeInformacao
	 * @param valor
	 * @throws Exception
	 */
	public void atualizaPerfil(String nomeInformacao, String valor)
			throws Exception {
		usuarioLogado.atualizaPerfil(getValidadores(), nomeInformacao, valor);
	}
	
	/**
	 * Metodo responsavel por promover a atualizacao da senha do usuario.
	 * @param nomeInformacao
	 * @param valor
	 * @param velhaSenha
	 * @throws Exception
	 */
	public void atualizaPerfil(String nomeInformacao, String valor,
			String velhaSenha) throws Exception {
		usuarioLogado.atualizaPerfil(nomeInformacao, valor, velhaSenha);
	}

	
	/**
	 * Ao recever uma solicitacao de amizade, o usuario podera rejeita-la ou adiciona-la, neste caso a amizade e criada.
	 * @param email
	 * @throws Exception
	 */
	
	public void aceitaAmizade(String email) throws Exception {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.aceitaAmizade(getUsuarioLogado(), usuario, email);
	}

	/**
	 * Caso uma solicitacao de amizade seja aceita o amigo e adicionado.
	 * @param email
	 */
	public void adicionaAmigo(String email) {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.adicionaAmigo(getUsuarioLogado(), usuario, email);
	}
	
	/**
	 * Metodo responsavel por remover amizade.
	 * @param email
	 */	
	public void removeAmigo(String email) {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.removeAmigo(getUsuarioLogado(), usuario, email);
	}
	
	/**
	 * Quando uma amizade nao e aceita. Este metodo e chamado.
	 * @param email
	 * @throws Exception
	 */
	public void rejeitaAmizade(String email) throws Exception {
		Usuario usuario = bancodedados.buscaUsuario(email);
		amizade.rejeitaAmizade(getUsuarioLogado(), usuario, email);
	}

	/**
	 * Retorna as notificacoes do usuario.
	 * @return
	 */
			
	public int getNotificacao() {
		return this.usuarioLogado.notificacoes.getNotificacoes();
	}
	
	/**
	 * Metodo que retorna uma proxima notificao do usuario, se a mesma existir.
	 * @return
	 * @throws Exception
	 */
	public String getNextInformacao() throws Exception {
		return this.usuarioLogado.notificacoes.getNextNotificacao();
	}

	
	/**
	 * Metodo responsavel por permitir um Usuario curtir posts de seus amigos.
	 * @param emailAmigo
	 * @param indice
	 * @throws Exception
	 */
	public void curtirPost(String emailAmigo, int indice) throws Exception {
		Usuario usuarioAmigo = bancodedados.buscaUsuario(emailAmigo);
		usuarioLogado.curtirPost(usuarioAmigo, emailAmigo, indice);
	}
	
	/**
	 * Metodo responsavel por permitir um Usuario rejeitar posts de seus amigos
	 * @param emailUsuario
	 * @param post
	 * @throws Exception
	 */
	public void rejeitarPost(String emailUsuario, int post) throws Exception {
		Usuario usuarioAmigo = bancodedados.buscaUsuario(emailUsuario);
		usuarioLogado.descurtirPost(usuarioAmigo, emailUsuario, post); // tirar
	}
	
	/**
	 * Metodo responsavel por postar uma mensagem.
	 * @param conteudo
	 * @param data
	 * @throws Exception
	 */
	public void postarMensagem(String conteudo, String data) throws Exception {
		ArrayList<String> hashtags = this.bancoHashtags.pegaHastags(conteudo);
		validadores.validarUsuarioLogado(this.usuarioLogado,
				"Nao eh possivel postar mensagem. ");
		usuarioLogado.postarMensagem(conteudo, data, hashtags);

	}

	/**
	 * Retorna um post especifico pelo indice do mesmo.
	 * @param indice
	 * @return
	 * @throws Exception
	 */
	public String getPost(int indice) throws Exception {
		return usuarioLogado.getPost(indice);
	}
	
	/**
	 * Retorna uma parte especifica de um post.
	 * @param atributo
	 * @param indice
	 * @return
	 * @throws Exception
	 */
	public String getPost(String atributo, int indice) throws Exception {
		return usuarioLogado.getPost(atributo, indice);
	}
	
	/**
	 * Retorna quantidade de amigos.
	 * @return
	 */
	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}
	
	/**
	 * Retorna uma parte especifica de um post do Mural do Usuario
	 * @param indice
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public String getConteudo(int indice, int post) throws Exception {
		return this.usuarioLogado.getMural().get(post).getConteudo(indice);
	}
	
	/**
	 * Retorna a quantidade de pontos de popularidade de um post especifico.
	 * @param post
	 * @return
	 */
	public int getPopsPost(int post) {
		return usuarioLogado.getMural().get(post).getPops();
	}
	
	/**
	 * Retorna quantidade de curtidas de um post.
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public int qtdCurtidasDePost(int post) throws Exception {
		return this.usuarioLogado.getPostagem(post).getLikes();
	}
	
	/**
	 * Retorna quantidade de "descurtidas" de um post.
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public int qtdDescurtidasDePost(int post) throws Exception {
		return this.usuarioLogado.getPostagem(post).getDeslikes();
	}

	/**
	 * Retorna quantidade de pontos de popularidade do usuario, pesquisado atraves de seu email.
	 * @param emailUsuario
	 * @return
	 * @throws Exception
	 */
	public int getPopsUsuario(String emailUsuario) throws Exception {
		if (this.usuarioLogado != null) {
			throw new Exception(
					"Erro na consulta de Pops. Um usuarix ainda esta logadx.");
		}
		Usuario usuario = this.bancodedados.buscaUsuario(emailUsuario);
		return usuario.getPops();
	}
	
	/**
	 * Retorna a quantidade de pontos de popularidade de um usuario logado.
	 * @return
	 */
	public int getPopsUsuario() {
		return this.usuarioLogado.getPops();
	}
	
	/**
	 * Atualiza o ranking de hashtags mais utilizadas.
	 * @return
	 */
	public String atualizaTrendingTopics() {
		return this.bancoHashtags.getTrendingTopics();
	}
	
	/**
	 * Metodo responsavel por atualizar os rankings.
	 * @return
	 */
	public String atualizaRanking() {
		return this.bancodedados.ordenaUsuario();
	}
	
	/**
	 * Metodo responsavel por adicionar pontos de popularidade.
	 * @param pops
	 */
	public void adicionaPops(int pops) {
		this.usuarioLogado.atribuirPontos(pops);
	}
	
	/**
	 * Metodo responsavel por retornar a popularidade de um usuario.
	 * @return
	 */
	public String getPopularidade() {
		return this.usuarioLogado.getPopularidade();
	}

	
	/**
	 * Retorna o banco de dados.
	 * @return
	 */
	public BancoDeDados getBancodedados() {
		return bancodedados;
	}
	
	/**
	 * Retorna o Usuario Logado no momento.
	 * @return
	 */
	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}
	
	public AuxiliarValidadores getValidadores() {
		return validadores;
	}
	/**
	 * Metodo responsavel por exportar os post para arquivos.
	 * @throws Exception
	 */
	public void exportaPostagem() throws Exception {
		this.usuarioLogado.exportaPostagem();
	}
	
	/**
	 * Metodo responsavel por retornar o feed de noticias ordenado por noticias mais populares.
	 * @param post
	 * @return
	 */
	public Postagem getPostFeedNoticiasMaisPopulares(int post) {
		return this.usuarioLogado.feedNoticias.getFeedPopularidade(post);
	}

	/**
	 * Metodo responsavel por retornar o feed de noticias ordenado por noticias mais recentes.
	 * @param post
	 * @return
	 */
	public Postagem getPostFeedNoticiasRecentes(int post) {
		return this.usuarioLogado.feedNoticias.getFeedTempo(post);
	}
	
	/**
	 * Atualiza os feeds de uma maneira mais generica.
	 */
	public void atualizaFeed() {
		this.usuarioLogado.atualizaFeed();
	}
	
	/**
	 * Retorna a quantidade do total de posts de um usuario.
	 * @return
	 */
	public int getTotalPosts() {
		return usuarioLogado.getTotalPosts();
	}

}
