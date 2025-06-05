package br.edu.ifsp.arqdsw2.taskAPI.controller.command;

import java.io.BufferedReader;

import com.google.gson.Gson;

import br.edu.ifsp.arqdsw2.taskAPI.model.dao.TarefaDAO;
import br.edu.ifsp.arqdsw2.taskAPI.model.entity.Tarefa;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtualizarTarefaCommand implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getPathInfo();
		if (path == null || !path.matches("^/\\d+$")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da tarefa inválido.");
			return;
		}
		int id = Integer.parseInt(path.substring(1));
		BufferedReader reader = request.getReader();
		Tarefa tarefaAtualizada = new Gson().fromJson(reader, Tarefa.class);
		TarefaDAO dao = new TarefaDAO();
		boolean atualizado = dao.atualizar(id, tarefaAtualizada);
		if (atualizado) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tarefa não encontrada.");
		}
	}
}