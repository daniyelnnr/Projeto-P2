package core;

import java.util.Scanner;

public class Pagina {
	
	public static void main(String[] args) {
		redeSocialPlusPop pluspop = new redeSocialPlusPop();
		Scanner sc = new Scanner(System.in);
		int opcao;
		
		
		do {

		
		pluspop.telaInicial();
		System.out.print("Digite sua opção: ");
		sc.nextLine();
		opcao = sc.nextInt();
		
		
		if (opcao == 1) {
			System.out.print("Email do usuario: ");
			String emailUsuario = sc.nextLine();
			sc.nextLine();
			System.out.print("Senha do usuario: ");
			String senhaUsuario = sc.nextLine();
			System.out.print("Nome do Usuario: ");
			String nomeUsuario = sc.nextLine();
			System.out.print("Telefone para contato: ");
			String telContatoUsuario = sc.nextLine();
			System.out.print("Data de nascimento: ");
			String dataNascUsuario = sc.nextLine();
			
			pluspop.criaUsuario(emailUsuario, senhaUsuario, nomeUsuario, telContatoUsuario, dataNascUsuario);
		
		}
		
		if (opcao == 2) {
			System.out.print("Email do usuario: ");
			String emailUsuario = sc.nextLine();
			sc.nextLine();
			System.out.print("Senha do usuario: ");
			String senhaUsuario = sc.nextLine();
			pluspop.login(emailUsuario, senhaUsuario);
		}
		
		}while(opcao != 3);
		
		
	}
}
