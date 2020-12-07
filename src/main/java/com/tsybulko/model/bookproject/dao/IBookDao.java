package com.tsybulko.model.dao;

import com.tsybulko.model.entity.Book;
import exception.DaoException;

import java.util.List;

public interface IBookDao {
    void addBook(Book book) throws DaoException;

    void removeBook(Book book) throws BookException;

    List<Book> findById(String id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByYear(String year);

    List<Book> findByPages(String pages);

    List<Book> findByPublishingHouse(String publishingHouse);

    List<Book> sortById();

    List<Book> sortByTitle();

    List<Book> sortByYear();

    List<Book> sortByPages();

    List<Book> sortByPublishingHouse();
}
