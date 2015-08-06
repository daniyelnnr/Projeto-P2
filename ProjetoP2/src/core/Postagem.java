package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Postagem {
	private String mensagem;
	private String data;
	private int pontosPopularidade;
	private ArrayList<String> tags = new ArrayList<String>();
	private int likes;
	private int deslikes;

	public Postagem(String mensagem, ArrayList<String> hashtags, String data){
		this.mensagem = mensagem;
		this.data = data;
		this.pontosPopularidade = 0;
		this.likes = 0;
		this.deslikes = 0;
		this.tags = hashtags;
	}
	
	public String getMensagem(){
		return mensagem;
	}
	
	public String getTags(){
		String txt = "";
		for (String tag : tags) {
			txt += tag + " ";
		}
		return txt.substring(0, txt.length()-1);
	}
	
	public String getTagsToString(){
		String txt = "";
		for (String tag : tags) {
			txt += tag + ",";
		}
		return txt.substring(0, txt.length()-1);
	}
	
	public int getLikes(){
		return likes;
	}
	
	public void setNewLikes(){
		this.likes += 1;
	}
	
	public String getData() throws Exception{
		String data = this.data;
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String output = myFormat.format(input.parse(data));
		return output;
	}
}
