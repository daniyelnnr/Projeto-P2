package core;

public class UsuarioLogado {

	private Usuario usuarioLogado = null;

	public boolean login(String emailUsuario, String senhaUsuario)
			throws Exception {
		if (this.usuarioLogado == null) {
			Usuario user = this.buscaUsuario(emailUsuario);
			if (user == null) {
				throw new Exception("Um usuarix com email " + emailUsuario
						+ " nao esta cadastradx.");
			}

			else if (user.getSenha().equals(senhaUsuario)) {
				usuarioLogado = user;
				return true;
			}

			else {
				throw new Exception("Senha invalida.");
			}
		} else {
			throw new Exception("Um usuarix ja esta logadx: "
					+ this.usuarioLogado.getNome() + ".");
		}
	}

}
