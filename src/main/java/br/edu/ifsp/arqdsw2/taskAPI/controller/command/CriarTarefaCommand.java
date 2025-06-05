package br.edu.ifsp.arqdsw2.taskAPI.controller.command;

import java.io.BufferedReader;

import com.google.gson.Gson;

import br.edu.ifsp.arqdsw2.taskAPI.model.dao.TarefaDAO;
import br.edu.ifsp.arqdsw2.taskAPI.model.entity.Tarefa;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CriarTarefaCommand implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedReader reader = request.getReader();
		Tarefa tarefa = new Gson().fromJson(reader, Tarefa.class);
		TarefaDAO dao = new TarefaDAO();
		dao.inserir(tarefa);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
}