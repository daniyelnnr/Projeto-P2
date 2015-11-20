package core;

import java.util.ArrayList;

/**
 * 
 * Projeto LP2 -2014.2
 * 
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 *
 *         Classe responsavel por todo tipo de manipulacao das notificacoes.
 */
public class Notificacoes {

	ArrayList<String> notificacoes = new ArrayList<String>();
	Usuario usuarioRemetente;

	/**
	 * Retorna as proximas notificacoes dos usuarios.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNextNotificacao() throws Exception {
		if (this.notificacoes.size() == 0) {
			throw new Exception("Nao ha mais notificacoes.");
		}
		String saida = this.notificacoes.get(0);
		this.notificacoes.remove(0);
		return saida;

	}

	/**
	 * Adiciona novas notificacoes a lista de notificacoes gerais de um usuario.
	 * 
	 * @param string
	 */
	public void add(String string) {
		this.notificacoes.add(string);

	}

	/**
	 * Retorna a quantidade de Notificacoes que o usuario tem.
	 * 
	 * @return
	 */
	public int getNotificacoes() {
		return this.notificacoes.size();
	}

}
