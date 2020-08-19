/**
 * 
 */
package com.example.CarLook_Ltd.views;

import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.grid.HeightMode;

import java.util.List;

import com.example.CarLook_Ltd.UI.MyUI;
import com.example.CarLook_Ltd.components.NavigationBar;
import com.example.CarLook_Ltd.control.HotelSearch;
import com.example.CarLook_Ltd.dto.Hotel;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.util.Views;

/**
 * @author kpierz2s
 *
 */

	public class MainView extends VerticalLayout implements View {
		
	 private int anzahl = 0;
	 private Hotel selected = null;

		@Override
		public void enter(ViewChangeEvent event) {
			
			//User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
			User user = ((MyUI) UI.getCurrent()).getUser();
			if ( user != null) {  // <---------------------------------------------- == setzen
				UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
			} else {
				this.setUp();
			}
		
			}
		
	
	
		public void setUp() {
			
			NavigationBar navigationBar = new NavigationBar();
			navigationBar.NavigationBarWithLogout();
	        this.addComponent(navigationBar);
	        this.setComponentAlignment(navigationBar, Alignment.TOP_LEFT);
	      
			
	        final VerticalLayout layout = new VerticalLayout();        
	        final HorizontalLayout horizon = new HorizontalLayout();
	 
	        final Label label = new Label("Gebe eine AutoMarke ein:");
	        Button button = new Button("Suche", FontAwesome.SEARCH);
	        Button buchen = new Button("Buchen", FontAwesome.BOOK);
	       
	        
	        final TextField textinput = new TextField();
	       
	        horizon.addComponents(label, textinput, button);
	        layout.addComponent(horizon);
	 
	        layout.setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
	        horizon.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
	 
	        
	        Panel panel = new Panel("                                                        Suchfenster");
			panel.addStyleName("search");
			
			this.addComponent(panel);
			this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
			
	        
	        Grid<Hotel> grid = new Grid<>();
	        grid.setSizeFull();
	        grid.setHeightMode(HeightMode.UNDEFINED);
	 
	        // Die aktuell angewählte Zeile in der Tabelle (aka dem Grid)
	        SingleSelect<Hotel> selection = grid.asSingleSelect();
	 
	        // Der Event Listener für den Grid
	        grid.addSelectionListener(event -> {
	   
	            // Speichert den aktuell angewählten Wert bei klick auf die Zeile in der Var. "selected"
	          this.selected = selection.getValue();
	       
	    });
	 
	        // Event Listener für den Suchen Button
	        button.addClickListener(e -> {
	           
	            String ort = textinput.getValue();
	            List<Hotel> liste = HotelSearch.getInstance().getHotelByOrt(ort);
	 
	            if (ort.equals("")) {
	                Notification.show(null, "Bitte Automarke eingeben!", Notification.Type.WARNING_MESSAGE);
	            }
	            anzahl += 1;
	 
	            //erstmal alles löschen
	            grid.removeAllColumns();
	            grid.setCaption("Treffer für " + ort + " (Anzahl der Suchen: " + anzahl + ")");
	           
	            // neue Items hinzufügen
	            grid.setItems(liste);
	 
	            // Columns definieren
	            grid.addColumn(Hotel::getName).setCaption("Marke");
	            grid.addColumn(Hotel::getId).setCaption("ID");
	            grid.addColumn(Hotel::getOrt).setCaption("Standort");
	            grid.addColumn(Hotel::getDescription).setCaption("Modell");
	        });
	 
	 
	        buchen.addClickListener(e -> {
	            // Testweise Ausgabe der derzeitigen Selektion in einer Notification
	            Notification.show(null, "Auswahl: " + this.selected.toString(), Notification.Type.WARNING_MESSAGE);
	        });
	 
	        // Grid und Buchen Button richtig anordnen
	        layout.addComponent(grid);
	        
	        layout.addComponent(buchen);
	        layout.setComponentAlignment(buchen, Alignment.MIDDLE_CENTER);
	        

			panel.setContent(layout);
			
		}

	}
		    
	      


