package com.tsybulko.model.bookproject.entity;

import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

public class BookWarehouse {
    private static BookWarehouse bookWarehouseInstance;
    private LinkedList<Book> books;
    private static int MAX = 200;

    private BookWarehouse() {
        books = new LinkedList<>();
    }

    public static BookWarehouse getInstance() {
        if (bookWarehouseInstance == null) {
            bookWarehouseInstance = new BookWarehouse();
        }
        return bookWarehouseInstance;
    }

    public void add(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public boolean isFull() {
        return books.size() >= MAX;
    }

    public void clean() {
        books = new LinkedList<Book>();
    }

    public int indexOf(Book book) {
        return books.indexOf(book);
    }

    public LinkedList<Book> all() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookWarehouse)) return false;
        BookWarehouse that = (BookWarehouse) o;
        return Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookWarehouse{");
        sb.append("books=").append(books);
        sb.append('}');
        return sb.toString();
    }
}
