package br.edu.ifsp.arqdsw2.taskAPI.controller.command;

import br.edu.ifsp.arqdsw2.taskAPI.model.dao.TarefaDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeletarTarefaCommand implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getPathInfo();
		if (path == null || !path.matches("^/\\d+$")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da tarefa inválido.");
			return;
		}
		int id = Integer.parseInt(path.substring(1));
		TarefaDAO dao = new TarefaDAO();
		boolean removido = dao.deletar(id);
		if (removido) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
		}
	}
}