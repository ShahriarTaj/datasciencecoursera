package com.taj.shah.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestfulProcessor {

	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/createTables")
	public String createTables() {
		StringBuilder sb = new StringBuilder();

		Connection conn;
		try {
			logger.info("trying to connect");
			conn = getConnection();
			conn.close();
			sb.append("YEAH");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append(e.getMessage());
		}

		sb.append("The time is: " + new Date(System.currentTimeMillis()).toString());
		
		return sb.toString();
	}

	private Connection getConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection connection;
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);

		
		String host = System.getenv("MYSQL_SERVICE_HOST");
		String username = System.getenv("MYSQL_USER");
		String password = System.getenv("MYSQL_PASSWORD");
		String db = System.getenv("MYSQL_DATABASE");
		String url = String.format("jdbc:mysql://%s:3306/%s?user=%s&password=%s", host, db, username, password);
		//String url = "jdbc:mysql://172.30.35.199:3306/sampledb?user=shah&password=test";
		logger.info(url);
		connection = DriverManager.getConnection(url);

		return connection;
	}

}
