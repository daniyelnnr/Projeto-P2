package core;

import java.util.ArrayList;

public class Usuario {

	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String dataNasc;
	private String imgAvatar;
	ArrayList<Postagem> perfil = new ArrayList<Postagem>();
	ArrayList<Postagem> mural = new ArrayList<Postagem>();
	ArrayList<Usuario> amigos = new ArrayList<Usuario>();

	public Usuario(String nome, String email, String senha, String dataNasc,
			String imgAvatar) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.imgAvatar = imgAvatar;
		this.dataNasc = dataNasc;
	}

	public Usuario(String nome, String email, String senha, String dataNasc) {
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

	public void adicionarPostagemAoPerfil(Postagem novaPostagem) {
		this.perfil.add(novaPostagem);
	}

	public void adicionarMensagemAoMural(Postagem novaPostagem) {
		this.perfil.add(novaPostagem);
	}

	public ArrayList<Postagem> getPerfil() {
		return perfil;
	}

	public void setPerfil(ArrayList<Postagem> perfil) {
		this.perfil = perfil;
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

	public void adicionaAmigo(Usuario amigo) {
		this.amigos.add(amigo);

	}

	public void removeAmigo(Usuario amigo) {
		this.amigos.remove(amigo);

	}

}
