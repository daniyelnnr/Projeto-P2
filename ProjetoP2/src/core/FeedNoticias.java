package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FeedNoticias {

	private ArrayList<Postagem> feedNormal = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedCelebridade = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedIcone = new ArrayList<Postagem>();

	private Map<Integer, ArrayList<Postagem>> feeds = new HashMap<Integer, ArrayList<Postagem>>();
	private Map<Integer, ArrayList<Postagem>> feedsParaAtualizar = new HashMap<Integer, ArrayList<Postagem>>();
	private Map<Integer, Comparator<Postagem>> comparadores = new HashMap<Integer, Comparator<Postagem>>();

	private ArrayList<Postagem> feedExibidoPorData = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedExibidoPorPop = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedAtualizadoPorData = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedAtualizadoPorPop = new ArrayList<Postagem>();

	public FeedNoticias() {
		this.feeds.put(0, this.feedNormal);
		this.feeds.put(1, this.feedCelebridade);
		this.feeds.put(2, this.feedIcone);
		this.feedsParaAtualizar.put(0, this.feedAtualizadoPorData);
		this.feedsParaAtualizar.put(0, this.feedAtualizadoPorPop);
		this.comparadores.put(0, compararData);
		this.comparadores.put(1, compararPops);
	}

	public void atualizaFeed() {
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 3; i++) {
				Collections.sort(feeds.get(i), this.comparadores.get(j));
			}
			this.feedsParaAtualizar.get(j).addAll(this.feedNormal.subList(0, 2));
			this.feedsParaAtualizar.get(j).addAll(this.feedCelebridade.subList(0, 4));
			this.feedsParaAtualizar.get(j).addAll(this.feedIcone.subList(0, 6));
		}

		this.feedExibidoPorData.addAll(this.feedAtualizadoPorData);
		this.feedExibidoPorPop.addAll(this.feedAtualizadoPorPop);

	}

	public HashMap<Integer, ArrayList<Postagem>> getFeeds() {
		return (HashMap<Integer, ArrayList<Postagem>>) feeds;
	}

	public ArrayList<Postagem> getFeedPopularidade() {
		return this.feedExibidoPorPop;
	}

	public ArrayList<Postagem> getFeedTempo() {
		return this.feedAtualizadoPorData;
	}

	public void adicionaPostagem(Postagem novaPostagem, ITipoDeUsuario tipoDeUsuario) {
		if (tipoDeUsuario.getTipoPopularidade().equals("Normal")) {
			this.feedNormal.add(novaPostagem);
		} else if (tipoDeUsuario.getTipoPopularidade().equals("Celebridade")) {
			this.feedCelebridade.add(novaPostagem);
		} else {
			this.feedIcone.add(novaPostagem);
		}
	}

	Comparator<Postagem> compararData = new Comparator<Postagem>() {

		@Override
		public int compare(Postagem postagem, Postagem outraPostagem) {
			try {
				return postagem.getData().compareTo(outraPostagem.getData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	};

	Comparator<Postagem> compararPops = new Comparator<Postagem>() {

		@Override
		public int compare(Postagem postagem, Postagem outraPostagem) {
			return postagem.getPops() - outraPostagem.getPops();

		}
	};
}
