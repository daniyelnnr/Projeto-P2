package core;

import java.util.ArrayList;

public class Controller {

	// boolean logado;//modificar privacidade
	private Usuario usuarioLogado = null;
	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	ArrayList<Usuario> listaUsuarioLogado = new ArrayList<Usuario>();

	/* criar construtor do controller? */

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar) {
		Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario, imgAvatar);
		listaUsuario.add(usuario);
		return usuario.getEmail();

	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) {
		Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario);
		listaUsuario.add(usuario);

		return usuario.getEmail();

	}

	public void login(String emailUsuario, String senhaUsuario)
			throws Exception {
		Usuario user = this.buscaUsuario(emailUsuario);
		if (user.getSenha().equals(senhaUsuario)) {
			usuarioLogado = user;
		}
	}
	
	public String getInfoUsuarioLogado(String nomeInformacao){
		String informacaoRequerida = "";
		if (nomeInformacao.equals("Nome")) {
			informacaoRequerida = this.usuarioLogado.getNome();
		}
		
		else if (nomeInformacao.equals("Foto")) {
			informacaoRequerida = this.usuarioLogado.getFoto();
		} 
		return informacaoRequerida;	
		
	}

	public String getNome(String emailUsuario) throws Exception {
		String nomeUsuario = "";
		return this.buscaUsuario(emailUsuario).getNome();
		//return nomeUsuario;

	}

	public void logout() {

		if (this.usuarioLogado != null) {
			this.usuarioLogado = null;

		}

	}

	public void atualizaSenhaUsuario(String emailUsuario, String antigaSenha,
			String novaSenha) {
		for (int i = 0; i < listaUsuarioLogado.size(); i++) {
			if (listaUsuarioLogado.get(i).getEmail().equals(emailUsuario)) {
				listaUsuarioLogado.get(i).setSenha(novaSenha);
			}
		}

	}

	public Usuario buscaUsuario(String emailUsuario) throws Exception {
		Usuario user = null;
		for (Usuario usuario : listaUsuario) {
			if (usuario.getEmail().equals(emailUsuario))
				user = usuario;
		}

		if (user == null) {
			throw new Exception("O usuario com email " + emailUsuario
					+ " nao esta cadastrado.");
		}
		return user;
	}

}
