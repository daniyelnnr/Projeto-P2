package core;

public class Usuario {

	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String dataNasc;
	private String imgAvatar;

	public Usuario(String email, String senha,
			String nome, String telefone, String dataNasc) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
	}
	
	public Usuario(String email, String senha,
			String nome, String telefone, String dataNasc, String imgAvatar) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
		this.imgAvatar = imgAvatar;
	}

	public String getemailUsuario() {
		return email;
	}

	public String getSenhaUsuario() {
		return senha;
	}

	public void setNovaSenha(String novaSenha) {
		this.senha = novaSenha;
	}

}
