package core;

public class UsuarioNormal implements ITipoDeUsuario{

	private String tipoPopularidade = "Normal Pop";
	
	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(10);
		
	}

	@Override
	public void descurtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(10);
	}
	
	@Override
	public String getTipoPopularidade() {
		return this.tipoPopularidade;
	}

}
