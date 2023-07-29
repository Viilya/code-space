package com.ssafy.ws.model;

/**
 * 도서테이터 클래스(Value Object - DTO:Data Transfer Object - 직렬화 가능하도록)
 * 도서 정보를 나타내는 클래스
 */
public class Book implements java.io.Serializable{
    // 코드를 작성하세요.
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int price;
    private String desc;
    private int quantity;
    public Book() {
    }

    public Book(String isbn, String title, String author, String publisher, int price, String desc, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.desc = desc;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //@Override
    // 부모 메서드 재 정의 : 메서드 이름, 리턴타입, argumentlist 동일하게
    // 접근 제한자 확장만 가능, 예외처리 축소만 가능
    // 객체를 대표하는 문자열
    public String toString() {

        
        return new StringBuilder(isbn)
                .append("\t| ").append(title)
                .append("\t| ").append(author)
                .append("\t| ").append(publisher)
                .append("\t| ").append(price)
                .append("\t| ").append(desc)
                .append("\t| ").append(quantity).toString();
    }
}