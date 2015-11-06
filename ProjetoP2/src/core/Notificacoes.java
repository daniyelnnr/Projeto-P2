package core;

import java.util.ArrayList;

public class Notificacoes {

	ArrayList<String> notificacoes = new ArrayList<String>();
	Usuario usuarioRemetente;


	public String getNextNotificacao() throws Exception {
		if (this.notificacoes.size() == 0) {
			throw new Exception("Nao ha mais notificacoes.");
		}
		String saida = this.notificacoes.get(0);
		this.notificacoes.remove(0);
		return saida;

	}

	public void add(String string) {
		this.notificacoes.add(string);

	}

	public int getNotificacoes() {
		return this.notificacoes.size();
	}

}
