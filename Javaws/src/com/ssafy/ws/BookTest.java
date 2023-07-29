package com.ssafy.ws;

import com.ssafy.ws.exception.ISBNNotFoundException;
import com.ssafy.ws.exception.QuantityException;
import com.ssafy.ws.model.Book;
import com.ssafy.ws.model.Magazine;
import com.ssafy.ws.model.service.BookManagerImpl;
import com.ssafy.ws.model.service.IBookManager;

public class BookTest {
    public static void main(String[] args) {
        // 코드를 작성하세요.
        IBookManager service = BookManagerImpl.getInstance();
        service.add(new Book("21424", "JavaPro", "김하나", "jaen.kr", 15000, "Java 기본 문법", 10));
        service.add(new Book("21425", "JavaPro2", "김하나", "jaen.kr", 25000, "Java 기본 응용", 20));
        service.add(new Book("35355", "분석설계", "소나무", "jaen.kr", 30000, "SW 모델",30));
        service.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr", 10000, "1월 알고리즘", 40, 2021, 1));
        Book[] books = service.getList();
        for (Book b : books)
            System.out.println(b);
        Book[] generalBooks = service.getBooks();
        for(Book gb : generalBooks)
            System.out.println(gb);
        Magazine[] magazines = service.getMagazines();
        for (Magazine m : magazines)
            System.out.println(m);
        
        System.out.printf("**********************도서 조회:%s**********************\n", "Java");
        Book[] searchedBoooks = service.searchByTitle("Java");
        for (Book sb : searchedBoooks)
            System.out.println(sb);
        int totalPrice = service.getTotalPrice();
        System.out.printf("도서 가격 총합 : %d\n", totalPrice);
        double avgPrice = service.getPriceAvg();
        System.out.printf("도서 가격 평균 : %.1f\n", avgPrice);

        try {
            service.sell("21414", 11);
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(QuantityException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            service.buy("21424", 10);

            System.out.println(service.searchByIsbn("21424"));
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            service.sell("21424", 11);
            System.out.println(service.searchByIsbn("21424")); 
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(QuantityException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        service.saveData();
// Book book = service.searchByIsbn("35355");
        // System.out.println(book);
        // Book book = service.searchByIsbn("21424");
        // System.out.println(book);
        // service.searchByIsbn("21425");
        // System.out.println(book);
        // service.remove("35355");
        // service.getList();
    }
}