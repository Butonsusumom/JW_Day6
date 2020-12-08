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
            dao.add(book);

        } catch (DaoException exception) {
            throw new exception.ServiceException(exception.getMessage());
        }
    }


    public void removeFromDaoById(long id) throws exception.ServiceException {
        try {
            dao.removeById(id);
        } catch (DaoException exception) {
            throw new exception.ServiceException(exception);
        }
    }


    public void removeFromDaoByIndex(int index) throws exception.ServiceException {
        try {
            dao.removeByIndex(index);
        } catch (DaoException exception) {
            throw new exception.ServiceException(exception);
        }
    }


    public void changeBookLabelInDao(long id, String newLabel) throws exception.ServiceException {
        if (newLabel == null) {
            throw new exception.ServiceException("New label was null");
        }
        if (!BookValidator.validateTitle(newLabel)) {
            throw new exception.ServiceException("Invalid book label");
        }
        try {
            dao.changeBookLabel(id, newLabel);
        } catch (DaoException exception) {
            throw new exception.ServiceException(exception.getMessage());
        }
    }

    public LinkedList<Book> findAllInDao() {
        return dao.findAll();
    }


    public LinkedList<Book> findByIndex(String index) {
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