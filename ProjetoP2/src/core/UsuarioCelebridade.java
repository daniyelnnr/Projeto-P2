package core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioCelebridade implements ITipoDeUsuario {

	private String tipoPopularidade = "Celebridade Pop";

	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		int pontos = 25;
		if(verificaData(postagem)){
			pontos += 10;
		}
		usuarioAmigo.atribuirPontos(pontos);
		postagem.atribuirPontos(pontos);
	}

	private boolean verificaData(Postagem postagem) {
		DateFormat dataCurtida = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		if(postagem.dataBasico.DATE_FIELD == dataCurtida.DATE_FIELD){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void descurtir(Usuario usuarioAmigo, Postagem postagem) {
		int pontos = -25;
		if(verificaData(postagem)){
			pontos -= 10;
		}
		usuarioAmigo.atribuirPontos(pontos);
		postagem.atribuirPontos(pontos);
	}

	@Override
	public String getTipoPopularidade() {
		return this.tipoPopularidade;
	}




}
