package core;

public class Usuario {

	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String dataNasc;

	public Usuario(String emailUsuario, String senhaUsuario,
			String nomeUsuario, String telContatoUsuario, String dataNascUsuario) {
		this.email = emailUsuario;
		this.senha = senhaUsuario;
		this.nome = nomeUsuario;
		this.telefone = telContatoUsuario;
		this.dataNasc = dataNascUsuario;

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
