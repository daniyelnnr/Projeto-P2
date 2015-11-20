/**
 * Projeto LP2 - 2014.2
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * Classe que armazena as hashtags que sao utilizadas no sistema. Ela tambem eh uma classe Singleton.
 */
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
	
	/**
	 * Metodo que retorna uma instancia dele mesmo. Como eh um metodo estatico, a instancia pode ser acessado diretamente.
	 * @return Retorna uma instancia do mesmo tipo da classe.
	 */
	public static BancoHashtags getInstance() {
		if (bancoHashtags == null) {
			bancoHashtags = new BancoHashtags();
		}
		return bancoHashtags;
	}

	/**
	 * Metodo que separa e retorna uma lista das hashtags do conteudo de um post, filtrando-as.
	 * @param Recebe o conteudo de um post.
	 * @return Retorna uma lista com as hashtags filtradas.
	 * @throws Lanca excecao quando o padrao de hashtags em uma postagem esta incorreto.
	 */
	public ArrayList<String> pegaHastags(String conteudo) throws Exception {
		String hashtag = null;
		ArrayList<String> hashtags = new ArrayList<String>();
		Pattern tagsPadrao = Pattern.compile(
				"(?<tags>([#])\\w*)");
		Matcher matcherTags = tagsPadrao.matcher(conteudo);
		Pattern tagErroPadrao = Pattern.compile("(#\\w+\\s+)(?<tagComErro>\\w+)");
		Matcher matcherTagErro = tagErroPadrao.matcher(conteudo);
		while (matcherTagErro.find()) {
			throw new Exception("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '"
					+ matcherTagErro.group("tagComErro") + "'.");
		}
		while (matcherTags.find()) {
			if (matcherTags.group("tags") != null) {
				hashtag = matcherTags.group("tags");
				hashtags.add(hashtag);
			}
		}
		this.hashtagsAll.addAll(hashtags);
		return hashtags;
	}
	
	/**
	 * Metodo que ordena as hashtags por frequencia, retornando as mais frequentes.
	 * @return Retorna uma lista com as hashtags mais frequentes.
	 */
	public ArrayList<String> ordenaHashtags() {
		ArrayList<String> trendHastags = new ArrayList<>();
		HashMap<String, Integer> hashtagsFrequencia = new HashMap<String, Integer>();
		List<String> list = listaTagsFrequencia(hashtagsFrequencia);
		for (int i = 0; i < 3; i++) {
			trendHastags.add(list.get(i));
		}
		this.hashtagsMap = hashtagsFrequencia;
		return (trendHastags);
	}
	
	/**
	 * Metodo que ordena um mapa por frequencia.
	 * @param Recebe um mapa que sera ordenado.
	 * @return Retorna uma lista de hashtags ordenada por frequencia.
	 */
	private List<String> listaTagsFrequencia(HashMap<String, Integer> hashtagsFrequencia) {
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
		return list;
	}

	/**
	 * Metodo que gera o Trending Topics, que serao as hashtags mais populares no sistema.
	 * @return Retorna uma lista das hashtags mais populares.
	 */
	public String getTrendingTopics() {
		ArrayList<String> trendHastags = this.ordenaHashtags();
		String retorno = "Trending Topics: ";
		for (int i = 0; i < 3; i++) {
			retorno += String.format(" (%d) %s: %d;", i + 1, trendHastags.get(i),
					this.hashtagsMap.get(trendHastags.get(i)));
		}
		return retorno;
	}
	
	/*
	public void adicionaHashtags(List<String> hashtags) {
		this.hashtagsAll.addAll(hashtags);
	}
	*/
	
	/**
	 * Metodo que retorna a lista de todas as hashtags do sistema.
	 * @return Retorna um ArrayList com as hashtags.
	 */
	public ArrayList<String> getHashtags() {
		return hashtagsAll;
	}
	
	/**
	 * Metodo que adiciona hashtags ao conjunto de todas as hashtags.
	 * @param Receve a hashtag a ser adicionada.
	 */
	public void adicionaHashtags(String hashtag) {
		this.hashtagsAll.add(hashtag);
	}
}
