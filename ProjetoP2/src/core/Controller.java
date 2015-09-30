package core;


public class Controller {

	Validadores validadores = new Validadores();
	Usuario usuarioLogado = null;
	BancoDeDados bancodedados = new BancoDeDados();
	Amizade amizade = new Amizade();
	Operacoes operacoes = new Operacoes();

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
	String senhaUsuario, String dataNasUsuario, String imgAvatar)
	throws Exception {
		return operacoes.cadastraUsuario( getValidadores(), getBancodedados(), nomeUsuario,
				emailUsuario, senhaUsuario, dataNasUsuario, imgAvatar);
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
	String senhaUsuario, String dataNasUsuario) throws Exception {
		return operacoes.cadastraUsuario( getValidadores(), getBancodedados(), nomeUsuario,
				emailUsuario, senhaUsuario, dataNasUsuario);
	}

	public void removeUsuario(String email) {
		this.bancodedados.removeUsuario(email);
	}

	public boolean login(String emailUsuario, String senhaUsuario)
	throws Exception {
		return operacoes.login(this, emailUsuario, senhaUsuario);
	}

	public void logout() throws Exception {
		operacoes.logout(this);
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
		amizade.aceitaAmizade(getUsuarioLogado(), getBancodedados(),  email);
	}

	public void adicionaAmigo(String email) {
		amizade.adicionaAmigo(getUsuarioLogado(), getBancodedados(),  email);
	}

	public void removeAmigo(String email) {
		amizade.removeAmigo(getUsuarioLogado(), getBancodedados(),  email);
	}

	public void rejeitaAmizade(String email) throws Exception {
		amizade.rejeitaAmizade(getUsuarioLogado(), getBancodedados(),  email);
	}

	public int getNotificacao() {
		return this.usuarioLogado.notificacoes.getNotificacoes();
	}

	public String getNextInformacao() throws Exception {
		return this.usuarioLogado.notificacoes.getNextNotificacao();
	}

	public void curtirPost(String emailAmigo, int indice) throws Exception {
		usuarioLogado.curtirPost(getBancodedados(), emailAmigo, indice);
	}

	public void atualizaPerfil(String nomeInformacao, String valor)
	throws Exception {
		usuarioLogado.atualizaPerfil(getValidadores(), nomeInformacao, valor);
	}

	public void atualizaPerfil(String nomeInformacao, String valor,
	String velhaSenha) throws Exception {
		usuarioLogado.atualizaPerfil(nomeInformacao, valor,
				velhaSenha);
	}

	
	public void postarMensagem(String conteudo, String data) throws Exception {
		usuarioLogado.postarMensagem(getValidadores(), conteudo, data);
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

	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}
	
	public BancoDeDados getBancodedados() {
		return bancodedados;
	}
	
	public Validadores getValidadores() {
		return validadores;
	}

	

	public String atualizaRanking() {
		// TODO Auto-generated method stub
		String var1 = bancodedados.ordenaUsuario();
		String var2 = bancodedados.ordenaHashtags();
		return null;
	}
}
