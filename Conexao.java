package JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao {
	
	String serverName = "localhost:3306";
	String banco = "alunos";
	String url = "jdbc:mysql://" + serverName + "/" + banco + "?autoReconnect=true&useSSL=true";
	String usuario = "root";
	String senha = "univille";
	Connection conexao;

	Conexao() throws SQLException{
		conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
	}

}


