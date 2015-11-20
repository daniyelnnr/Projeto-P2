package core;

/**
 *  Projeto LP2 - 2014.2
 * 
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 *
 *         Classe Interface que irá ser utilizada para implementar a Strategy e
 *         auxiliar na mudança dinamica do tipo de usuario.
 */
public interface ITipoDeUsuario {

	public void curtir(Usuario usuarioAmigo, Postagem postagem)
			throws Exception;

	public void descurtir(Usuario usuarioAmigo, Postagem postagem)
			throws Exception;

	public String getTipoPopularidade();

}
