package core;

public class Facade {

	Controller controle = new Controller(); 
	Controller sistema = null;
	
	
	public void iniciaSistema(){
		sistema = controle;
	}
	
	public void fechaSistema()throws Exception{
		if (sistema.getUsuarioLogado() != null){
			throw new Exception ("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
		
		else{
			this.sistema = null;
		}
	}

	

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar) throws Exception {
		sistema.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario, imgAvatar);

		return emailUsuario;
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) throws Exception {
		sistema.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario);

		return emailUsuario;
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario) throws Exception {
		return sistema.getInfoUsuario(nomeInformacao, emailUsuario);

	}
	
	public String getInfoUsuario(String nomeInformacao) throws Exception{
		return sistema.getInfoUsuarioLogado(nomeInformacao);
	}

	public void login(String emailUsuario, String senhaUsuario)
			throws Exception {
		sistema.login(emailUsuario, senhaUsuario);
	}

	public void logout() throws Exception {
		sistema.logout();

	}
	
	public void removeUsuario(String email){
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

	public void atualizaSenhaUsuarioFacade(String novaSenha) {
		sistema.atualizaSenhaUsuario(novaSenha);
	}

	public void atualizaNomeUsuario(String novoNome) {
		sistema.atualizaNomeUsuario(novoNome);
	}

	public void atualizaEmail(String novoEmail) {
		sistema.atualizaEmail(novoEmail);
	}

	public void atualizaFoto(String novaFoto) {
		sistema.atualizaFoto(novaFoto);
	}

	public void atualizaData(String novaData) {
		sistema.ataulizaData(novaData);
	}

	public void atualizadaTel(String novoTelefone) {
		sistema.atualizaTelefone(novoTelefone);
	}

	public Usuario buscaUsuario(String emailUsuario) throws Exception {
		return sistema.buscaUsuario(emailUsuario);

	}
	
	public void postarMensagem(String conteudo) throws Exception {
		sistema.postarMensagem(conteudo);
	}
	
}
