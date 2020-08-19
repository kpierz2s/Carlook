/**
 * 
 */
package com.example.CarLook_Ltd.control;
import java.sql.Statement;
import java.util.List;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import com.example.CarLook_Ltd.UI.MyUI;
import com.example.CarLook_Ltd.dao.BuchungDAO;
import com.example.CarLook_Ltd.dto.BookingDetail;
import com.example.CarLook_Ltd.dto.BookingRequest;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.entities.Booking;
import com.example.CarLook_Ltd.factories.BookingFactory;
import com.example.CarLook_Ltd.util.Roles;
import com.example.CarLook_Ltd.windows.ConfirmationWindow;


/**
 * @author kpierz2s
 *
 */
public class BookingProcess {
	
	public static BookingProcess process = null;
	
	private BookingProcess() {
		
	}
	
	public static BookingProcess getInstance() {
		if( process == null) {
			process = new BookingProcess();
		}
		return process;
		
	}
	
	public void createBooking(BookingRequest request, Window window) {
		//User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
		User user = ((MyUI) UI.getCurrent()).getUser();
		Booking booking = BookingFactory.createBooking(request, user);
		
		boolean result = BuchungDAO.getInstance().addBooking(booking);
		//Navigation auf Basis der un/erfolgreichen Buchung
		if ( result == true) {
			window.close();
			UI.getCurrent().addWindow(new ConfirmationWindow("Buchung erfolgreich!  ID: " + booking.getId()));
			
		} else {
			
			// Fehlerhandling
			
		}
		
	}
	
	public List<BookingDetail> getAllBookingsForUser() {
		final User user = ((MyUI) UI.getCurrent()).getUser();
		return BuchungDAO.getInstance().getAllBookingsForUser(user);
	}
	
	
	
	public void deleteBookingByID ( int id) {
		BuchungDAO.getInstance().deleteBookingBy (id);
		UI.getCurrent().addWindow ( new ConfirmationWindow( " Dies Reise wurde storniert"));
	}

}
