package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BancoDeDados {

	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	HashMap<String, Integer> hashtagsMap;

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
