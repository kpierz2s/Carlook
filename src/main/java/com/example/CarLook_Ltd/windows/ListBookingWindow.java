/**
 * 
 */
package com.example.CarLook_Ltd.windows;
import java.util.List;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;

import com.example.CarLook_Ltd.control.BookingProcess;
import com.example.CarLook_Ltd.dto.BookingDetail;
/**
 * @author kpierz2s
 *
 */
public class ListBookingWindow extends Window{
	
	private int currentID;
	private List<BookingDetail> liste;
	
	public ListBookingWindow() {
		
		super("Liste alle Buchungen"); //Set window caption
		center();
		VerticalLayout layout = new VerticalLayout();
		
		// ERzeuge Tabelle anhand DTO
		
		final BeanContainer<Integer, BookingDetail> data = new BeanContainer<Integer,BookingDetail> (BookingDetail.class);
		data.setBeanIdProperty("id");
		final Table table = new Table ("Liste ihrer Buchungen:" , data);
		table.setSizeFull();
		table.setSelectable(true);
		
		//liste
		liste = BookingProcess.getInstance().getAllBookingsForUser();
		
		//Befülle Tabelle
		
		data.addAll(liste);
		table.setPageLength(table.size());
		this.setSizeFull();
		layout.addComponent(table);
		
		Button deleteButton = new Button("Storniere Reise");
		deleteButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
			
				BookingProcess.getInstance().deleteBookingByID(currentID);
				
				data.removeAllItems();
				
				liste = BookingProcess.getInstance().getAllBookingsForUser();
				data.addAll(liste);
				table.setPageLength(table.size());
		
			}
		});
		
		Label emptyLabel = new Label ("&nbsp;", ContentMode.HTML);
		layout.addComponent(emptyLabel);
		layout.addComponent(deleteButton);
		layout.setComponentAlignment(deleteButton, Alignment.MIDDLE_CENTER);
		
		table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent itemClickEvent) {
			
				System.out.println(itemClickEvent.getItemId());
				ListBookingWindow.this.currentID = (Integer) itemClickEvent.getItemId();
				
			}
		});
		
		this.setContent(layout);
		
	}

}
