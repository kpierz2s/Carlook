/**
 * 
 */
package com.example.CarLook_Ltd.windows;



import java.util.Date;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import com.example.CarLook_Ltd.control.BookingProcess;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import com.example.CarLook_Ltd.dto.BookingRequest;
import com.example.CarLook_Ltd.dto.Hotel;

/**
 * @author kpierz2s
 *
 */
public class BookingWindow extends Window {
	
	public BookingWindow ( final Hotel hotel) {
		super("Buchung"); //set window caption
		center();
		
		//some bsic content for the window
		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label ("Buchung für Hotel: " + hotel.getName()));
		content.setMargin(true);
		setContent(content);
		
		final DateField dateAnreise = new DateField();
		content.addComponent(dateAnreise);
		dateAnreise.setCaption("Anreise");
		dateAnreise.setDateFormat("yyyy-MM--dd");
		dateAnreise.setValue(new Date());
		
		final DateField dateAbreise = new DateField();
		content.addComponent(dateAbreise);
		dateAbreise.setCaption("Abreise");
		dateAbreise.setDateFormat("yyyy-MM--dd");
		dateAbreise.setValue(new Date());
		
		final ComboBox personNumber = new ComboBox();
		personNumber.setCaption("Anzahl Personen");
		content.addComponent(personNumber);
		personNumber.addItem(new Integer (1));
		personNumber.addItem(new Integer (2));
		personNumber.addItem(new Integer (3));
		
		final TextField ibanFeld = new TextField();
		ibanFeld.setCaption("IBAN:" );
		content.addComponent(ibanFeld);
		
		Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
		content.addComponent(emptyLabel);
		
		//Enable the close button
		
		setClosable(true);
		
		// Implementierung Button
		Button buchungsButton = new Button("Buche");
		buchungsButton.addClickListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
			
				BookingRequest request = new BookingRequest();
				request.setAbreise(dateAbreise.getValue());
				request.setAnreise(dateAnreise.getValue());
				request.setIban(ibanFeld.getValue());
				request.setNumber((Integer)personNumber.getValue());
				request.setHotel(hotel);
				
				BookingProcess.getInstance().createBooking(request, BookingWindow.this);
				
				
				
				
			}
		});
		
		content.addComponent(buchungsButton);
		content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);
		
	}

		
	
}
