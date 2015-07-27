package core;

public class Usuario {

	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String dataNasc;
	private String imgAvatar;

	public Usuario(String nome, String email,
			String senha, String dataNasc, String imgAvatar) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.imgAvatar = imgAvatar;
		this.dataNasc = dataNasc;
	}
	
	public Usuario(String nome, String email,
			String senha,  String dataNasc) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.imgAvatar = null;
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

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	public String getFoto() {
		return imgAvatar;
	}

}
