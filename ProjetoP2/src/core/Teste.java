package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Teste {
	private static ArrayList<String> hashtagsAll = new ArrayList<String>();
	public static List<String> ordenaHashtags(ArrayList<String> hashtagsAll){
		//aqui tem que fazer um for na lista "tags" e ordenalas por ordem de frequencia
		HashMap<String, Integer> hashtagsFrequencia = new HashMap();
		for (String string : hashtagsAll) {
			if(hashtagsFrequencia.containsKey(string)){
				int frequenciaAtual = hashtagsFrequencia.get(string);
				frequenciaAtual++;
				hashtagsFrequencia.replace(string, frequenciaAtual);
			}else{
				hashtagsFrequencia.put(string, 1);
			}
		}
		List<String> list = new ArrayList<String>(hashtagsFrequencia.keySet());
		Collections.sort(list, new Comparator<String>() {
		    @Override
		    public int compare(String x, String y) {
		        return hashtagsFrequencia.get(y) - hashtagsFrequencia.get(x);
		    }
		});
		return list;
	}
	public static void main(String[] args) {
		hashtagsAll.add("#a");
		hashtagsAll.add("#d");
		hashtagsAll.add("#a");
		hashtagsAll.add("#a");
		hashtagsAll.add("#c");
		hashtagsAll.add("#b");
		hashtagsAll.add("#b");
		hashtagsAll.add("#c");
		hashtagsAll.add("#d");
		hashtagsAll.add("#d");
		System.out.println(hashtagsAll);
		System.out.println(ordenaHashtags(hashtagsAll));
	}		
}
