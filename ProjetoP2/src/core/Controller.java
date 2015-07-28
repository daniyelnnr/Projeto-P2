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

	public boolean login(String emailUsuario, String senhaUsuario)
			throws Exception {
		if (this.usuarioLogado == null){
		Usuario user = this.buscaUsuario(emailUsuario);
		if (user == null) {
			throw new Exception("Nao foi possivel realizar login. O usuario com email "+ emailUsuario +" nao esta cadastrado.");
		}
		
		else if (user.getSenha().equals(senhaUsuario)) {
			usuarioLogado = user;
			return true;
		}
		
		else{
			throw new Exception ("Nao foi possivel realizar login. Senha Invalida.");
		}
		}else{
			throw new Exception("Nao foi possivel realizar login. Um usuario ja esta logado: "+this.usuarioLogado.getNome()+".");
		}
	}
	
	public String getInfoUsuarioLogado(String nomeInformacao) throws Exception{
		String informacaoRequerida = "";
		if (nomeInformacao.equals("Senha") || nomeInformacao.equals("SENHA") || nomeInformacao.equals("senha")) {
			throw new Exception ("A senha do usuario eh protegida.");
		}
		
		else if (nomeInformacao.equals("Nome") || nomeInformacao.equals("NOME") || nomeInformacao.equals("nome")){
			informacaoRequerida = this.usuarioLogado.getNome();
		}
		
		else if (nomeInformacao.equals("Foto") || nomeInformacao.equals("FOTO") || nomeInformacao.equals("foto")) {
			informacaoRequerida = this.usuarioLogado.getFoto();
		} 
		
		else if (nomeInformacao.equals("Email") || nomeInformacao.equals("EMAIL") || nomeInformacao.equals("email")){
			informacaoRequerida = this.usuarioLogado.getEmail();
		}
		
		else if (nomeInformacao.equals("Data") || nomeInformacao.equals("DATA") || nomeInformacao.equals("data")){
			informacaoRequerida = this.usuarioLogado.getData();
		}
		
		else{ //(nomeInformacao.equals("Telefone") || nomeInformacao.equals("TELEFONE") || nomeInformacao.equals("telefone")){
			informacaoRequerida = this.usuarioLogado.getTel();
		}
		
		
		return informacaoRequerida;	
		
	}


	public String getNome(String emailUsuario){
		//String nomeUsuario = "";
		Usuario user = this.buscaUsuario(emailUsuario);
		if (user == null){
			return null;
		} else {
			return user.getNome();
		}
		//return nomeUsuario;

	}

	public void logout() throws Exception {

		if (this.usuarioLogado != null) {
			this.usuarioLogado = null;
		} else {
			throw new Exception("Nao eh possivel realizar logout. Nenhum usuario esta logado no +pop.");
		}

	}
	
	public void atualizaSenhaUsuario(String novaSenha) {
		this.usuarioLogado.setSenha(novaSenha);
	}
	
	public void atualizaNomeUsuario(String novoNome){
		this.usuarioLogado.setNome(novoNome);
	}
	
	public void atualizaEmail(String novoEmail){
		this.usuarioLogado.setEmail(novoEmail);
	}
	
	public void atualizaFoto(String novaFoto){
		this.usuarioLogado.setFoto(novaFoto);
	}
	
	public void ataulizaData(String novaData){
		this.usuarioLogado.setData(novaData);
	}
	
	public void atualizaTelefone(String novoTelefone){
		this.usuarioLogado.setTelefone(novoTelefone);
	}

	public Usuario buscaUsuario(String emailUsuario){
		Usuario user = null;
		for (Usuario usuario : listaUsuario) {
			if (usuario.getEmail().equals(emailUsuario))
				user = usuario;
		}

		return user;
	}

}
