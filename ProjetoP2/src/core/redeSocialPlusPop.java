package core;

import java.util.ArrayList;

public class redeSocialPlusPop {
	
	ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();


	public void telaInicial(){
		System.out.println("Seja bem vindo ao +Pop a rede Social mais popular de todas !");
		System.out.println("Selecione sua opção: ");
		System.out.println("1 - Cadastrar Usuario");
		System.out.println("2 - Login");
		System.out.println("3 - Sair");
		
	}
	
	public void TelaPerfil(){
		System.out.println("Seja bem vindo ");
		System.out.println("1 - Postar");
		System.out.println("2- Logout");
	}
	
	public void criaUsuario(String emailUsuario, String senhaUsuario, String nomeUsuario, String telContatoUsuario, String dataNascUsuario){
		Usuario usuario = new Usuario(emailUsuario,senhaUsuario,nomeUsuario,telContatoUsuario,dataNascUsuario);
		listaDeUsuarios.add(usuario);
		
		System.out.println("Usuario criado com sucesso!");
		
	}

	
	public void login(String emailUsuario, String senha){
		for (int i = 0; i < listaDeUsuarios.size(); i++) {
			if (listaDeUsuarios.get(i).getemailUsuario().equals(emailUsuario)) {
				if (listaDeUsuarios.get(i).getSenhaUsuario().equals(senha)) {
					this.TelaPerfil();
				}
			}
			
		}
		
	}

}
