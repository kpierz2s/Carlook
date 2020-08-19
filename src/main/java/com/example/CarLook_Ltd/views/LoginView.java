/**
 * 
 */
package com.example.CarLook_Ltd.views;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import com.example.CarLook_Ltd.Exceptions.DatabaseException;
import com.example.CarLook_Ltd.Exceptions.NoSuchUserOrPassword;
import com.vaadin.ui.Button.ClickEvent;
import com.example.CarLook_Ltd.control.LoginControl;
import com.example.CarLook_Ltd.db.JDBCConnection;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.util.Roles;
import com.example.CarLook_Ltd.util.Views;
import com.example.CarLook_Ltd.UI.MyUI;
import com.example.CarLook_Ltd.components.NavigationBar;
/**
 * @author kpierz2s
 *
 */
public class LoginView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		
		// User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
		User user =((MyUI) UI.getCurrent()).getUser();
		if ( user != null) {
			UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
		} else {
			this.setUp();
		}
		}
	
	public void setUp() {
		
		NavigationBar navigationBar = new NavigationBar();
		
        this.addComponent(navigationBar);
        this.setComponentAlignment(navigationBar, Alignment.TOP_LEFT);
		
		this.setSizeFull();
		
		
		
		final TextField userLogin = new TextField ();
		userLogin.setCaption("UserID:");
		
		final PasswordField passwordField = new PasswordField();
		passwordField.setCaption("Passwort");
		
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(userLogin);
		layout.addComponent(passwordField);
		
		Label label = new Label("&nbsp", ContentMode.HTML);
		layout.addComponent(label);
		
		Panel panel = new Panel("Bitte Login daten eingeben:");
		panel.addStyleName("login");
		
		this.addComponent(panel);
		this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		
		panel.setContent(layout);
		
		Button buttonLogin = new Button("Login" , FontAwesome.SEARCH);
		Button buttonRegistierung = new Button("Registieren" , FontAwesome.SEARCH);
		
		layout.addComponent(buttonLogin);
		layout.setComponentAlignment(buttonLogin, Alignment.MIDDLE_CENTER);
		
		layout.addComponent(buttonRegistierung);
		layout.setComponentAlignment(buttonRegistierung, Alignment.MIDDLE_CENTER);
		
		panel.setSizeUndefined();
		
		buttonLogin.addClickListener( new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String login = userLogin.getValue();
				String password = passwordField.getValue();
				
				try {
					LoginControl.checkAuthentfication(login, password);
				} catch (NoSuchUserOrPassword ex ) {
					Notification.show("Benutzer Fehler"," Login oder Passwort falsch", Notification.TYPE_ERROR_MESSAGE);
					userLogin.setValue("");
					passwordField.setValue("");
				}
				catch (DatabaseException ex) {
					Notification.show("DB-Fehler",ex.getReason() , Notification.TYPE_ERROR_MESSAGE);
					userLogin.setValue("");
					passwordField.setValue("");
				}
			}
		});
		
		buttonRegistierung.addClickListener(e -> {
            UI.getCurrent().getNavigator().navigateTo(Views.REGIS);
        });
		
	}
}