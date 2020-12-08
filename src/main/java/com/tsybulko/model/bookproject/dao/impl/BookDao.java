package com.tsybulko.model.bookproject.dao.impl;

import com.tsybulko.model.bookproject.dao.IBookDao;
import com.tsybulko.model.bookproject.entity.Book;
import com.tsybulko.model.bookproject.entity.BookWarehouse;

import com.tsybulko.model.bookproject.service.BookComparator;
import exception.DaoException;

import java.util.LinkedList;


public class BookDao implements IBookDao {
    @Override
    public void addBook(Book book) throws DaoException {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        if (allBooks.contains(book)) {
            throw new DaoException("Such book is already exists");
        }
        if (warehouse.isFull()) {
            throw new DaoException("Warehouse is full");
        }
        warehouse.add(book);
    }

    @Override
    public void removeBook(Book removingBook)
            throws DaoException {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = new LinkedList<Book>(warehouse.all());
        boolean result = false;
        for (Book book : allBooks) {
            if (isSimilar(book, removingBook)) {
                warehouse.remove(book);
                result = true;
            }
        }
        if (!result) {
            throw new DaoException("Such book doesn't exist!");
        }
    }

    private boolean isSimilar(Book book, Book removingBook) {
        return (book.getTitle().equals(removingBook.getTitle()) &&
                book.getAuthors().equals(removingBook.getAuthors()) &&
                book.getYear() == removingBook.getYear() &&
                book.getPages() == removingBook.getPages() &&
                book.getPublishingHouse()
                        .equals(removingBook.getPublishingHouse()));
    }

    @Override
    public LinkedList<Book> findById(String id) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> searchedBook = new LinkedList<>();
        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                searchedBook.add(book);
                break;
            }
        }
        return searchedBook;
    }


    @Override
    public LinkedList<Book> findByTitle(String title) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> resultList = new LinkedList<>();
        for (Book book : allBooks) {
            if (book.getTitle().equals(title)) {
                resultList.add(book);
            }
        }
        return resultList;
    }


    @Override
    public  LinkedList<Book> findByYear(String year) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> resultList = new LinkedList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getYear()).equals(year)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public LinkedList<Book> findByPages(String pages) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> resultList = new LinkedList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getPages()).equals(pages)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public LinkedList<Book> findByPublishingHouse(String publishingHouse) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> resultList = new LinkedList<>();
        for (Book book : allBooks) {
            if (book.getPublishingHouse().equals(publishingHouse)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public LinkedList<Book> findAll(){
        BookWarehouse warehouse = BookWarehouse.getInstance();
        return  warehouse.all();
    }

    @Override
    public LinkedList<Book> sortById() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> sortedList = new LinkedList<>(allBooks);
        sortedList.sort(new BookComparator.BookIdComparator());
        return sortedList;
    }

    @Override
    public LinkedList<Book> sortByTitle() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> sortedList = new LinkedList<>(allBooks);
        sortedList.sort(new BookComparator.BookTitleComparator());
        return sortedList;
    }

    @Override
    public LinkedList<Book> sortByYear() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> sortedList = new LinkedList<>(allBooks);
        sortedList.sort(new BookComparator.BookYearComparator());
        return sortedList;
    }

    @Override
    public LinkedList<Book> sortByPages() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> sortedList = new LinkedList<>(allBooks);
        sortedList.sort(new BookComparator.BookPagesComparator());
        return sortedList;
    }

    @Override
    public LinkedList<Book> sortByPublishingHouse() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        LinkedList<Book> allBooks = warehouse.all();
        LinkedList<Book> sortedList = new LinkedList<>(allBooks);
        sortedList.sort(new BookComparator.BookPublishingHouseComparator());
        return sortedList;
    }
}

