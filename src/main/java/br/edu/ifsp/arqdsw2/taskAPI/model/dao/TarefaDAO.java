package br.edu.ifsp.arqdsw2.taskAPI.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.edu.ifsp.arqdsw2.taskAPI.model.entity.Tarefa;

public class TarefaDAO {
	private DataSource dataSource;

	public TarefaDAO() throws NamingException {
		InitialContext ctx = new InitialContext();
		dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/taskAPI");
	}

	public List<Tarefa> listar() throws SQLException {
		List<Tarefa> tarefas = new ArrayList<>();
		String sql = "SELECT * FROM tarefas";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Tarefa t = new Tarefa(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"),
						rs.getBoolean("concluida"));
				tarefas.add(t);
			}
		}
		return tarefas;
	}

	public void inserir(Tarefa tarefa) throws SQLException {
		String sql = "INSERT INTO tarefas (titulo, descricao, concluida) VALUES (?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, tarefa.getTitulo());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setBoolean(3, tarefa.isConcluida());
			stmt.executeUpdate();
		}
	}

	public boolean atualizar(int id, Tarefa tarefa) throws SQLException {
		String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, concluida = ? WHERE id = ?";
		int rows = 0;
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, tarefa.getTitulo());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setBoolean(3, tarefa.isConcluida());
			stmt.setInt(4, id);
			rows = stmt.executeUpdate();
		}
		return rows > 0;
	}

	public boolean deletar(int id) throws SQLException {
		String sql = "DELETE FROM tarefas WHERE id = ?";
		int rows = 0;
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			rows = stmt.executeUpdate();
		}
		return rows > 0;
	}
}