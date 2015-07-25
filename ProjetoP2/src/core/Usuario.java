package core;

public class Usuario {

	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String dataNasc;

	public Usuario(String emailUsuario, String senhaUsuario,
			String nomeUsuario, String telContatoUsuario, String dataNascUsuario) {
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.nomeUsuario = nomeUsuario;
		this.telContatoUsuario = telContatoUsuario;
		this.dataNascUsuario = dataNascUsuario;

	}

	public String getemailUsuario() {
		return emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setNovaSenha(String novaSenha) {
		this.senhaUsuario = novaSenha;
	}

}
