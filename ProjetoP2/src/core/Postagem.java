package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Postagem implements Comparable<Postagem> {
	private String mensagem;
	private String data;
	private int pontosPopularidade;
	private ArrayList<String> tags = new ArrayList<String>();
	private int likes;
	private int deslikes;
	private ArrayList<String> conteudo = new ArrayList<String>();
	public SimpleDateFormat dataBasico;


	public Postagem(String mensagem, ArrayList<String> hashtags, String data) {
		this.mensagem = mensagem;
		this.data = data;
		this.setPontosPopularidade(0);
		this.likes = 0;
		this.setDeslikes(0);
		this.tags = hashtags;
		this.refinaMensagem(this.mensagem);

	}

	public void refinaMensagem(String mensagem){
		if(mensagem.contains(" <imagem>")){
			this.conteudo.add(mensagem.substring(0, mensagem.indexOf(" <imagem>")));	
		}
		if(mensagem.contains(" <audio>")){
			this.conteudo.add(mensagem.substring(0, mensagem.indexOf(" <audio>")));
		}
		
		Pattern padrao1 = Pattern.compile("(?<=<audio>)(\\S*)(?=</audio>)");  
		List<String> list1 = new ArrayList<String>();
		Matcher m1 = padrao1.matcher(mensagem);

			while (m1.find()) {
			    list1.add("$arquivo_audio:" + m1.group());
			}
			this.conteudo.addAll(list1);
		Pattern padrao2 = Pattern.compile("(?<=<imagem>)(\\S*)(?=</imagem>)");  
		List<String> list2 = new ArrayList<String>();
		Matcher m2 = padrao2.matcher(mensagem);

			while (m2.find()) {
			    list2.add("$arquivo_imagem:" + m2.group());
			}
			this.conteudo.addAll(list2);
	}
	
	public String getConteudo(int index) throws Exception {
		if(index < 0){
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		}
		if((this.conteudo.size()) < index+1){
			throw new Exception("Item #" + index + " nao existe nesse post, ele possui apenas " + conteudo.size() + " itens distintos.");
		}
		return conteudo.get(index);
	}
	
	
	
	public String getMensagem() {
		return mensagem;
	}

	public String getTags() {
		String txt = "";
		for (String tag : tags) {
			txt += tag + " ";
		}
		return txt.substring(0, txt.length() - 1);
	}

	public String getTagsToString() {
		String txt = "";
		for (String tag : tags) {
			txt += tag + ",";
		}
		return txt.substring(0, txt.length() - 1);
	}

	public int getLikes() {
		return likes;
	}

	public void setNewLikes() {
		this.likes += 1;
	}

	public String getData() throws Exception {
		String data = this.data;
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String output = myFormat.format(input.parse(data));
		this.dataBasico = input;
		return output;
	}


	@Override
	public int compareTo(Postagem postagem) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPontosPopularidade() {
		return pontosPopularidade;
	}

	public void setPontosPopularidade(int pontosPopularidade) {
		this.pontosPopularidade = pontosPopularidade;
	}

	public int getDeslikes() {
		return deslikes;
	}

	public void setDeslikes(int deslikes) {
		this.deslikes = deslikes;
	}
	
}
