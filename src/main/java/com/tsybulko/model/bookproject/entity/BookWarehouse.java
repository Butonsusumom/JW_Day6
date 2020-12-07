package com.tsybulko.model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BookWarehouse {
    private static BookWarehouse bookWarehouseInstance;
    private LinkedList<Book> books;

    private BookWarehouse() {
        books = new LinkedList<>();
    }

    public static BookWarehouse getInstance() {
        if (bookWarehouseInstance == null) {
            bookWarehouseInstance = new BookWarehouse();
        }
        return bookWarehouseInstance;
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
