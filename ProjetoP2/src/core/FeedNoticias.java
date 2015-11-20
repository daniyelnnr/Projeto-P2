/**
 * Projeto LP2 - 2014.2
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * Metodo que sera um conglomerado de posts, podendo ser ordenado e exibido de diversas formas.
 */
package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FeedNoticias {

	private Map<Integer, ArrayList<Postagem>> feedsParaAtualizar = new HashMap<Integer, ArrayList<Postagem>>();
	private Map<Integer, Comparator<Postagem>> comparadores = new HashMap<Integer, Comparator<Postagem>>();

	private ArrayList<Postagem> feedExibidoPorData = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedExibidoPorPop = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedAtualizadoPorData = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedAtualizadoPorPop = new ArrayList<Postagem>();

	public FeedNoticias() {
		this.feedsParaAtualizar.put(0, this.feedAtualizadoPorPop);
		this.feedsParaAtualizar.put(1, this.feedAtualizadoPorData);
		this.comparadores.put(0, compararPops);
		this.comparadores.put(1, compararData);
	}

	/**
	 * Metodo que atualiza o feed de noticias de uma lista de usuarios,
	 * ordenando o feed de cada usuario de acordo com seu tipo de popularidade.
	 * 
	 * @param Recebe
	 *            um ArrayList de usuarios.
	 */
	public void atualizaFeed(ArrayList<Usuario> amigos) {
		for (Usuario amigo : amigos) {
			@SuppressWarnings("unchecked")
			ArrayList<Postagem> muralAmigo = (ArrayList<Postagem>) amigo.mural.clone();
			if (amigo.getPopularidade().equals("Normal Pop")) {
				for (int i = 0; i < 2; i++) {
					Collections.sort(muralAmigo, comparadores.get(i));
					for (int j = 0; j < muralAmigo.size() && j < 2; j++) {
						this.feedsParaAtualizar.get(i).add(muralAmigo.get(j));
					}
				}
			} else if (amigo.getPopularidade().equals("Celebridade Pop")) {
				for (int i = 0; i < 2; i++) {
					Collections.sort(muralAmigo, comparadores.get(i));
					for (int j = 0; j < muralAmigo.size() && j < 4; j++) {
						this.feedsParaAtualizar.get(i).add(muralAmigo.get(j));
					}
				}
			} else if (amigo.getPopularidade().equals("Icone Pop")) {
				for (int i = 0; i < 2; i++) {
					Collections.sort(muralAmigo, comparadores.get(i));
					for (int j = 0; j < muralAmigo.size() && j < 6; j++) {
						this.feedsParaAtualizar.get(i).add(muralAmigo.get(j));
					}
				}
			}

		}
		Collections.sort(feedAtualizadoPorData, compararData);
		Collections.sort(feedAtualizadoPorPop, compararPops);
		this.feedExibidoPorData.addAll(feedAtualizadoPorData);
		this.feedExibidoPorPop.addAll(feedAtualizadoPorPop);

	}

	/**
	 * Metodo que retorna a postagem do feed ordenado por popularidade.
	 * 
	 * @param Recebe
	 *            um inteiro com o indice da postagem no feed.
	 * @return Retorna a postagem encontrada.
	 */
	public Postagem getFeedPopularidade(int post) {
		return this.feedExibidoPorPop.get(this.feedExibidoPorPop.size() - post - 1);
	}

	/**
	 * Metodo que retorna a postagem do feed ordenado por data.
	 * 
	 * @param Recebe
	 *            um inteiro com o indice da postagem no feed.
	 * @return Retorna a postagem encontrada.
	 */
	public Postagem getFeedTempo(int post) {
		return this.feedExibidoPorData.get(this.feedExibidoPorData.size() - post - 1);
	}

	Comparator<Postagem> compararData = new Comparator<Postagem>() {

		@Override
		public int compare(Postagem postagem, Postagem outraPostagem) {
			try {
				return outraPostagem.getData().compareTo(postagem.getData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	};

	Comparator<Postagem> compararPops = new Comparator<Postagem>() {

		@Override
		public int compare(Postagem postagem, Postagem outraPostagem) {
			if (postagem.getPops() > outraPostagem.getPops()) {
				return -1;
			} else if (postagem.getPops() < outraPostagem.getPops()) {
				return 1;
			} else {
				return compararData.compare(postagem, outraPostagem);
			}

		}
	};
}
