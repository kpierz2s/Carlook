/**
 * 
 */
package com.example.CarLook_Ltd.factories;

import java.util.Date;

import com.example.CarLook_Ltd.dto.BookingRequest;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.entities.Booking;

/**
 * @author kpierz2s
 *
 */
public class BookingFactory {

	
	public static Booking createBooking(BookingRequest request, User user) {
		Booking book = new Booking();
		
		book.setAbreise(request.getAbreise());
		book.setAnreise(request.getAnreise());
		book.setHotel(request.getHotel());
		book.setIban(request.getIban());
		book.setNumber(request.getNumber());
		
		// Zser gehört zu einer Buchung ER Modell
		
		book.setUser(user);
		
		// Zusätzliches Attribut
		book.setDatumBuchung(new Date());
		
		// book.setID später für DB
		
		return book;
	}

}
