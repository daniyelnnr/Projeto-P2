package core;

public interface ITipoDeUsuario {


	public void curtir(Usuario usuarioAmigo, Postagem postagem) throws Exception;
	public void descurtir(Usuario usuarioAmigo, Postagem postagem) throws Exception;
	public String getTipoPopularidade();
	
}
