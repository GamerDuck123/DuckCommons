package com.gamerduck.commons.storage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	private Connection connection;

	public Database(String name, String folder) throws Exception {
		File folderFile = new File(folder);
		folderFile.mkdirs();
		Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
		connection = DriverManager.getConnection("jdbc:sqlite:" + new File(folderFile, name + ".db"));
	}

	public Database(boolean reconnect, String host, String database, String username, String password, int port) throws Exception {
		Properties info = new Properties();
		info.setProperty("useSSL", "true");

		if (reconnect) {
			info.setProperty("autoReconnect", "true");
		}
		info.setProperty("trustServerCertificate", "true");
		info.setProperty("user", username);
		info.setProperty("password", password);

		Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, info);
	}

	public void createTable(String sqlURL) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(sqlURL)) {
			statement.executeUpdate();
		}
	}

	public Connection connection() {return connection;}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
