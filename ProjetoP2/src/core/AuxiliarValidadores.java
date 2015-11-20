/**
 * Projeto LP2 - 2014.2
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * Classe que realiza validacoes de email, data e usuario logado no sistema.
 */
package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import exceptions.ErroUsuarioOffline;

public class AuxiliarValidadores {
	
	/**
	 * Metodo que realiza a validacao de um email.
	 * @param Tem como parametro o email a ser verificado.
	 * @return Retorna um booleano confirmando ou nao a validade do email.
	 */
	public boolean validaEmail(String email) {
		int count = 0;
		for (char c : email.toCharArray()) {
			if (c == '@')
				count++;
		}
		if (count == 1) {
			int atPos = email.lastIndexOf("@");
			if (atPos != -1) {
				String subs = email.substring(0, atPos);
				if (subs.isEmpty()) {
					return false;
				}
				subs = email.substring(atPos + 1, email.length());
				if (subs.isEmpty()) {
					return false;
				} else {
					if (subs.contains(".")) {
						String subs1 = subs.substring(0, subs.indexOf('.'));
						if (subs1.isEmpty()) {
							return false;
						}
						if (subs.endsWith(".com") || subs.endsWith(".com.br")) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo que realiza a validacao de uma data.
	 * @param Tem como parametro a data a ser verificada.
	 * @return Retorna um booleano confirmando ou nao a validade da data. 
	 * @throws Lanca excecao caso a data esteja com o formato invalido ou se ela nao existir.
	 */
	public boolean validaData(String data) throws Exception {
		int count = 0;
		for (char c : data.toCharArray()) {
			if (c == '/')
				count++;
			if (Character.isLetter(c)) {
				throw new Exception("Formato de data esta invalida.");
			}
		}
		if (count == 2) {
			String dia = data.substring(0, data.indexOf("/"));
			String mes = data.substring(data.indexOf("/") + 1, data.lastIndexOf("/"));
			String ano = data.substring(data.lastIndexOf("/") + 1, data.length());
			if (dia.length() == 2 && mes.length() == 2 && ano.length() == 4) {
				SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
				input.setLenient(false);
				try {
					input.parse(data);
				} catch (ParseException e) {
					if (e.getLocalizedMessage().startsWith("Unparseable date")) {
						throw new Exception("Data nao existe.");
					}
				}
				return true;
			} else {
				throw new Exception("Formato de data esta invalida.");
			}
		} else {
			throw new Exception("Formato de data esta invalida.");
		}
	}

	/**
	 * Metodo que realiza a verificacao de um usuario logado no sistema.
	 * @param Tem como parametro o usuario que esta sendo verificado se esta logado.
	 * @param Passa a mensagem da excecao que foi lancada.
	 * @throws Lanca uma excecao indicando que nao ha usuario logado.
	 */
	public void validarUsuarioLogado(Object usuarioLogado, String menssagem) throws ErroUsuarioOffline {
		if (usuarioLogado == null) {//usuarioLogado deve ser do tipo OBJECT?
			throw new ErroUsuarioOffline(menssagem);
		}
	}

}
