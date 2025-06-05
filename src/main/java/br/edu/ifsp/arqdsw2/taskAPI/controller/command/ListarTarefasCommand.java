package br.edu.ifsp.arqdsw2.taskAPI.controller.command;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import br.edu.ifsp.arqdsw2.taskAPI.model.dao.TarefaDAO;
import br.edu.ifsp.arqdsw2.taskAPI.model.entity.Tarefa;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListarTarefasCommand implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TarefaDAO dao = new TarefaDAO();
		List<Tarefa> tarefas = dao.listar();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(tarefas));
		out.flush();
	}
}