package core;

public class Amizade {

	public void aceitaAmizade(Controller controller, String email) throws Exception {
		Usuario usuario = controller.bancodedados.buscaUsuario(email);
		controller.usuarioLogado.aceitaAmizade(usuario);
		usuario.adicionaAmigo(controller.usuarioLogado);
		usuario.notificacoes.add(controller.usuarioLogado.getNome()
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
