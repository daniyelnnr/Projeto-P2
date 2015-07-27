package core;

public class Facade {

	Controller controller = new Controller();//modificar privacidade

	public String cadastraUsuario(String emailUsuario, String senhaUsuario,
			String nomeUsuario, String dataNasUsuario, String imgAvatar) {
		controller.cadastraUsuario(emailUsuario, senhaUsuario, nomeUsuario,
				dataNasUsuario, imgAvatar);
		
		return emailUsuario;
	}
	
	public String cadastraUsuario (String emailUsuario, String senhaUsuario, String nomeUsuario, String dataNasUsuario){
		controller.cadastraUsuario(emailUsuario, senhaUsuario, nomeUsuario, dataNasUsuario);
		
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
