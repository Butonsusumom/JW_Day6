package com.tsybulko.model.bookproject.entity;

import java.util.LinkedList;
import java.util.Objects;


public class Book {
    private String id;
    private String title;
    private LinkedList<String> authors;
    private int year;
    private int pages;
    private String publishingHouse;

    public Book() {
    }

    public Book(String id, String title, LinkedList<String> authors, int year, int pages, String publishingHouse) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.pages = pages;
        this.publishingHouse = publishingHouse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(LinkedList<String> authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getYear() == book.getYear() &&
                getPages() == book.getPages() &&
                Objects.equals(getId(), book.getId()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthors(), book.getAuthors()) &&
                Objects.equals(getPublishingHouse(), book.getPublishingHouse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthors(), getYear(), getPages(), getPublishingHouse());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", year=").append(year);
        sb.append(", pages=").append(pages);
        sb.append(", publishingHouse='").append(publishingHouse).append('\'');
        sb.append('}');
        return sb.toString();
    }
}