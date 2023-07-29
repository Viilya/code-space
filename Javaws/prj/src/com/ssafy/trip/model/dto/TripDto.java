package com.ssafy.trip.model.dto;

public class TripDto {
	private int num;
	private String touristDestination;
	private String streetAddress;
	private String lotAddress;
	private double alt;
	private double lng;
	private String info;
	private String tel;
	private String img;
	
	public TripDto() {};
	public TripDto(int num) {
		setNum(num);
	}
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTouristDestination() {
		return touristDestination;
	}
	public void setTouristDestination(String touristDestination) {
		this.touristDestination = touristDestination;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getLotAddress() {
		return lotAddress;
	}
	public void setLotAddress(String lotAddress) {
		this.lotAddress = lotAddress;
	}
	public double getAlt() {
		return alt;
	}
	public void setAlt(double alt) {
		this.alt = alt;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
