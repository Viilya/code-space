package com.ssafy.ws.model;

public class Magazine extends Book{
    private int year;
    private int month;
    
    public Magazine() {};

    public Magazine(String isbn, String title, String author, String publisher, int price, String desc, int qunatity, int year, int month) {
        super(isbn, title, author, publisher, price, desc, qunatity);
        this.year = year;
        this.month = month;
    }

    public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
    @Override
    public String toString() {
        return new StringBuilder(super.toString()).append("\t|").append(year).append("\t|").append(month).toString();
    }
}
