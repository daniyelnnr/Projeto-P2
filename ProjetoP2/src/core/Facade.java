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

	public void loginUsuarioFacade(String emailUsuario, String senhaUsuario) {
		controller.loginUsuario(emailUsuario, senhaUsuario);
	}

	public void logoutFacade(String emailUsuario) {
		controller.logout(emailUsuario);

	}

	public void atualizaSenhaUsuarioFacade(String emailUsuario,
			String antigaSenha, String novaSenha) {
		controller.atualizaSenhaUsuario(emailUsuario, antigaSenha, novaSenha);
	}
	
	public Usuario buscaUsuario(String emailUsuario){
		return controller.buscaUsuario(emailUsuario);
		
	}


}
