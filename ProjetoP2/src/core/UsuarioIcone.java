package core;

public class UsuarioIcone implements ITipoDeUsuario {
	
	private String tipoPopularidade = "Icone Pop";

	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(50);
		postagem.addTag("#EpicWin");
		
	}

	@Override
	public void descurtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(-50);
		postagem.addTag("#EpicFail");
	}

	@Override
	public String getTipoPopularidade() {
		return this.tipoPopularidade;
	}

}
