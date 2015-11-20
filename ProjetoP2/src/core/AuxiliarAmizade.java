/**
 * Projeto LP2 - 2014.2
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * Essa classe vai funcionar como intermediaria para as chamadas de amizade entre dois usuarios.
 */
package core;

public class AuxiliarAmizade {
	
	/**
	 * Metodo que aceita a amizade entre dois usuarios.
	 * @param Tem como parametro um usuario que recebeu o convite de amizade, no caso o usuario logado.
	 * @param Tem como parametro um usuario que sera quem enviou o convite de amizade.
	 * @param Passa como parametro o email do usuario que enviou o convite de amizade.
	 * @throws Lanca uma excecao referente aos erros provenientes dos metodos que estao lancando excecao dentro deste metodo.
	 */
	public void aceitaAmizade(Usuario usuario2, Usuario usuario, String email) throws Exception {
		usuario2.aceitaAmizade(usuario);
		usuario.adicionaAmigo(usuario2);
		usuario.notificacoes.add(usuario2.getNome() + " aceitou sua amizade.");
	}

	/**
	 * Metodo que ira requisitar uma amizade entre dois usuarios.
	 * @param Tem como parametro um usuario que requisitou a amizade, no caso o usuario logado.
	 * @param Tem como parametro um usuario que sera quem recebera o convite de amizade.
	 * @param Passa como parametro o email do usuario que recebeu o convite de amizade.
	 */
	public void adicionaAmigo(Usuario usuario2, Usuario usuario, String email) {
		usuario.notificacoes.add(usuario2.getNome() + " quer sua amizade.");
		usuario.pedidosAmizade.add(usuario2);
	}
	
	/**
	 * Metodo que ira remover a relacao de amizade entre dois usuarios.
	 * @param Tem como parametro o usuario logado que ira remover a amizade com outro usuario.
	 * @param Tem como parametro o usuario que esta perdendo a amizade.
	 * @param Passa como parametro o email do usuario esta perdendo a amizade.
	 */
	public void removeAmigo(Usuario usuario2, Usuario usuario, String email) {
		usuario.notificacoes.add(usuario2.getNome() + " removeu a sua amizade.");
		usuario.amigos.remove(usuario2);
		usuario2.removeAmigo(usuario);
	}
	
	/**
	 * Metodo que ira negar uma nova requisicao de amizade.
	 * @param Tem como parametro o usuario que esta logado e negando a requisicao de amizade.
	 * @param Tem como parametro o usuario que esta tendo o pedido negado.
	 * @param Tem como parametro o usuario que esta tendo o pedido negado.
	 * @throws Lanca uma excecao quando o usuario eh nulo, ou seja, nao esta cadastrado no sistema. Tambem lanca excecoes oriundas dos metodos que sao chamados internamente.
	 */
	public void rejeitaAmizade(Usuario usuario2, Usuario usuario, String email) throws Exception {
		if (usuario == null) {
			throw new Exception("O usuario " + email + " nao esta cadastrado no +pop.");
		}
		usuario2.rejeitaAmizade(usuario);
		usuario.notificacoes.add(usuario2.getNome() + " rejeitou sua amizade.");

	}

}
