package core;

import exceptions.ErroUsuarioOffline;

public class Operacoes {
	
	public String cadastraUsuario(Validadores validadores, BancoDeDados bancodedados, String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario, String imgAvatar)
			throws Exception {
		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (validadores.validaData(dataNasUsuario)
				&& validadores.validaEmail(emailUsuario)) {
			validadores.validaData(dataNasUsuario);
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario, imgAvatar);
			bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}
	
	}

	public String cadastraUsuario(Validadores validadores, BancoDeDados bancodedados, String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario) throws Exception {
	
		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (validadores.validaData(dataNasUsuario)
				&& validadores.validaEmail(emailUsuario)) {
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario);
			bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}
	
	}

	public boolean login(BancoDeDados bancodedados, Usuario usuarioLogado, String emailUsuario, String senhaUsuario)
			throws Exception {
		if (usuarioLogado == null) {
			Usuario user = bancodedados.buscaUsuario(emailUsuario);
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
					+ usuarioLogado.getNome() + ".");
		}
	}

	public void logout(Usuario usuarioLogado) throws Exception {
		if (usuarioLogado != null) {
			usuarioLogado = null;
		} else {
			throw new ErroUsuarioOffline("Nao eh possivel realizar logout. ");
		}
	}

}
