package core;

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
public class UsuarioIcone implements ITipoDeUsuario {

	private String tipoPopularidade = "Icone Pop";

	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(50);
		postagem.atribuirPontos(50);
		postagem.addTag("#epicwin");
	}

	@Override
	public void descurtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(-50);
		postagem.atribuirPontos(-50);
		postagem.addTag("#epicfail");
	}

	@Override
	public String getTipoPopularidade() {
		return this.tipoPopularidade;
	}

}
