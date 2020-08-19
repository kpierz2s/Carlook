/**
 * 
 */
package com.example.CarLook_Ltd.db;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.CarLook_Ltd.Exceptions.DatabaseException;

import java.sql.*;
/**
 * @author kpierz2s
 *
 */
public class JDBCConnection {
	
	private static JDBCConnection connection = null;
	private Connection conn;
	private String login = "jvetmi2s";
	private String password = "jvetmi2s";
	private String url = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/jvetmi2s";
	
	public static JDBCConnection getInstance() throws DatabaseException {
		
		if ( connection == null) {
			connection = new JDBCConnection();
		}
		
		return connection;
		
	}
	
	private JDBCConnection() throws DatabaseException {
		this.initConnection();
	}
	
	/*public static void main (String [] args ) {
		JDBCConnection conncection = new JDBCConnection();
		try {
			conncection.test();
			
		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,ex);
		}
	} 
	 */
	
	private void initConnection() throws DatabaseException {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.openConnection();
		
	}
	
	
	public void openConnection() throws DatabaseException {
		
		try {
	
		Properties props = new Properties();
		props.setProperty("user", "jvetmi2s");
		props.setProperty("password" ,"jvetmi2s" );
		
		this.conn = DriverManager.getConnection(this.url,props);
		
		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,ex);
			throw new DatabaseException("Fehler bei Zugriff auf die DB! Sichere Verbindung vorhanden?!");
		}
		
	}
	
	
	
	public Statement getStatement() throws DatabaseException {
		
		try {
			if ( this.conn.isClosed()) {
				this.openConnection();
			}
			
			return this.conn.createStatement();
			
		}  catch (SQLException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,ex);
			
			return null;
		}
		
	}
	
	
	public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
		try {
			if ( this.conn.isClosed()) {
				this.openConnection();
			}
			
			return this.conn.prepareStatement(sql);
			
		}  catch (SQLException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,ex);
			
			return null;
		}
		
	}
	
	
	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
		
	}


