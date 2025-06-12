package br.edu.ifsp.arqdsw2.taskAPI.controller.handler;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.ConcluirTarefaCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PatchConcluirTarefaHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("PATCH")
				&& request.getPathInfo() != null
				&& request.getPathInfo().matches("^/\\d+/concluir$");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new ConcluirTarefaCommand().executar(request, response);
	}

}
