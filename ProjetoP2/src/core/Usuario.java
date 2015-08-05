package core;

import java.util.ArrayList;

public class Usuario {

	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String dataNasc;
	private String imgAvatar;
	ArrayList<Postagem> mural = new ArrayList<Postagem>();
	ArrayList<Usuario> amigos = new ArrayList<Usuario>();
	ArrayList<Notificacoes> notificacoes = new ArrayList<Notificacoes>();
	ArrayList<Usuario> pedidosAmizade = new ArrayList<Usuario>();
	public Usuario(String nome, String email, String senha, String dataNasc,
			String imgAvatar) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.imgAvatar = imgAvatar;
		this.dataNasc = dataNasc;
	}

	public Usuario(String nome, String email, String senha, String dataNasc)
			throws Exception {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.imgAvatar = "resources/default.jpg";
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getData() {
		return dataNasc;
	}

	public String getTel() {
		return telefone;
	}

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}

	public void setFoto(String novaFoto) {
		this.imgAvatar = novaFoto;
	}

	public void setData(String novaData) {
		this.dataNasc = novaData;
	}

	public void setTelefone(String novoTelefone) {
		this.telefone = novoTelefone;
	}

	public String getFoto() {
		return imgAvatar;
	}


	public ArrayList<Postagem> getMural() {
		return mural;
	}

	public void setMural(ArrayList<Postagem> mural) {
		this.mural = mural;
	}

	public ArrayList<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(ArrayList<Usuario> amigos) {
		this.amigos = amigos;
	}

	public boolean adicionaAmigo(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if(usuario.getEmail() == amigo.getEmail()){
				this.amigos.add(usuario);
				return true;
				
			}
		}
		throw new Exception(amigo.getNome() + " nao lhe enviou solicitacoes de amizade");
	}

	public boolean removeAmigo(Usuario amigo) throws Exception {
		for (Usuario usuario : pedidosAmizade) {
			if(usuario.getEmail() == amigo.getEmail()){
				this.amigos.remove(usuario);
				return true;
				
			}
		}
		throw new Exception(amigo.getNome() + " nao lhe enviou solicitacoes de amizade");
	}

}
