/**
 * 
 */
package com.example.CarLook_Ltd.components;

import com.example.CarLook_Ltd.control.LoginControl;
import com.example.CarLook_Ltd.util.Views;
import com.vaadin.event.MouseEvents;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

/**
 * @author kpierz2s
 *
 */
public class NavigationBar extends HorizontalLayout {

	public NavigationBar() {

        Image logo = createImage();


        this.addComponent(logo);
        this.setComponentAlignment(logo, Alignment.TOP_LEFT);

      
	}
	
	public void NavigationBarWithLogout() {


        MenuBar menuBar = new MenuBar();
        
        MenuBar.MenuItem logout = menuBar.addItem("Logout", clickEvent -> LoginControl.logoutUser());
        logout.setIcon(VaadinIcons.SIGN_OUT);
       
       

        this.addComponent(menuBar);
        this.setComponentAlignment(menuBar, Alignment.TOP_RIGHT);
	}

	
	
	private Image createImage() {
        ThemeResource themeResource = new ThemeResource("image/carlook.png");
        Image logo = new Image(null, themeResource);
        logo.setWidth("600px");
        logo.addStyleName("logo");
        logo.addClickListener((MouseEvents.ClickListener) event -> {
        	UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        });
        return logo;
    }
	
}