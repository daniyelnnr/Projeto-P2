package core;

import java.util.ArrayList;
import java.util.List;

public class BancoHashtags {
	
	private static BancoHashtags bancoHashtags;
	private ArrayList<String> hashtagsAll = new ArrayList<String>();
	
	public static BancoHashtags getInstance(){
		if (bancoHashtags == null){
			bancoHashtags = new BancoHashtags();
		}
		return bancoHashtags;
	}
	
	public void adicionaHashtags(List<String> hashtags){
		this.hashtagsAll.addAll(hashtags);
	}
	
	public ArrayList<String> getHashtags(){
		return hashtagsAll;
	}
	
	public void adicionaHashtags(String hashtag){
		this.hashtagsAll.add(hashtag);
	}
	
}
