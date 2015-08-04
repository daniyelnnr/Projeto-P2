package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

	private Usuario usuarioLogado = null;
	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar)
			throws Exception {
		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception(
					"Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		} else if (this.validaData(dataNasUsuario)
				&& this.validaEmail(emailUsuario)) {
			this.validaData(dataNasUsuario);
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario, imgAvatar);
			listaUsuario.add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception(
					"Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		}

	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) throws Exception {

		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception(
					"Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		} else if (this.validaData(dataNasUsuario)
				&& this.validaEmail(emailUsuario)) {
			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario);
			listaUsuario.add(usuario);
			return usuario.getEmail();
		} else {
			throw new Exception(
					"Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		}

	}

	public boolean login(String emailUsuario, String senhaUsuario)
			throws Exception {
		if (this.usuarioLogado == null) {
			Usuario user = this.buscaUsuario(emailUsuario);
			if (user == null) {
				throw new Exception(
						"Nao foi possivel realizar login. Um usuarix com email "
								+ emailUsuario + " nao esta cadastradx.");
			}

			else if (user.getSenha().equals(senhaUsuario)) {
				usuarioLogado = user;
				return true;
			}

			else {
				throw new Exception(
						"Nao foi possivel realizar login. Senha invalida.");
			}
		} else {
			throw new Exception(
					"Nao foi possivel realizar login. Um usuarix ja esta logadx: "
							+ this.usuarioLogado.getNome() + ".");
		}
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario)
			throws Exception {
		String informacaoRequerida = "";
		Usuario usuarioRequerido = this.buscaUsuario(emailUsuario);

		if (usuarioRequerido == null) {

			throw new Exception("Um usuarix com email " + emailUsuario
					+ " nao esta cadastradx.");
		}

		else if (nomeInformacao.equals("Senha")
				|| nomeInformacao.equals("SENHA")
				|| nomeInformacao.equals("senha")) {
			throw new Exception("A senha dx usuarix eh protegida.");
		}

		else if (nomeInformacao.equals("Nome") || nomeInformacao.equals("NOME")
				|| nomeInformacao.equals("nome")) {
			informacaoRequerida = usuarioRequerido.getNome();
		}

		else if (nomeInformacao.equals("Foto") || nomeInformacao.equals("FOTO")
				|| nomeInformacao.equals("foto")) {
			informacaoRequerida = usuarioRequerido.getFoto();
		}

		else if (nomeInformacao.equals("Email")
				|| nomeInformacao.equals("EMAIL")
				|| nomeInformacao.equals("email")) {
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

	public String getInfoUsuarioLogado(String nomeInformacao) throws Exception {
		return this.getInfoUsuario(nomeInformacao,
				this.usuarioLogado.getEmail());

	}

	public String getNome(String emailUsuario) {
		Usuario user = this.buscaUsuario(emailUsuario);
		return user == null ? null : user.getNome();
	}

	public void logout() throws Exception {
		if (this.usuarioLogado != null) {
			this.usuarioLogado = null;
		} else {
			throw new Exception(
					"Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		}
	}

	public void removeUsuario(String email) {
		for (int i = 0; i < listaUsuario.size(); i++) {
			if (listaUsuario.get(i).getEmail().equals(email)) {
				listaUsuario.remove(i);
			}

		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor) throws Exception {
	
		if(usuarioLogado == null){
			throw new Exception("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
		
		if (nomeInformacao.equalsIgnoreCase("Nome")) {
			if(valor.equals("")){
				throw new Exception("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
			}
			this.usuarioLogado.setNome(valor);
		} else if (nomeInformacao.equalsIgnoreCase("foto")) {
			this.usuarioLogado.setFoto(valor);
		} else if (nomeInformacao.equalsIgnoreCase("E-mail")) {
			if(validaEmail(valor) == false){
				throw new Exception("Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
			}
			this.usuarioLogado.setEmail(valor);
		} else if (nomeInformacao.equalsIgnoreCase("Data de Nascimento")) {
			if(this.validaData(valor) == false){
				throw new Exception("Erro na atualizacao de perfil. Formato de data esta invalida.");
			}
			this.usuarioLogado.setData(valor);
		}else{
			throw new Exception();
		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor, String velhaSenha) throws Exception{
		if(usuarioLogado == null){
			throw new Exception("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
		if ((nomeInformacao.equalsIgnoreCase("Senha")) && (this.usuarioLogado.getSenha().equals(velhaSenha))) {
				this.usuarioLogado.setSenha(valor);
		}else{
			throw new Exception("Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
		}
	}
	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public Usuario buscaUsuario(String emailUsuario) {
		Usuario user = null;
		for (Usuario usuario : listaUsuario) {
			if (usuario.getEmail().equals(emailUsuario))
				user = usuario;
		}
		return user;
	}

	public void postarMensagem(String conteudo, String data) throws Exception {
		if (this.usuarioLogado == null) {
			throw new Exception(
					"Nao eh possivel postar mensagem. Nenhum usuario esta logado no +pop.");
		}

		if (this.verificaTamanho(conteudo) == true) {
			Postagem novaPostagem = new Postagem(conteudo, data);
			this.usuarioLogado.adicionarPostagemAoPerfil(novaPostagem);
			enviaPostagemParaAmgios(novaPostagem);

		} else {
			throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		}

	}

	private void enviaPostagemParaAmgios(Postagem novaPostagem) {
		for (Usuario usuario : usuarioLogado.getAmigos()) {
			usuario.adicionarMensagemAoMural(novaPostagem);
		}
	}
	
	private boolean verificaTamanho(String mensagem){
			if (mensagem.indexOf("#") <= 200){
				return true;
			}
			
			else{
				return false;
			}
		}
	
	

	private boolean validaEmail(String email) {
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
						throw new Exception(
								"Erro no cadastro de Usuarios. Data nao existe.");
					}
				}
				return true;
			} else {
				throw new Exception(
						"Erro no cadastro de Usuarios. Formato de data esta invalida.");
			}
		} else {
			throw new Exception(
					"Erro no cadastro de Usuarios. Formato de data esta invalida.");
		}
	}
}
