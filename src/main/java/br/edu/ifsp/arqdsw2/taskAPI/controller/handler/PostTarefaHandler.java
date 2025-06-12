package br.edu.ifsp.arqdsw2.taskAPI.controller.handler;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.CriarTarefaCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PostTarefaHandler extends AbstractHandler {
	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("POST")
				&& (request.getPathInfo() == null || request.getPathInfo().equals("/"));
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new CriarTarefaCommand().executar(request, response);
	}
}