package com.tsybulko.model.bookproject.service;

import com.tsybulko.model.bookproject.dao.IBookDao;
import com.tsybulko.model.bookproject.dao.impl.BookDao;
import com.tsybulko.model.bookproject.entity.Book;
import com.tsybulko.model.bookproject.service.validator.BookValidator;
import exception.DaoException;

import java.util.Comparator;
import java.util.LinkedList;

public class BookService {
    IBookDao dao = new BookDao();

    public void addToDao(Book book) throws exception.ServiceException {
        if (book == null) {
            throw new exception.ServiceException("Book was null");
        }
        if (!BookValidator.validateTitle(book.getTitle())) {
            throw new exception.ServiceException("Invalid book label");
        }
        if (!BookValidator.validateYear(book.getYear())) {
            throw new exception.ServiceException("Invalid publish year");
        }
        for (String author: book.getAuthors()){
            if (!BookValidator.validateAuthor(author)) {
                throw new exception.ServiceException("Invalid Authors");
            }
        }
        try {
            dao.addBook(book);

        } catch (DaoException exception) {
            throw new exception.ServiceException(exception.getMessage());
        }
    }


    public void removeFromDaoById(String id) throws exception.ServiceException {
        try {
            LinkedList<Book> book = dao.findById(id);
            dao.removeBook(book.get(0));
        } catch (DaoException exception) {
            throw new exception.ServiceException(exception);
        }
    }


    public LinkedList<Book> findAllInDao() {
        return dao.findAll();
    }


    public LinkedList<Book> findById(String index) {
        LinkedList<Book> result;
        result = dao.findById(index);
        return result;
    }

    public  LinkedList<Book> findByYearInDao(String year, Comparator<Book> comparator) {
        LinkedList<Book> result = dao.findByYear(year);
        result.sort(comparator);
        return result;
    }
}