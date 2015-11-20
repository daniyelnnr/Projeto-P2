/**
 * Projeto LP2 - 2014.2
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * Essa classe vai funcionar para fazer operacoes como cadastro de usuarios e login/logout, lancando excecoes quando preciso.
 */
package core;

import exceptions.ErroUsuarioOffline;

public class AuxiliarOperacoes {
	// Validadores validadores, BancoDeDados bancodedados, Usuario usuarioLogado
	/**
	 * Metodo que cadastra um usuario no banco de dados.
	 * @param Recebe uma instancia de validadores, para validacao de datas e emails.
	 * @param Recebe uma instancia de banco de dados, para futura listagem de usuarios.
	 * @param Parametro que define o nome do usuario a ser cadastrado.
	 * @param Parametro que define o email do usuario a ser cadastrado.
	 * @param Parametro que define a senha do usuario a ser cadastrado.
	 * @param Parametro que define a data de nascimento do usuario a ser cadastrado.
	 * @param Parametro que define a imagem de avatar do usuario a ser cadastrado.
	 * @return Retorna o email do usuario que foi cadastrado.
	 * @throws Lanca excecao caso nome, data de nascimento ou email sejam invalidos.
	 */
	public String cadastraUsuario(AuxiliarValidadores validadores, BancoDeDados bancodedados, String nomeUsuario,
			String emailUsuario, String senhaUsuario, String dataNasUsuario, String imgAvatar) throws Exception {
		if (nomeUsuario.equals("") || nomeUsuario == null || nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (validadores.validaData(dataNasUsuario) && validadores.validaEmail(emailUsuario)) {
			validadores.validaData(dataNasUsuario);
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario, dataNasUsuario, imgAvatar);
			bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}

	}

	/**
	 * Metodo que cadastra um usuario no banco de dados.
	 * @param Recebe uma instancia de validadores, para validacao de datas e emails.
	 * @param Recebe uma instancia de banco de dados, para futura listagem de usuarios.
	 * @param Parametro que define o nome do usuario a ser cadastrado.
	 * @param Parametro que define o email do usuario a ser cadastrado.
	 * @param Parametro que define a senha do usuario a ser cadastrado.
	 * @param Parametro que define a data de nascimento do usuario a ser cadastrado.
	 * @return Retorna o email do usuario que foi cadastrado.
	 * @throws Lanca excecao caso nome, data de nascimento ou email sejam invalidos.
	 */
	public String cadastraUsuario(AuxiliarValidadores validadores, BancoDeDados bancodedados, String nomeUsuario,
			String emailUsuario, String senhaUsuario, String dataNasUsuario) throws Exception {

		if (nomeUsuario.equals("") || nomeUsuario == null || nomeUsuario.equals("  ")) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		} else if (validadores.validaData(dataNasUsuario) && validadores.validaEmail(emailUsuario)) {
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario, dataNasUsuario);
			bancodedados.getListaUsuario().add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception("Formato de e-mail esta invalido.");
		}

	}

	/**
	 * Metodo que confirma a realizacao do login de um usuario no sistema.
	 * @param Recebe uma instancia do controller, que esta fazendo um forwarding com a facade.
	 * @param Recebe o email do usuario que deseja realizar o login no sistema.
	 * @param Recebe a senha do usuario que deseja realizar o login no sistema.
	 * @return Retorna um boolean confirmando ou não do login.
	 * @throws Lanca excecao caso o usuario nao esteja cadastrado, senha invalida ou ja exista um usuario logado no sistema.
	 */
	public boolean login(Controller controller, String emailUsuario, String senhaUsuario) throws Exception {
		if (controller.usuarioLogado == null) {
			Usuario user = controller.bancodedados.buscaUsuario(emailUsuario);
			if (user == null) {
				throw new Exception("Um usuarix com email " + emailUsuario + " nao esta cadastradx.");
			}

			else if (user.getSenha().equals(senhaUsuario)) {
				controller.usuarioLogado = user;
				return true;
			}

			else {
				throw new Exception("Senha invalida.");
			}
		} else {
			throw new Exception("Um usuarix ja esta logadx: " + controller.usuarioLogado.getNome() + ".");
		}
	}

	/**
	 * Metodo que confirma a realizacao do logout de um usuario no sistema.
	 * @param Recebe uma instancia do controller, que esta fazendo um forwarding com a facade.
	 * @throws Lanca excecao caso nao seja possivel realizar o logout, ou seja, quando o sistema nao foi inicializado.
	 */
	public void logout(Controller controller) throws Exception {
		if (controller.usuarioLogado != null) {
			controller.usuarioLogado = null;
		} else {
			throw new ErroUsuarioOffline("Nao eh possivel realizar logout. ");
		}
	}


}
