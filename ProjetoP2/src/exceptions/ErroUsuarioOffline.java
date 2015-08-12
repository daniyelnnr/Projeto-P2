package exceptions;

public class ErroUsuarioOffline extends Exception {
	private static final long serialVersionUID = 1L;

	public ErroUsuarioOffline(String message) {
		    super(message + "Nenhum usuarix esta logadx no +pop.");
		  }
}

