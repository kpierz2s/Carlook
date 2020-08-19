/**
 * 
 */
package com.example.CarLook_Ltd.dao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.CarLook_Ltd.Exceptions.DatabaseException;
import com.example.CarLook_Ltd.control.LoginControl;
import com.example.CarLook_Ltd.db.JDBCConnection;
import com.example.CarLook_Ltd.dto.Role;
import com.example.CarLook_Ltd.dto.User;

/**
 * @author kpierz2s
 *
 */
public class RoleDAO extends AbstractDAO{
	
	
	public static RoleDAO dao = null;
	
	private RoleDAO() {
		
	}
	public static RoleDAO getInstance() {
		if ( dao == null) {
			dao = new RoleDAO();
		}
		return dao;
	}
	
	public List<Role> getRolesForUser(User user ) {
		Statement statement = this.getStatement();
		ResultSet rs = null;
		try {
			rs = statement.executeQuery("SELECT *"
										+ "FROM realm.user_to_rolle "
										+ "WHERE realm.user_to_rolle.login = \'" 
										+ user.getLogin() + "\'  ");
		} catch (SQLException ex) {
			Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		if ( rs == null) return null;
		
		List<Role> liste = new ArrayList<Role>();
		Role role = null;
		try {
			while (rs.next()) {
				role = new Role();
				role.setBezeichnung(rs.getString(2));
				liste.add(role);
			}	
		} catch  (SQLException ex) {
			Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE,null,ex);
			
		}
		return liste;
	}

}
