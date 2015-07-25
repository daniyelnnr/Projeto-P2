package core;

public class Facade {

	Controller controller = new Controller();//modificar privacidade

	public void cadastroUsuarioFacade(String emailUsuario, String senhaUsuario,
			String nomeUsuario, String dataNasUsuario, String telContatoUsuario) {
		controller.cadastraUsuario(emailUsuario, senhaUsuario, nomeUsuario,
				dataNasUsuario, telContatoUsuario);
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
}
