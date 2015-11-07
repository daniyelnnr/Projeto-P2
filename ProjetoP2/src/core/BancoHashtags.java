package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
		if (!conteudo.contains("#"))
			return new ArrayList<String>();
		int index = conteudo.indexOf("#");
		String resto = conteudo.substring(index, conteudo.length());
		ArrayList<String> hashtags = new ArrayList<String>();
		for (String hashtag : resto.split(" ")) {
			if (!hashtag.startsWith("#"))
				throw new Exception(
						"Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '" + hashtag
								+ "'.");
			hashtags.add(hashtag);
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
