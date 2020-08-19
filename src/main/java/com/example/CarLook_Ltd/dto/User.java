/**
 * 
 */
package com.example.CarLook_Ltd.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.example.CarLook_Ltd.dao.RoleDAO;

/**
 * @author kpierz2s
 *
 */
public class User implements Serializable{
	
	private String vorname = null;
	private String login = null;
	private List<Role> roles = null;
	private String email = null;
	private LocalDate geburtstag;
	private String passwort = null;
	private String nachname;
	private String vollstName;
	

	public User() {
      
    }
	
	
	public User(String username, String email, String passwort) {
        this.login = username;
        this.email = email;
        this.passwort = passwort;
    }
	
	public LocalDate getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(LocalDate geburtstag) {
		this.geburtstag = geburtstag;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVollstName() {
		return vollstName;
	}

	public void setVollstName(String vollstName) {
		this.vollstName = vollstName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return passwort;
	}
	public void setPassword(String password) {
		this.passwort = password;
	}

	
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean hasRole(String role) {
		
		//Lazy Load
		if ( this.roles == null) {
			getRoles();
		}
		for ( Role r : roles) {
			
			if ( r.getBezeichnung().equals(role)) return true;
		}
		
		
		return false;
	}
	
	private void getRoles() {
		
		this.roles = RoleDAO.getInstance().getRolesForUser(this);
		
	}
	
	
	

}
