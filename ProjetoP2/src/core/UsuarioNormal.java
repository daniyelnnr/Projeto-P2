package core;

public class UsuarioNormal implements ITipoDeUsuario{



	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		usuarioAmigo.atribuirPontos(10);
		
	}

}
