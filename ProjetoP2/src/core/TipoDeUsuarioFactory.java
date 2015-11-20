package core;

/**
 * 
 * Projeto LP2 - 2014.2
 * 
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * 
 *         Fabrica dos tipos de Usuario, responsavel por definir de forma
 *         polimorfica o tipo de usuario. Importante comentar que esta é uma
 *         classe Singleton.
 *
 */
public class TipoDeUsuarioFactory {

	private static TipoDeUsuarioFactory instance;

	/**
	 * Projeto LP2 - 2014.2
	 * 
	 * @author Daniyel Rocha 114210779
	 * @author Igor Pinheiro 114210164
	 * @author Matheus Maia 114210417
	 * 
	 *         Como tratasse de uma classe singleton, este metodo retorna a
	 *         propria instancia dela.
	 * @return
	 */
	public static TipoDeUsuarioFactory getInstance() {
		if (instance == null)
			instance = new TipoDeUsuarioFactory();
		return instance;
	}

	/**
	 * Cria o tipo de usuario de acordo com a quantidade de pontos de
	 * popularidade.
	 * 
	 * @param pops
	 * @return
	 */
	public ITipoDeUsuario createTipoDeUsuarioStrategy(int pops) {
		if (pops < 1000 && pops > 500) {
			return new UsuarioCelebridade();
		}
		if (pops >= 1000) {
			return new UsuarioIcone();
		}
		return new UsuarioNormal();
	}
}
