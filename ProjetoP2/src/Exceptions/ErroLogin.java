package Exceptions;

public class ErroLogin extends Exception {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroLogin(String message) {
		    super("Nao foi possivel realizar login. " + message);
		  }
}
