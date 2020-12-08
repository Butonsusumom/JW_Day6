package com.tsybulko.model.bookproject.dao;

import com.tsybulko.model.bookproject.entity.Book;
import exception.DaoException;

import java.util.LinkedList;

public interface IBookDao {
    void addBook(Book book) throws DaoException;

    void removeBook(Book book) throws DaoException;

    LinkedList<Book> findById(String id);

    LinkedList<Book> findByTitle(String title);

    public LinkedList<Book> findAll();

    LinkedList<Book> findByYear(String year);

    LinkedList<Book> findByPages(String pages);

    LinkedList<Book> findByPublishingHouse(String publishingHouse);

    LinkedList<Book> sortById();

    LinkedList<Book> sortByTitle();

    LinkedList<Book> sortByYear();

    LinkedList<Book> sortByPages();

    LinkedList<Book> sortByPublishingHouse();
}
