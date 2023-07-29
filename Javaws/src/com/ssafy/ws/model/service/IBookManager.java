package com.ssafy.ws.model.service;

import com.ssafy.ws.exception.ISBNNotFoundException;
import com.ssafy.ws.exception.QuantityException;
import com.ssafy.ws.model.Book;
import com.ssafy.ws.model.Magazine;

public interface IBookManager {
    public abstract void add(Book book);

    public abstract void remove(String isbn);

    public abstract Book[] getList();

    public abstract Book searchByIsbn(String isbn);

    public abstract Book[] searchByTitle(String title);

    public abstract Magazine[] getMagazines();

    public abstract Book[] getBooks();

    public abstract int getTotalPrice();

    public abstract double getPriceAvg();

    void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException;

    void buy(String isbn, int quantity) throws ISBNNotFoundException;

    public abstract void saveData();
}
