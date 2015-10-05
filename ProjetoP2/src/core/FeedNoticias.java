package core;

import java.util.ArrayList;

public class FeedNoticias {
	
	ArrayList<Postagem> feedExibido = new ArrayList<Postagem>();
	ArrayList<Postagem> feedAtualizado = new ArrayList<Postagem>();
	DataComparator comparator1 = new DataComparator();
	PopsComparator comparator2 = new PopsComparator();
	
	public void atualizaFeed(){	
	//alterar o feed exibido
	}
	
	public void getFeedPopularidade(){
	// ordena os posts por popularidade	
	}
	
	public void getFeedTempo(){
	// ordena os post por tempo
	}
	
}
