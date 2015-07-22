package core;

public class Usuario {
	
	private String emailUsuario;
	private String senhaUsuario;
	private String nomeUsuario;
	private String telContatoUsuario;
	private String dataNascUsuario;
	
	
	public Usuario(String emailUsuario, String senhaUsuario, String nomeUsuario, String telContatoUsuario, String dataNascUsuario){
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.nomeUsuario = nomeUsuario;
		this.telContatoUsuario = telContatoUsuario;
		this.dataNascUsuario = dataNascUsuario;
		
	}
	
	public String getemailUsuario(){
		return emailUsuario;
	}
	
	public String getSenhaUsuario(){
		return senhaUsuario;
	}
	
	

}
