package br.edu.ifsp.arqdsw2.taskAPI.controller.handler;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.DeletarTarefaCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteDeletarTarefaHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("DELETE")
				&& request.getPathInfo() != null
				&& request.getPathInfo().matches("^/\\d+$");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DeletarTarefaCommand().executar(request, response);
	}
	

}
