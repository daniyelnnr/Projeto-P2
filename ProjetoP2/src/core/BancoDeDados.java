package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class BancoDeDados {

	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	private ArrayList<String> usuariosMenosPop = new ArrayList<String>();
	private Usuario[] trendHastags = new Usuario[3];
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
	}

	

}
