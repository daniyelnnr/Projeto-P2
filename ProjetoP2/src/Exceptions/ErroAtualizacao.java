package Exceptions;

public class ErroAtualizacao extends Exception {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroAtualizacao(String message) {
		    super("Erro na atualizacao de perfil. " + message);
		  }

}
