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
		String pagina = request.getParameter("page");
		String limite = request.getParameter("limit");
		int page = 0;
		int limit = 0;
		if(pagina!=null && pagina.matches("^\\d+$")) {
			page = Integer.parseInt(pagina);
		}
		if(limite!=null && limite.matches("^\\d+$")) {
			limit = Integer.parseInt(limite);
		}
		List<Tarefa> tarefas = dao.listar(limit,page);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(tarefas));
		out.flush();
	}
}