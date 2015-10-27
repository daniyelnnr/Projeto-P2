package core;

public class TipoDeUsuarioFactory {

	private static TipoDeUsuarioFactory instance;
	
	
	public static TipoDeUsuarioFactory getInstance() {
		if (instance == null)
			instance = new TipoDeUsuarioFactory();
		return instance;
	}
	
	public ITipoDeUsuario createTipoDeUsuarioStrategy(int pops) {
		if(pops<1000 && pops>500){
			return new UsuarioCelebridade();
		}
		if(pops>=1000){
			return new UsuarioIcone();
		}
		return new UsuarioNormal();
	}
}
