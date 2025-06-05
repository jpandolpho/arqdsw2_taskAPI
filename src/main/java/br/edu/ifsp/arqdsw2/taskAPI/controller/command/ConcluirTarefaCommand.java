package br.edu.ifsp.arqdsw2.taskAPI.controller.command;

import br.edu.ifsp.arqdsw2.taskAPI.model.dao.TarefaDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConcluirTarefaCommand implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getPathInfo();
		int id = Integer.parseInt(path.replaceAll("[^\\d]", ""));
		TarefaDAO dao = new TarefaDAO();
		dao.marcarComoConcluida(id);
		response.setStatus(HttpServletResponse.SC_OK);
	}
}