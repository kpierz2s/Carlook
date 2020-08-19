/**
 * 
 */
package com.example.CarLook_Ltd.entities;

import java.util.Date;

import com.example.CarLook_Ltd.dto.Hotel;
import com.example.CarLook_Ltd.dto.User;

/**
 * @author kpierz2s
 *
 */
public class Booking {
	private int id ;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	private Date anreise = null;
	private Date abreise = null;
	private Date datumBuchung = null;
	
	
	public Date getAnreise() {
		return anreise;
	}
	public void setAnreise(Date anreise) {
		this.anreise = anreise;
	}
	public Date getAbreise() {
		return abreise;
	}
	public void setAbreise(Date abreise) {
		this.abreise = abreise;
	}
	public Date getDatumBuchung() {
		return datumBuchung;
	}
	public void setDatumBuchung(Date datumBuchung) {
		this.datumBuchung = datumBuchung;
	}


	private String iban = null;
	private int number;
	private Hotel hotel;
	private User user;


	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
