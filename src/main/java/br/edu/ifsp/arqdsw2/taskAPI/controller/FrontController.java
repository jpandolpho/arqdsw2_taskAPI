package br.edu.ifsp.arqdsw2.taskAPI.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.AtualizarTarefaCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.Command;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.CriarTarefaCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.DeletarTarefaCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.ListarTarefasCommand;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tarefas/*")
public class FrontController extends HttpServlet {
	private final Map<String, Command> comandos = new HashMap<>();

	@Override
	public void init() {
		comandos.put("GET", new ListarTarefasCommand());
		comandos.put("POST", new CriarTarefaCommand());
		comandos.put("PUT", new AtualizarTarefaCommand());
		comandos.put("DELETE", new DeletarTarefaCommand());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String metodo = request.getMethod();
			Command comando = comandos.get(metodo);
			if (comando != null) {
				comando.executar(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}