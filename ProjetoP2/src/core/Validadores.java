package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import exceptions.ErroUsuarioOffline;

public class Validadores {

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
			String mes = data.substring(data.indexOf("/") + 1,
					data.lastIndexOf("/"));
			String ano = data.substring(data.lastIndexOf("/") + 1,
					data.length());
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

	public void validarUsuarioLogado(Object usuarioLogado, String menssagem) throws ErroUsuarioOffline{
		if (usuarioLogado == null) {
			throw new ErroUsuarioOffline(menssagem);
		}
	}

}
