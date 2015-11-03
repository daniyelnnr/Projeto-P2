package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class BancoDeDados {

	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	private ArrayList<String> usuariosMenosPop = new ArrayList<String>();
	private ArrayList<String> hashtagsAll = new ArrayList<String>();
	private ArrayList<String> trendHastags = new ArrayList<String>();
	private ArrayList<String> usuariosMaisPop = new ArrayList<String>();
	
	
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

	public Usuario buscaUsuario(String emailUsuario) {
		Usuario user = null;
		for (Usuario usuario : getListaUsuario()) {
			if (usuario.getEmail().equals(emailUsuario))
				user = usuario;
		}
		return user;
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario)
			throws Exception {
		String informacaoRequerida = "";
		Usuario usuarioRequerido = buscaUsuario(emailUsuario);

		if (usuarioRequerido == null) {

			throw new Exception("Um usuarix com email " + emailUsuario
					+ " nao esta cadastradx.");
		}

		else if (nomeInformacao.equalsIgnoreCase("Senha")) {
			throw new Exception("A senha dx usuarix eh protegida.");
		}

		else if (nomeInformacao.equalsIgnoreCase("Nome")) {
			informacaoRequerida = usuarioRequerido.getNome();
		}

		else if (nomeInformacao.equalsIgnoreCase("Foto")) {
			informacaoRequerida = usuarioRequerido.getFoto();
		}

		else if (nomeInformacao.equalsIgnoreCase("Email")) {
			informacaoRequerida = usuarioRequerido.getEmail();
		}

		else {
			String data = usuarioRequerido.getData();
			SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String output = myFormat.format(input.parse(data));
			informacaoRequerida = output;
		}

		return informacaoRequerida;
	}

	public void removeUsuario(String email) {
		Usuario user = this.buscaUsuario(email);
		this.listaUsuario.remove(user);

	}

	public void atualizaPerfil(Controller controller, String nomeInformacao, String valor)
			throws Exception {
		controller.validadores.validarUsuarioLogado(controller.usuarioLogado, "");
	
		if (nomeInformacao.equalsIgnoreCase("Nome")) {
			if (valor.equals("")) {
				throw new Exception("Nome dx usuarix nao pode ser vazio.");
			}
			controller.usuarioLogado.setNome(valor);
		} else if (nomeInformacao.equalsIgnoreCase("foto")) {
			controller.usuarioLogado.setFoto(valor);
		} else if (nomeInformacao.equalsIgnoreCase("E-mail")) {
			if (controller.validadores.validaEmail(valor) == false) {
				throw new Exception("Formato de e-mail esta invalido.");
			}
			controller.usuarioLogado.setEmail(valor);
		} else if (nomeInformacao.equalsIgnoreCase("Data de Nascimento")) {
			controller.validadores.validaData(valor);
			controller.usuarioLogado.setData(valor);
		} else {
			throw new Exception();
		}
	}
	
	// implementar depois
	public void ordenaUsuario(){
		Collections.sort(this.listaUsuario);
		for (int i = 0; i < 3; i++) {
			this.usuariosMaisPop.add(this.listaUsuario.get(i).getNome());
		}
		for (int i = this.listaUsuario.size(); i < this.listaUsuario.size()-3; i--) {
			this.usuariosMenosPop.add(this.listaUsuario.get(i).getNome());
		}
		
	}
	
	public void ordenaHashtags(){
		//aqui tem que fazer um for na lista "tags" e ordenalas por ordem de frequencia
		HashMap<String, Integer> hashtagsFrequencia = new HashMap<String, Integer>();
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
		for (int i = 0; i < 3; i++) {
			this.trendHastags.add(list.get(i));
		}
	}

	public ArrayList<String> pegaHastags(String conteudo) throws Exception {
		int index = conteudo.indexOf("#");
		String resto = conteudo.substring(index, conteudo.length());
		ArrayList<String> hashtags = new ArrayList<String>();
		for (String hashtag : resto.split(" ")) {
			if (!hashtag.startsWith("#"))
				throw new Exception(
						"Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '"
								+ hashtag + "'.");
			hashtags.add(hashtag);
		}
		this.hashtagsAll = hashtags;
		return hashtags;
	}

	

}
