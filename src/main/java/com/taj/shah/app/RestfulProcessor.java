package com.taj.shah.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
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

		Connection conn = null;
		try {
			logger.info("trying to connect");
			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			sb.append(e.getMessage());
		}

		if ( conn!= null){
			
			createTables(conn);
			
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		sb.append("The time is: " + new Date(System.currentTimeMillis()).toGMTString());
		
		return sb.toString();
	}

	private void createTables(Connection conn) {
		String sql = "Create table test (time varch(200) )";
		
	}

	private Connection getConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection connection;
		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);

		               
		String host = System.getenv("MARIADB_SERVICE_HOST");
		String username = System.getenv("MYSQL_USER");
		String password = System.getenv("MYSQL_PASSWORD");
		String db = System.getenv("MYSQL_DATABASE");
		String url = String.format("jdbc:mariadb://%s:3306/%s?user=%s&password=%s", host, db, username, password);
		logger.info(url);
		connection = DriverManager.getConnection(url);

		return connection;
	}

}
