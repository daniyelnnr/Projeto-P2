package core;

import exceptions.ErroUsuarioOffline;

public class Operacoes {

	public String cadastraUsuario(Controller controller, String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario, String imgAvatar)
			throws Exception {
		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (controller.validadores.validaData(dataNasUsuario)
				&& controller.validadores.validaEmail(emailUsuario)) {
			controller.validadores.validaData(dataNasUsuario);
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario, imgAvatar);
			controller.bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}
	
	}

	public String cadastraUsuario(Controller controller, String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario) throws Exception {
	
		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (controller.validadores.validaData(dataNasUsuario)
				&& controller.validadores.validaEmail(emailUsuario)) {
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario);
			controller.bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}
	
	}

	public boolean login(Controller controller, String emailUsuario, String senhaUsuario)
			throws Exception {
		if (controller.usuarioLogado == null) {
			Usuario user = controller.bancodedados.buscaUsuario(emailUsuario);
			if (user == null) {
				throw new Exception("Um usuarix com email " + emailUsuario
						+ " nao esta cadastradx.");
			}
	
			else if (user.getSenha().equals(senhaUsuario)) {
				controller.usuarioLogado = user;
				return true;
			}
	
			else {
				throw new Exception("Senha invalida.");
			}
		} else {
			throw new Exception("Um usuarix ja esta logadx: "
					+ controller.usuarioLogado.getNome() + ".");
		}
	}

	public void logout(Controller controller) throws Exception {
		if (controller.usuarioLogado != null) {
			controller.usuarioLogado = null;
		} else {
			throw new ErroUsuarioOffline("Nao eh possivel realizar logout. ");
		}
	}

}
