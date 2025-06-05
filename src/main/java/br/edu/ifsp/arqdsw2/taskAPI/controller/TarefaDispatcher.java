package br.edu.ifsp.arqdsw2.taskAPI.controller;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.AtualizarTarefaCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.Command;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.CriarTarefaCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.DeletarTarefaCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.ListarTarefasCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.ListarTarefasPendentesCommand;
import br.edu.ifsp.arqdsw2.taskAPI.controller.command.RecuperarTarefaCommand;
import jakarta.servlet.http.HttpServletRequest;

public class TarefaDispatcher {
	private final Map<String, Command> rotas = new HashMap<>();

	public TarefaDispatcher() {
		rotas.put("GET:/", new ListarTarefasCommand());
		rotas.put("POST:/", new CriarTarefaCommand());
		rotas.put("GET:/id", new RecuperarTarefaCommand());
		rotas.put("PUT:/id", new AtualizarTarefaCommand());
		rotas.put("DELETE:/id", new DeletarTarefaCommand());
		rotas.put("GET:/pendentes", new ListarTarefasPendentesCommand());
	}

	public Command resolver(HttpServletRequest request) {
		String method = request.getMethod();
		String path = request.getPathInfo();
		if (path == null || path.equals("/")) {
			return rotas.get(method + ":/");
		} else if (path.matches("^/\\d+$")) {
			return rotas.get(method + ":/id");
		} else if (path.equals("/pendentes")) {
			return rotas.get(method + ":/pendentes");
		}
		return null;
	}
}