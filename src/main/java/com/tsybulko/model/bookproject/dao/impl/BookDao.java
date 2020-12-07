package com.tsybulko.model.dao.impl;

import com.tsybulko.model.dao.IBookDao;
import com.tsybulko.model.entity.Book;
import com.tsybulko.model.entity.BookWarehouse;
import exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    @Override
    public void addBook(Book book) throws DaoException {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.all();
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
        List<Book> allBooks = new ArrayList<Book>(warehouse.all());
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
    public List<Book> findById(String id) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.all();
        List<Book> searchedBook = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                searchedBook.add(book);
                break;
            }
        }
        return searchedBook;
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getTitle().equals(title)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthors().contains(author)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByYear(String year) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getYear()).equals(year)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByPages(String pages) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getPages()).equals(pages)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.all();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getPublishingHouse().equals(publishingHouse)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> sortById() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookIdComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortByTitle() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookTitleComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortByYear() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.all();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookYearComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortByPages() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.all();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookPagesComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortByPublishingHouse() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.all();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookPublishingHouseComparator());
        return sortedList;
    }
}

