package core;

public class Amizade {

	public void aceitaAmizade(Usuario usuario2, BancoDeDados bancoDeDados, String email) throws Exception {
		Usuario usuario = bancoDeDados.buscaUsuario(email);
		usuario2.aceitaAmizade(usuario);
		usuario.adicionaAmigo(usuario2);
		usuario.notificacoes.add(usuario2.getNome()
				+ " aceitou sua amizade.");
	}

	public void adicionaAmigo(Controller controller, String email) {
		Usuario usuario = controller.bancodedados.buscaUsuario(email);
		usuario.notificacoes.add(controller.usuarioLogado.getNome()
				+ " quer sua amizade.");
		usuario.pedidosAmizade.add(controller.usuarioLogado);
	}

	public void removeAmigo(Controller controller, String email) {
		Usuario usuario = controller.bancodedados.buscaUsuario(email);
		usuario.notificacoes.add(controller.usuarioLogado.getNome()
				+ " removeu a sua amizade.");
		usuario.amigos.remove(controller.usuarioLogado);
		controller.usuarioLogado.removeAmigo(usuario);
	}

	public void rejeitaAmizade(Controller controller, String email) throws Exception {
		Usuario usuario = controller.bancodedados.buscaUsuario(email);
		if (usuario == null) {
			throw new Exception("O usuario " + email
					+ " nao esta cadastrado no +pop.");
		}
		controller.usuarioLogado.rejeitaAmizade(usuario);
		usuario.notificacoes.add(controller.usuarioLogado.getNome()
				+ " rejeitou sua amizade.");
	
	}

}
