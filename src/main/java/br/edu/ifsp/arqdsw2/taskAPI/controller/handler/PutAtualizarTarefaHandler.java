package br.edu.ifsp.arqdsw2.taskAPI.controller.handler;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.AtualizarTarefaCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PutAtualizarTarefaHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("PUT")
				&& request.getPathInfo() != null
				&& request.getPathInfo().matches("^/\\d+$");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new AtualizarTarefaCommand().executar(request, response);
	}
	
}
