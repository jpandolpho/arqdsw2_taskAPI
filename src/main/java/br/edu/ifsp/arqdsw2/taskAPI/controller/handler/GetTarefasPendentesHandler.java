package br.edu.ifsp.arqdsw2.taskAPI.controller.handler;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.ListarTarefasPendentesCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetTarefasPendentesHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("GET")
				&& (request.getPathInfo() == null || request.getPathInfo().equals("/pendentes"));
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new ListarTarefasPendentesCommand().executar(request, response);
	}

}
