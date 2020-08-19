/**
 * 
 */
package com.example.CarLook_Ltd.views;


import java.util.ArrayList;
import java.util.List;

import com.example.CarLook_Ltd.UI.MyUI;
import com.example.CarLook_Ltd.components.NavigationBar;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.util.PasswortValidator;
import com.example.CarLook_Ltd.util.Views;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author kpierz2s
 *
 */
public class Registration extends VerticalLayout implements View {
	
	
	
    private final Panel userCreationPanel = new Panel("Schritt 1: Geben Sie Ihre Daten ein");
    private final Panel studentCreationPanel = new Panel("Schritt 2: Geben Sie Ihre persönlichen Daten an");
  
    private final Binder<User> binder = new Binder<>();
    private final Binder<User> userBinder = new Binder<>();
	
	public void enter(ViewChangeEvent event) {
		
		//User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
		User user = ((MyUI) UI.getCurrent()).getUser();
		if ( user != null) {  // <---------------------------------------------- == setzen
			UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
		} else {
			this.setUp();
		}
	
		}
	


	public void setUp() {
		
		NavigationBar navigationBar = new NavigationBar();
		
        this.addComponent(navigationBar);
        this.setComponentAlignment(navigationBar, Alignment.TOP_LEFT);
        
		userCreationPanel.setVisible(true);
        this.setSizeUndefined();
        this.addComponent(userCreationPanel);
        this.setComponentAlignment(userCreationPanel, Alignment.TOP_CENTER);
        
        Button weiterButton1 = new Button("Fortfahren", VaadinIcons.ARROW_RIGHT);
        weiterButton1.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        FormLayout content = new FormLayout();
        content.setSizeUndefined();
        
        TextField usernameField = new TextField("Nutzername");
        binder.forField(usernameField).asRequired(new StringLengthValidator("Ihr Nutzername mindestens 5 Buchstaben haben", 5, 1000))
                .bind(User::getLogin, User::setLogin);
        
        TextField emailField = new TextField("E-Mail Adresse");
        binder.forField(emailField).asRequired(new EmailValidator("Bitte geben Sie eine gültige E-Mail Adresse an"))
                .bind(User::getEmail, User::setEmail);

        PasswordField passwordField = new PasswordField("Passwort");
        binder.forField(passwordField).asRequired(new PasswortValidator())
                .bind(User::getPassword, User::setPassword);
        
        PasswordField passwordCheckField = new PasswordField("Passwort wiederholen");
        
        content.addComponents(usernameField, emailField, passwordField, passwordCheckField, weiterButton1);
        content.setSizeUndefined();

        content.setMargin(true);
        userCreationPanel.setContent(content);

        this.setComponentAlignment(userCreationPanel, Alignment.TOP_CENTER);
        
        weiterButton1.addClickListener(clickEvent -> {
            if (!passwordField.getValue().equals(passwordCheckField.getValue())) {
                Notification notification = new Notification("Die Passwörter stimmen nicht überein.", Notification.Type.ERROR_MESSAGE);
                notification.setPosition(Position.BOTTOM_CENTER);
                notification.setDelayMsec(4000);
                notification.show(Page.getCurrent());
                return;
            }

            User myUser = new User();
            try {
                binder.writeBean(myUser);
            } catch (ValidationException exception) {
                Notification notification = new Notification("Ein oder mehrere Felder sind ungültig", Notification.Type.ERROR_MESSAGE);
                notification.setPosition(Position.BOTTOM_CENTER);
                notification.setDelayMsec(4000);
                notification.show(Page.getCurrent());
                return;
            }

           

            userCreationPanel.setVisible(false);
            
            setUpStep2(myUser);

        });
        
		}



	/**
	 * @param myUser
	 */
	private void setUpStep2(User myUser) {
		
		NavigationBar navigationBar = new NavigationBar();
		
        this.addComponent(navigationBar);
        this.setComponentAlignment(navigationBar, Alignment.TOP_LEFT);
		   studentCreationPanel.setVisible(true);
	        this.addComponent(studentCreationPanel);
	        this.setComponentAlignment(studentCreationPanel, Alignment.MIDDLE_CENTER);

	        VerticalLayout layout = new VerticalLayout();
	        studentCreationPanel.setContent(layout);

	        HorizontalLayout nameLayout = new HorizontalLayout();
	        nameLayout.setSizeFull();

	        TextField vorname = new TextField("Vorname:");
	        vorname.setRequiredIndicatorVisible(true);
	        userBinder.forField(vorname).asRequired("Bitte geben Sie Ihren Vornamen an")
	                .bind(User::getVorname, User::setVorname);

	        TextField nachname = new TextField("Nachname");
	        nachname.setRequiredIndicatorVisible(true);
	        userBinder.forField(nachname).asRequired("Bitte geben Sie Ihren Nachnamen an")
	                .bind(User::getNachname, User::setNachname);

	        nameLayout.addComponents(vorname, nachname);

	        DateField geburtstag = new DateField("Geburtstag");
	        geburtstag.setDateFormat("dd.MM.yyyy");
	        geburtstag.setPlaceholder("dd.mm.yyyy");
	        geburtstag.setParseErrorMessage("Bitte Datum im richtigen Format angeben");
	        userBinder.forField(geburtstag).asRequired("Bitte geben Sie Ihr Geburtsdatum an")
	                .bind(User::getGeburtstag, User::setGeburtstag);

	        layout.addComponents(nameLayout, geburtstag);

	        

	        List<Button> list = addButtons(layout);
	        Button backButton2 = list.get(0);
	        Button completeButton = list.get(1);

	        backButton2.addClickListener(ClickEvent -> {
	            studentCreationPanel.setVisible(false);
	            setUp();
	        });


	        completeButton.addClickListener(clickEvent -> {
	            boolean isValidEntry = true;
	            //User user = new User();
	           
	            if (!isValidEntry) {
	                Notification notification = new Notification("Ein oder mehrere Felder sind ungültig", Notification.Type.ERROR_MESSAGE);
	                notification.setPosition(Position.BOTTOM_CENTER);
	                notification.setDelayMsec(4000);
	                notification.show(Page.getCurrent());
	            }
	            else {
	            	setUpStep3();
	            }
	        });

	    }
	
	private void setUpStep3() {
        Notification notification = new Notification("Sie haben sich erfolgreich registriert", Notification.Type.ASSISTIVE_NOTIFICATION);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
	}
		
		
	
private List<Button> addButtons(Layout layout) {
    HorizontalLayout buttonContainer = new HorizontalLayout();
    layout.addComponent(buttonContainer);
    buttonContainer.setSizeFull();

    Button buttonTosetup = new Button("Zurück");
    buttonContainer.addComponent(buttonTosetup);
    buttonContainer.setComponentAlignment(buttonTosetup, Alignment.BOTTOM_LEFT);

    Button buttonToStep2 = new Button("Weiter");
    buttonContainer.addComponent(buttonToStep2);
    buttonContainer.setComponentAlignment(buttonToStep2, Alignment.BOTTOM_RIGHT);

    List l = new ArrayList();
    l.add(buttonTosetup);
    l.add(buttonToStep2);
    return l;
		}
	}


