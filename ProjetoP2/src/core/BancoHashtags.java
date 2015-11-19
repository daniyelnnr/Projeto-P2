package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BancoHashtags {

	private static BancoHashtags bancoHashtags;
	private ArrayList<String> hashtagsAll = new ArrayList<String>();
	private HashMap<String, Integer> hashtagsMap;

	public static BancoHashtags getInstance() {
		if (bancoHashtags == null) {
			bancoHashtags = new BancoHashtags();
		}
		return bancoHashtags;
	}

	public ArrayList<String> pegaHastags(String conteudo) throws Exception {
		String hashtag = null;
		ArrayList<String> hashtags = new ArrayList<String>();
		Pattern p1 = Pattern.compile("(((?<mensagem>^.*?)(?=[#,<]))|((?<=<(?<multimidia>imagem|audio)>)(?<caminho>\\S*)(?=</(imagem|audio)>)|(?<tags>([#])\\w*)))");
		Matcher m = p1.matcher(conteudo);
		Pattern p = Pattern.compile("(#\\w+\\s+)(?<ai>\\w+)");
		Matcher m1 = p.matcher(conteudo);
		while(m1.find()){
			throw new Exception(
					"Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '" + m1.group("ai")
					+ "'.");
		}
		while(m.find())
		{
			if(m.group("tags") != null){
				hashtag = m.group("tags");
				hashtags.add(hashtag);
			}
		}
		this.hashtagsAll.addAll(hashtags);
		return hashtags;
	}

	public ArrayList<String> ordenaHashtags() {
		ArrayList<String> trendHastags = new ArrayList<>();
		HashMap<String, Integer> hashtagsFrequencia = new HashMap<String, Integer>();
		for (String string : getHashtags()) {
			if (hashtagsFrequencia.containsKey(string)) {
				int frequenciaAtual = hashtagsFrequencia.get(string);
				frequenciaAtual++;
				hashtagsFrequencia.replace(string, frequenciaAtual);
			} else {
				hashtagsFrequencia.put(string, 1);
			}
		}
		List<String> list = new ArrayList<String>(hashtagsFrequencia.keySet());
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String x, String y) {
				int compare = hashtagsFrequencia.get(y) - hashtagsFrequencia.get(x);
				if (compare == 0)
					compare = y.compareToIgnoreCase(x);
				return compare;
			}
		});
		for (int i = 0; i < 3; i++) {
			trendHastags.add(list.get(i));
		}
		this.hashtagsMap = hashtagsFrequencia;
		return (trendHastags);
	}

	public String getTrendingTopics() {
		ArrayList<String> trendHastags = this.ordenaHashtags();
		String retorno = "Trending Topics: ";
		for (int i = 0; i < 3; i++) {
			retorno += String.format(" (%d) %s: %d;", i + 1, trendHastags.get(i),
					this.hashtagsMap.get(trendHastags.get(i)));
		}
		return retorno;
	}

	public void adicionaHashtags(List<String> hashtags) {
		this.hashtagsAll.addAll(hashtags);
	}

	public ArrayList<String> getHashtags() {
		return hashtagsAll;
	}

	public void adicionaHashtags(String hashtag) {
		this.hashtagsAll.add(hashtag);
	}
}
