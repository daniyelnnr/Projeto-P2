package core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioCelebridade implements ITipoDeUsuario {


	@Override
	public void curtir(Usuario usuarioAmigo, Postagem postagem) {
		int pontos = 25;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		if(postagem.dataBasico.DATE_FIELD == dateFormat.DATE_FIELD){
			pontos += 10;
		}
		usuarioAmigo.atribuirPontos(pontos);
	}




}
