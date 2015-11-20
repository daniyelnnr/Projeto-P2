/**
 * Projeto LP2 - 2014.2
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * Classe que serve como banco de dados, armazenando e manipulando os usuarios do sistema.
 */
package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BancoDeDados {

	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	HashMap<String, Integer> hashtagsMap;//nao esta sendo usado?
	
	/**
	 * Metodo que ira buscar um usuario na lista de usuarios armazenados.
	 * @param Recebe o email de um usuario cadastrado.
	 * @return Retorna um usuario, se foi encontrado. Retorna null caso o usuario nao foi encontrado.
	 */
	public Usuario buscaUsuario(String emailUsuario) {
		Usuario user = null;
		for (Usuario usuario : getListaUsuario()) {
			if (usuario.getEmail().equals(emailUsuario))
				user = usuario;
		}
		return user;
	}
	
	/**
	 * Metodo que ira retornar uma informacao do usuario.
	 * @param Recebe qual eh a informacao a ser retornada.
	 * @param Recebe o email do usuario que sera buscado para ter a informacao extraida.
	 * @return Retorna a informacao especificada do usuario, na forma de String.
	 * @throws Lanca excecao quando algum metodo chamado internamente lance outra excecao ou haja alguma informacao invalida.
	 */
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
	
	/**
	 * Metodo que atualiza as informacoes 
	 * @param controller
	 * @param nomeInformacao
	 * @param valor
	 * @throws Exception
	 */
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
