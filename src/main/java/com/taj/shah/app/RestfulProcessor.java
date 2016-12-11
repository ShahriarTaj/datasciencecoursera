package com.taj.shah.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulProcessor {

	@RequestMapping("/createTables")
	public String createTables() {
		StringBuilder sb = new StringBuilder();

		Connection conn;
		try {
			System.out.println("trying to connect");
			conn = getConnection();
			conn.close();
			sb.append("YEAH");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append(e.getMessage());
		}

		return sb.toString();
	}

	private Connection getConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection connection;

		
		String host = "mariadb-app-with-persistence.44fs.preview.openshiftapps.com";
		String username = "shah";
		String password = "test";
		String driver = "org.mariadb.jdbc.Driver";
		String url = String.format("jdbc:mariadb://%s:3306/DB?user=%s&password=%s", host, username, password);
		System.out.println(url);
		Class.forName(driver);
		connection = DriverManager.getConnection(url);

		System.out.println(
				"host: " + host + "\\username: " + username + "\\password: " + password + "\\driver: " + driver);

		System.out.println("--------------------------");
		System.out.println("DRIVER: " + driver);
		connection = DriverManager.getConnection(host, username, password);
		System.out.println("CONNECTION: " + connection);
		return connection;
	}

}
