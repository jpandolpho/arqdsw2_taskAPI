package br.edu.ifsp.arqdsw2.taskAPI.controller;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.taskAPI.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tarefas/*")
public class FrontController extends HttpServlet {
	private TarefaDispatcher dispatcher;

	@Override
	public void init() {
		dispatcher = new TarefaDispatcher();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Command comando = dispatcher.resolver(request);
			if (comando != null) {
				comando.executar(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Rota não encontrada.");
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}