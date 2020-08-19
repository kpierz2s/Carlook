package com.example.CarLook_Ltd.dto;

/**
 * 
 */


/**
 * @author kpierz2s
 *
 */
@SuppressWarnings("serial")
public class Hotel implements java.io.Serializable{
	
	/**
	 * 
	 */
	
	private String name;
	private Integer id;
	private String ort;
	private String description;

	public Hotel(String name, int id, String ort, String description) {
		
		this.name = name;
		this.id = id;
		this.ort = ort;
		this.description = description;
	}
	

	public Hotel () {
		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId () {
		return id;
	}


}
