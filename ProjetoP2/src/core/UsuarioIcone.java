package core;

public class UsuarioIcone implements ITipoDeUsuario {
	

	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(50);
		postagem.getTags();
		
	}

}
