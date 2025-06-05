package br.edu.ifsp.arqdsw2.taskAPI.controller.command;

import java.io.PrintWriter;

import com.google.gson.Gson;

import br.edu.ifsp.arqdsw2.taskAPI.model.dao.TarefaDAO;
import br.edu.ifsp.arqdsw2.taskAPI.model.entity.Tarefa;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecuperarTarefaCommand implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getPathInfo();
		if (path == null || !path.matches("^/\\d+$")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da tarefa inválido.");
			return;
		}
		int id = Integer.parseInt(path.substring(1));
		TarefaDAO dao = new TarefaDAO();
		Tarefa tarefa = dao.buscarTarefa(id);
		if(tarefa!=null) {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(tarefa));
			out.flush();
		}else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tarefa não encontrada.");
		}
	}

}
