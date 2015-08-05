package Exceptions;

public class ErroCadastro extends Exception {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroCadastro(String message) {
		    super("Erro no cadastro de Usuarios. " + message);
		  }
}
