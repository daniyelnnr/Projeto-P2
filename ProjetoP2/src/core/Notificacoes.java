package core;

import java.util.ArrayList;

public class Notificacoes {

	ArrayList<String> notificacoes = new ArrayList<String>();
	private int tamanho = this.notificacoes.size();
	Usuario usuarioRemetente;
	
public int getNotificacoes(){
	return tamanho;
}

public String getNextNotificacao() throws Exception{
	if(tamanho > 0){
		throw new Exception("Nao ha mais notificacoes.");
	}
	String saida = this.notificacoes.get(0);
	this.notificacoes.remove(0);
	return saida;
		
	
}
}
