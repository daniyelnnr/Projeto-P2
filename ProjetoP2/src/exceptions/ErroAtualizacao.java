package exceptions;

public class ErroAtualizacao extends ErroDeLogica {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroAtualizacao(String message) {
		    super("Erro na atualizacao de perfil. " + message);
		  }

}
