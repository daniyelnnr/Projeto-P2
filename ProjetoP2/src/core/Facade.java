package core;

public class Facade {

	Controller controller = new Controller();//modificar privacidade

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar) {
		controller.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario, imgAvatar);
		
		return emailUsuario;
	}
	
	public String cadastraUsuario (String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario){
		controller.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario, dataNasUsuario);
		
		return emailUsuario;
	}
	
	public String getInfoUsuarioLogado(String nomeInformacao) throws Exception{
		return controller.getInfoUsuarioLogado(nomeInformacao);
	
	}

	public void login(String emailUsuario, String senhaUsuario) throws Exception {
		controller.login(emailUsuario, senhaUsuario);
	}

	public void logout() throws Exception {
		controller.logout();

	}
	
	public String getNome(String email) throws Exception{
		String nomeUsuario = controller.getNome(email);
		if (nomeUsuario == null){
			throw new Exception("O usuario com email "+email+" nao esta cadastrado.");
		} else {
			return nomeUsuario;
		}
	}

	public void atualizaSenhaUsuarioFacade(String novaSenha) {
		controller.atualizaSenhaUsuario( novaSenha);
	}
	
	public void atualizaNomeUsuario(String novoNome){
		controller.atualizaNomeUsuario(novoNome);
	}
	
	public void atualizaEmail(String novoEmail){
		controller.atualizaEmail(novoEmail);
	}
	
	public void atualizaFoto(String novaFoto){
		controller.atualizaFoto(novaFoto);
	}
	
	public void atualizaData(String novaData){
		controller.ataulizaData(novaData);
	}
	
	public void atualizadaTel(String novoTelefone){
		controller.atualizaTelefone(novoTelefone);
	}
	
	public Usuario buscaUsuario(String emailUsuario) throws Exception{
		return controller.buscaUsuario(emailUsuario);
		
	}
	


}
