package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class BancoDeDados {

	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	private ArrayList<String> trendHastags = new ArrayList<String>();
	private BancoHashtags bancoHashtags = BancoHashtags.getInstance();
	private HashMap<String, Integer> hashtagsMap;

	public Usuario buscaUsuario(String emailUsuario) {
		Usuario user = null;
		for (Usuario usuario : getListaUsuario()) {
			if (usuario.getEmail().equals(emailUsuario))
				user = usuario;
		}
		return user;
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario) throws Exception {
		String informacaoRequerida = "";
		Usuario usuarioRequerido = buscaUsuario(emailUsuario);

		if (usuarioRequerido == null) {

			throw new Exception("Um usuarix com email " + emailUsuario + " nao esta cadastradx.");
		}

		switch (nomeInformacao.toUpperCase()) {
		case "SENHA":
			throw new Exception("A senha dx usuarix eh protegida.");
		case "NOME":
			informacaoRequerida = usuarioRequerido.getNome();
			break;
		case "FOTO":
			informacaoRequerida = usuarioRequerido.getFoto();
			break;
		case "EMAIL":
			informacaoRequerida = usuarioRequerido.getEmail();
			break;
		case "DATA DE NASCIMENTO":
			String data = usuarioRequerido.getData();
			SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String output = myFormat.format(input.parse(data));
			informacaoRequerida = output;
			break;
		default:
			break;
		}

		return informacaoRequerida;
	}

	public void atualizaPerfil(Controller controller, String nomeInformacao, String valor) throws Exception {
		controller.validadores.validarUsuarioLogado(controller.usuarioLogado, "");
		
		switch (nomeInformacao.toUpperCase()) {
		case "NOME":
			if (valor.equals("")) {
				throw new Exception("Nome dx usuarix nao pode ser vazio.");
			}
			controller.usuarioLogado.setNome(valor);
			break;
		case "FOTO":
			controller.usuarioLogado.setFoto(valor);
			break;
		case "EMAIL":
			if (controller.validadores.validaEmail(valor) == false) {
				throw new Exception("Formato de e-mail esta invalido.");
			}
			controller.usuarioLogado.setEmail(valor);
			break;
		case "DATA DE NASCIMENTO":
			controller.validadores.validaData(valor);
			controller.usuarioLogado.setData(valor);
			break;

		default:
			throw new Exception();
		}
	}


	// implementar depois
	public String ordenaUsuario() {
		Collections.sort(this.listaUsuario);
		String retorno = "Mais Populares:";
		for (int i = 0; i < 3; i++) {
			retorno += String.format(" (%d) %s %d;", i + 1, this.listaUsuario.get(i).getNome(),
					this.listaUsuario.get(i).getPops());
		}
		retorno += " | Menos Populares:";
		for (int i = this.listaUsuario.size() - 1; i > this.listaUsuario.size() - 4; i--) {
			retorno += String.format(" (%d) %s %d;", this.listaUsuario.size() - i, this.listaUsuario.get(i).getNome(),
					this.listaUsuario.get(i).getPops());
		}
		return retorno;

	}

	public void ordenaHashtags() {
		this.trendHastags.clear();
		HashMap<String, Integer> hashtagsFrequencia = new HashMap<String, Integer>();
		for (String string : this.bancoHashtags.getHashtags()) {
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
				return hashtagsFrequencia.get(y) - hashtagsFrequencia.get(x);
			}
		});
		for (int i = 0; i < 3; i++) {
			this.trendHastags.add(list.get(i));
		}
		this.hashtagsMap = hashtagsFrequencia;
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
		return hashtags;
	}

	public String getTrendingTopics() {
		String retorno = "Trending Topics: ";
		for (int i = 0; i < 3; i++) {
			retorno += String.format(" (%d) %s: %d;", i + 1, this.trendHastags.get(i),
					this.hashtagsMap.get(this.trendHastags.get(i)));
		}
		return retorno;
	}

	public void removeUsuario(String email) {
		Usuario user = this.buscaUsuario(email);
		this.listaUsuario.remove(user);

	}

	public Usuario get(int i) {
		return getListaUsuario().get(i);
	}

	public int size() {
		return getListaUsuario().size();
	}

	public void remove(int i) {
		getListaUsuario().remove(i);
	}

	public ArrayList<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

}
