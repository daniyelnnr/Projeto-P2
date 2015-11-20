package exceptions;

public class ErroCadastro extends ErroDeEntrada {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroCadastro(String message) {
		    super("Erro no cadastro de Usuarios. " + message);
		  }
}
