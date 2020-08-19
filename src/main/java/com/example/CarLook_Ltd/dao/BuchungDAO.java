/**
 * 
 */
package com.example.CarLook_Ltd.dao;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

import com.vaadin.ui.UI;

import com.example.CarLook_Ltd.dto.BookingDetail;

import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.entities.Booking;

/**
 * @author kpierz2s
 *
 */
public class BuchungDAO extends AbstractDAO{
	public static BuchungDAO dao = null;
	
	private BuchungDAO () {
		
	}
	
	public static BuchungDAO getInstance () {
		if (dao == null ) {
			dao = new BuchungDAO();
		}
		return dao;
	}
	
	public boolean addBooking ( Booking booking) {
		String sql = "insert into realm.booking values (?,?,?,?,?,?,?,default);";
		PreparedStatement statement = this.getPreparedStatement(sql);
		
		// Zeilenweise Abbildung der Datenbanksätze
		
		try {
			
			statement.setDate(1, new java.sql.Date( booking.getAnreise().getTime()));
			statement.setDate(2, new java.sql.Date( booking.getAbreise().getTime()));
			statement.setString(3, booking.getIban());
			statement.setInt(4, booking.getNumber());
			statement.setString(5, booking.getUser().getLogin());
			statement.setDate(6, new java.sql.Date(booking.getDatumBuchung().getTime()));
			statement.setInt(7, booking.getHotel().getId());
			
			statement.executeUpdate();
			
			//nachträgliches setzten Buchung ID
			
			setBuchungsID ( booking);
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE,null,ex);
			return false;
		}
		
		
	}
	
	
	private void setBuchungsID(Booking booking) {
		
		Statement statement = this.getStatement();
		ResultSet rs = null;
		
		try {
			rs = statement.executeQuery("SELECT max(realm.booking.id" 
										+ "FROM realm.booking "
										);
		} catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		int currentValue = 0;
		
		try {
			rs.next();
			currentValue = rs.getInt(1);	
		} catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		booking.setId(currentValue);
		
	}
	
	public void deleteBookingBy ( int id) {
		Statement statement = this.getStatement();
		try {
			statement.execute("DELETE FROM realm.booking WHERE realm.booking.id = \'" + id + "\';" );
		} catch (SQLException ex) {
			//todo
		}
	}

	/**
	 * @param user
	 * @return
	 */
	
	
	 public List <BookingDetail> getAllBookingsForUser ( User user ) {
		
		Statement statement = this.getStatement();
		
		ResultSet rs = null;
		
		
		try {
			
		rs = statement.executeQuery("SELECT realm.hotel.name, realm.booking.anreise, realm.booking.abreise, realm.booking.datumbuchung "
									+ "FROM realm.booking JOIN realm.hotel "
									+ "ON ( realm.booking.hotelid = realm.hotel.id )" 
									+ "WHERE realm.booking.userid = \'" + user.getLogin() + "\'  ");
		
		} catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE,null,ex);
		}
		if ( rs == null) return null;
		
		List <BookingDetail> liste = new ArrayList<BookingDetail>();
		BookingDetail booking = null;
		
		try {
			while (rs.next()) {
			booking = new BookingDetail();
			booking.setHotel(rs.getString(1));
			booking.setId(rs.getInt(2));
			booking.setAnreise(rs.getDate(3));
			booking.setAbreise(rs.getDate(4));
			booking.setDatumBuchung(rs.getDate(5));
			
			liste.add(booking); 
			}
		} catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE,null,ex);
		
	 }
		
		return liste;
}

}
