/**
 * 
 */
package com.example.CarLook_Ltd.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.CarLook_Ltd.Exceptions.DatabaseException;
import com.example.CarLook_Ltd.db.JDBCConnection;

/**
 * @author kpierz2s
 *
 */
public class AbstractDAO {
	
	protected Statement getStatement() {
		
		Statement statement = null;
		
		try {
			statement = JDBCConnection.getInstance().getStatement();
		} catch (DatabaseException ex) {
			Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		return statement;
		
	}
	
	protected PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement statement = null;
		
		try {
			statement = JDBCConnection.getInstance().getPreparedStatement(sql);
		} catch (DatabaseException ex) {
			Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE,null,ex);
		}
		return statement;
		
	}
	
	

}
