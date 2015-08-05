package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class Postagem {
	private String conteudo;
	private String dataDePostagem;
	private int pontosPopularidade;
	ArrayList<String> hastags = new ArrayList<String>();
	private int likes;
	private int deslikes;

	public Postagem(String conteudo, String data) {
		this.conteudo = conteudo;
		this.dataDePostagem = data;
		this.pontosPopularidade = 0;
		this.likes = 0;
		this.deslikes = 0;
		this.hastags.addAll(procuraHastags(conteudo));
	}

	private Collection<? extends String> procuraHastags(String conteudo2) {
		return hastags;
		// num sei
	}
	
	public String getConteudo(){
		return conteudo;
	}
	
	public String getData() throws Exception{
		String data = this.dataDePostagem;
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String output = myFormat.format(input.parse(data));
		return output;
	}
}
