package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeedNoticias {

	ArrayList<Postagem> feedNormal = new ArrayList<Postagem>();
	ArrayList<Postagem> feedCelebridade = new ArrayList<Postagem>();
	ArrayList<Postagem> feedIcone = new ArrayList<Postagem>();
	public Map<Integer, ArrayList<Postagem>> feeds= new HashMap<Integer, ArrayList<Postagem>>();
	ArrayList<Postagem> feedExibido = new ArrayList<Postagem>();
	ArrayList<Postagem> feedAtualizado = new ArrayList<Postagem>();

	
	public void atualizaFeed() {
	}
//		for (int i = 0; i < 3; i++) {
//			Collections.sort(feedsDeNoticia[i], new Comparator<Postagem>() {
//				@Override
//				public int compare(Postagem postagem1, Postagem postagem2) {
//					return 0;
//				}
//				
//			});
//			
//		}
//	}

	public HashMap<Integer, ArrayList<Postagem>> getFeeds() {
		return feeds;
	}
	public void getFeedPopularidade() {
		// ordena os posts por popularidade
	}

	public void getFeedTempo() {
		// ordena os post por tempo
	}

	public void adicionaPostagem(Postagem novaPostagem, ITipoDeUsuario tipoDeUsuario) {
		if(tipoDeUsuario.getTipoPopularidade().equals("Normal")){
			this.feedNormal.add(novaPostagem);
		}
		else if(tipoDeUsuario.getTipoPopularidade().equals("Celebridade")){
			this.feedCelebridade.add(novaPostagem);
		}else{
			this.feedIcone.add(novaPostagem);
		}
	}

}
