/**
 * 
 */
package com.example.CarLook_Ltd.control;
import java.util.List;

import com.example.CarLook_Ltd.dao.HotelDAO;
import com.example.CarLook_Ltd.dto.Hotel;



import java.util.ArrayList;




/**
 * @author kpierz2s
 *
 */
public class HotelSearch {
	
	/* private HotelSearch () {
		
	}
	

	public static HotelSearch search = null;
	
	
	public static HotelSearch getInstance () {
		
		if (search == null) {
			search = new HotelSearch();
			
		}
		
		return search;
	}
	
	public List<Hotel> getHotelByOrt( String ort) {
		return HotelDAO.getInstance().getHotelByLocation(ort);
	}
}
*/
	
	Hotel hotel1 = new Hotel("Hotel Maier" , 1 , "Köln" , "Ein schönes Hotel");
	Hotel hotel2 = new Hotel("Hotel Maritim" , 2 , "Bonn" , "Ein wunderbares Hotel");
	Hotel hotel3 = new Hotel("Hotel Königshof" , 3 , "Bonn" , "Zentrales Hotel");
	
	private HotelSearch () {
		
	}
	

	public static HotelSearch search = null;
	
	
	public static HotelSearch getInstance () {
		
		if (search == null) {
			search = new HotelSearch();
			
		}
		
		return search;
	}
	
	public List<Hotel> getHotelByOrt( String ort) {
		ArrayList<Hotel> liste = new ArrayList<Hotel>();
		if (ort.equals("Köln")) liste.add(hotel1);
		if (ort.equals("Bonn")) liste.add(hotel2);
		if (ort.equals("Bonn")) liste.add(hotel3);
		
		return liste;
	}
}