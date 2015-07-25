package core;

import java.util.ArrayList;

public class Controller {

	boolean logado;//modificar privacidade
	ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	ArrayList<Usuario> listaUsuarioLogado = new ArrayList<Usuario>();
	/* criar construtor do controller? */
			
	public void cadastraUsuario(String emailUsuario, String senhaUsuario,
			String nomeUsuario, String dataNasUsuario, String telContatoUsuario) {
		Usuario usuario = new Usuario(emailUsuario, senhaUsuario, nomeUsuario,
				dataNasUsuario, telContatoUsuario);
		listaUsuario.add(usuario);

	}

	public void loginUsuario(String emailUsuario, String senhaUsuario) {
		for (int i = 0; i < listaUsuario.size(); i++) {
			if (listaUsuario.get(i).getEmail().equals(emailUsuario)
					&& listaUsuario.get(i).getSenha()
							.equals(senhaUsuario)
					&& listaUsuarioLogado.size() == 0) {
				this.logado = true;
				listaUsuarioLogado.add(listaUsuario.get(i));
			}
		}

	}

	public void logout(String emailUsuario) {
		for (int i = 0; i < listaUsuarioLogado.size(); i++) {

			if (listaUsuarioLogado.get(i).getEmail()
					.equals(emailUsuario)
					&& this.getStatus() == true) {
				listaUsuarioLogado.remove(0);
				this.logado = false;
			}

		}

	}

	public boolean getStatus() {
		return logado;
	}

	public void atualizaSenhaUsuario(String emailUsuario, String antigaSenha,
			String novaSenha) {
		for (int i = 0; i < listaUsuarioLogado.size(); i++) {
			if (listaUsuarioLogado.get(i).getEmail()
					.equals(emailUsuario)) {
				listaUsuarioLogado.get(i).setSenha(novaSenha);
			}
		}

	}
	
	/*public boolean buscaUsuario(String emailUsuario){
		for (Usuario usuario : listaUsuario) {
			
		}
	}*/

}
