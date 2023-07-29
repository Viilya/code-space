package com.ssafy.ws.model.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssafy.ws.exception.ISBNNotFoundException;
import com.ssafy.ws.exception.QuantityException;
import com.ssafy.ws.model.Book;
import com.ssafy.ws.model.Magazine;

/**
 * 
 */
public class BookManagerImpl implements IBookManager{
    // 2. 생성자를 외부에서 접근을 못하기 때문에 private static instance로 객체 생성
    private static IBookManager instance = new BookManagerImpl();
    private ArrayList<Book> books; // declare array and initialize and allocate needed ALT+SHIFT+S 

    /**
     * book.dat 있으면 readObject, books 에 할당
     *          없으면 ArrayList<Book> 생성
     */
    private BookManagerImpl() {
        super();
        //books = new Book[MAX_SIZE];
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("book.dat"));
            books = (ArrayList<Book>)in.readObject();
        } catch (IOException e) {
            books = new ArrayList<Book>();
        } catch (ClassNotFoundException e) {
            books = new ArrayList<Book>();
        } finally {
            try{
                if (in != null)
                    in.close();
            } catch (IOException closeError) {
                System.out.println("fail to close the resource");
            }
        }
        
        System.out.println("BookMananger initialized");
    }
    // Singleton Pattern
    // 1. 생성자를 private으로 접근 제한
    // 3. 객체 없이 클래스 이름으로 참조할 수 있도록 public static instance return method 제공    
    public static IBookManager getInstance() { 
        return instance;
    }

    /**
     * add Book
     * @param book
     */
    @Override    
    public void add(Book book) {
        for (int k = 0; k < books.size(); k++) {
            if (books.get(k).getIsbn().equals(book.getIsbn())) {
                System.out.println("DB already has the book!!");
                return;
            }
        }
        books.add(book);
    }
    
    /**
     * remove Book
     * @param isbn
     */
    @Override    
    public void remove(String isbn) {
        for (int k = 0; k < books.size(); k++) {
            if (isbn.equals(books.get(k).getIsbn())) {
                books.remove(k);
                System.out.printf("**********************도서 삭제:%s**********************\n", isbn);
                break;
            }
        }
    }
    @Override    
    public Book[] getList() {
        Book[] result = new Book[books.size()];
        System.out.println("**********************도서 목록**********************"); 
        return books.toArray(result);    
    }
    @Override    
    public Book searchByIsbn(String isbn) {
        for (int k = 0; k < books.size(); k++) {
            if (isbn.equals(books.get(k).getIsbn())) {
                return books.get(k);
            }
        }
        return null;

    }
    
    /**
     * 도서 제목으로 검색
     * @param title
     * @return
     */
    @Override    
    public Book[] searchByTitle(String title) {
        Book[] searchedBooks = new Book[books.size()];
        int sSize = 0;
        System.out.printf("**********************도서 제목 포함 검색:%s**********************\n", title);
        for (int k = 0; k < books.size(); k++) {
            if (books.get(k).getTitle().contains(title)) {
                //System.out.println(books[k].toString());
                searchedBooks[sSize++] = books.get(k);
            }
        }
        return Arrays.copyOfRange(searchedBooks, 0, sSize);
    }
    
    /**
     * Search Magazine 
     * @return
     */
    @Override    
    public Magazine[] getMagazines() {
        // Magazine[] magazines = new Magazine[books.size()];
        // int mSize = 0;
        // System.out.printf("**********************잡지 목록**********************\n");
        // for (int k = 0; k < books.size(); k++) {
        //     if (books.get(k) instanceof Magazine) {
        //         //System.out.println(books[k].toString());
        //         magazines[mSize++] = (Magazine)books.get(k);
        //     }
        // }
        
        // return Arrays.copyOfRange(magazines, 0, mSize);
        List<Magazine> result = new ArrayList<Magazine>();
        for (Book book : books) {
            if(book instanceof Magazine)
                result.add((Magazine)book);
        }
        Magazine[] magazines = new Magazine[result.size()];
        return result.toArray(magazines);
    }
    
    /**
     * Search Books, except Magazine
     * @return
     */
    @Override    
    public Book[] getBooks() {
        // Book[] realBooks = new Book[books.size()];
        // int mSize = 0;
        // System.out.printf("**********************일반 도서 목록**********************\n");
        // for (int k = 0; k < books.size(); k++) {
        //     if (!(books.get(k) instanceof Magazine)) {
        //         //System.out.println(books[k].toString());
        //         realBooks[mSize++] = books.get(k);
        //     }
        // }
        // return Arrays.copyOfRange(realBooks, 0, mSize);
        List<Book> result = new ArrayList<Book>();
        for (Book book : books) {
            if (!(book instanceof Magazine))
                result.add(book);
        }
        Book[] bookList = new Book[result.size()];
        return result.toArray(bookList);
    }
    
    /**
     * Get total Price
     * @return
     */
    @Override    
    public int getTotalPrice() {
        int totalPrice = 0;
        for (int k = 0; k < books.size(); k++) {
            totalPrice += books.get(k).getPrice();
        }
        //System.out.println("도서 가격 총합 : " + totalPrice);
        return totalPrice;
    }
    @Override    
    public double getPriceAvg() {
        double avg = 0;
        for (int k = 0; k < books.size(); k++) {
            avg += books.get(k).getPrice();
        }
        avg /= books.size();
        //System.out.println("도서 가격 평균 : " + avg);
        return avg;
    }
    
    /**
     * @params : isbn:String ISBN, qunatity:int 재고량
     * @throws : ISBNNotFoundException : books is not exist exception
     * @thorws : QuantityException : 제거 부족 예외
     */
    @Override
    public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException{
        Book book = searchByIsbn(isbn);
        System.out.printf("********************** 도서 %s 판매 : %d 권 **********************\n", isbn, quantity);
        if (book == null)
            throw new ISBNNotFoundException(isbn);
        if(book.getQuantity() < quantity)
            throw new QuantityException();
        book.setQuantity(book.getQuantity() - quantity);
    }
    @Override
    public void buy(String isbn, int quantity) throws ISBNNotFoundException {
        System.out.printf("********************** 도서 %s 구매 : %d 권 **********************\n", isbn, quantity);
        Book book = searchByIsbn(isbn);
        if (book == null)
            throw new ISBNNotFoundException(isbn);
        book.setQuantity(book.getQuantity() + quantity);
    }
    
    /**
     * 종료 시 List<Book> books 객체 book.dat에 저장
     */
    @Override
    public void saveData() {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("./book.dat"));
            out.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(out!=null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}