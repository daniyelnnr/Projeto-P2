package exceptions;

public class ErroLogin extends ErroDeLogica {
	private static final long serialVersionUID = 1L;

	public ErroLogin(String message) {
		    super("Nao foi possivel realizar login. " + message);
		  }
}
