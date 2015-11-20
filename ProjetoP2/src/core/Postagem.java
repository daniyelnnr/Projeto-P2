package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * Projeto LP2 - 2014.2
 * 
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 *
 *         Classe responsavel por representar uma Postagem e todas suas
 *         funcionalidades.
 */

public class Postagem implements Comparable<Postagem> {
	private String mensagem;
	private String data;
	private int pops;
	private int likes;
	private int deslikes;
	private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<String> conteudo = new ArrayList<String>();
	private BancoHashtags bancoHashtags = BancoHashtags.getInstance();

	public Postagem(String mensagem, ArrayList<String> hashtags, String data) {
		this.mensagem = mensagem;
		this.data = data;
		this.setPops(0);
		this.setLikes(0);
		this.setDeslikes(0);
		this.setTags(hashtags);
		this.refinaMensagem(this.mensagem);
	}

	/**
	 * Metodo que separa a postagem em partes , podendo ser: mensagem, imagem,
	 * audio e hashtags.
	 * 
	 * @param mensagem
	 */
	public void refinaMensagem(String mensagem) {
		Pattern mensagemPadrao = Pattern.compile(
				"(((?<mensagem>^.+?)(?=\\s[#<]))|((?<=<(?<multimidia>imagem|audio)>)(?<caminho>\\S*)(?=</(imagem|audio)>)))");
		Matcher multimidiaPadrao = mensagemPadrao.matcher(mensagem);
		while (multimidiaPadrao.find()) {
			if (multimidiaPadrao.group("mensagem") != null) {
				this.conteudo.add(multimidiaPadrao.group("mensagem"));

			}
			if (multimidiaPadrao.group("multimidia") != null) {

				if (multimidiaPadrao.group("multimidia").startsWith("i")) {
					this.conteudo.add("$arquivo_imagem:" + multimidiaPadrao.group("caminho"));
				}
				if (multimidiaPadrao.group("multimidia").startsWith("a")) {
					this.conteudo.add("$arquivo_audio:" + multimidiaPadrao.group("caminho"));
				}
			}
		}

	}

	@Override
	public String toString() {
		String retorno = this.mensagem;
		for (String string : tags) {
			retorno += " ";
			retorno += string;
		}
		try {
			retorno += " ";
			retorno += String.format("(%s)", this.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Metodo responsavel por recuperar uma parte especifica da postagem.
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public String getConteudo(int index) throws Exception {
		if (index < 0) {
			throw new Exception(
					"Requisicao invalida. O indice deve ser maior ou igual a zero.");
		}
		if ((this.conteudo.size()) < index + 1) {
			throw new Exception("Item #" + index
					+ " nao existe nesse post, ele possui apenas "
					+ conteudo.size() + " itens distintos.");
		}
		return conteudo.get(index);
	}

	public String getTagsEspaco() {
		String txt = "";
		for (String tag : getTags()) {
			txt += tag + " ";
		}
		return txt.substring(0, txt.length() - 1);
	}

	public String getTagsVirgula() {
		String txt = "";
		for (String tag : getTags()) {
			txt += tag + ",";
		}
		return txt.substring(0, txt.length() - 1);
	}

	/**
	 * Metodo responsavel por recuperar a data, e formata-la de acordo com a
	 * saida requisitada pelo programa.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getData() throws Exception {
		String data = this.data;
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String output = myFormat.format(input.parse(data));
		return output;
	}

	/**
	 * Retorna a data do formato Original.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDataOutroFormato() throws Exception {
		String data = this.data;
		return data;
	}

	/**
	 * Metodo responsavel por adicionar uma hashtag no banco de hashtags.
	 * 
	 * @param tag
	 */
	public void addTag(String tag) {
		if (!this.getTags().contains(tag)) {
			this.getTags().add(tag);
			this.bancoHashtags.adicionaHashtags(tag);
		}
	}

	@Override
	public int compareTo(Postagem postagem) {
		return 0;
	}

	/**
	 * Retorna os pontos de popularidade de uma postagem.
	 * 
	 * @return
	 */
	public int getPops() {
		return pops;
	}

	/**
	 * Aplica novos valores de pontos de popularidade.
	 * 
	 * @param pops
	 */
	public void setPops(int pops) {
		this.pops = pops;
	}

	/**
	 * Retorna numero de "descurtidas".
	 * 
	 * @return
	 */
	public int getDeslikes() {
		return deslikes;
	}

	/**
	 * Aplica novos valores de "descurtidas".
	 * 
	 * @param deslikes
	 */
	public void setDeslikes(int deslikes) {
		this.deslikes = deslikes;
	}

	/**
	 * Retorna a mensagem do post.
	 * 
	 * @return
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * Atribui pontos a uma determinada postagem.
	 * 
	 * @param pontos
	 */
	public void atribuirPontos(int pontos) {
		pops += pontos;
	}

	public void setNewDeslikes() {
		this.deslikes++;
	}

	/**
	 * Recupera a lista de Hashtags.
	 * 
	 * @return
	 */
	public ArrayList<String> getArrayTags() {
		return this.getTags();
	}

	/**
	 * Retorna a quantidade de Curtidas.
	 * 
	 * @return
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * Adiciona uma Curtida a postagem.
	 */
	public void setNewLikes() {
		this.setLikes(this.getLikes() + 1);
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * Aplica um novo valor de lista de hashtags.
	 * 
	 * @param tags
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public ArrayList<String> getConteudo() {
		return this.conteudo;
	}

}
