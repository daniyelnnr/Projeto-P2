package core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * Projeto LP2 - 2014.2
 * 
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 *         Classe concreta do tipo de usuario. Participa do design de Strategy
 *         implementado no codigo. E sobre escreve metodos para que se comportem
 *         de manera polimorfica para cada tipo de usuario.
 *
 */
public class UsuarioCelebridade implements ITipoDeUsuario {

	private String tipoPopularidade = "Celebridade Pop";

	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem)
			throws Exception {
		int pontos = 25;
		if (verificaData(postagem)) {
			pontos += 10;
		}
		usuarioAmigo.atribuirPontos(pontos);
		postagem.atribuirPontos(pontos);
	}

	private boolean verificaData(Postagem postagem) throws Exception {
		DateFormat dataCurtida = new SimpleDateFormat("yyyy/MM/dd");
		if (postagem.getData().substring(0, 11).equals(dataCurtida)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void descurtir(Usuario usuarioAmigo, Postagem postagem)
			throws Exception {
		int pontos = -25;
		if (verificaData(postagem)) {
			pontos -= 10;
		}
		usuarioAmigo.atribuirPontos(pontos);
		postagem.atribuirPontos(pontos);
	}

	@Override
	public String getTipoPopularidade() {
		return this.tipoPopularidade;
	}

}
