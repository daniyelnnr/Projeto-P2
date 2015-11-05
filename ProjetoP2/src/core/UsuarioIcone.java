package core;

public class UsuarioIcone implements ITipoDeUsuario {
	
	private String tipoPopularidade = "Icone Pop";

	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(50);
		postagem.addTag("#epicwin");
		
	}

	@Override
	public void descurtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(-50);
		postagem.addTag("#epicfail");
	}

	@Override
	public String getTipoPopularidade() {
		return this.tipoPopularidade;
	}

}
