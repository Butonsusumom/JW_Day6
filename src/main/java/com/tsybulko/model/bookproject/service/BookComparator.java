package com.tsybulko.model.bookproject.service;

import com.tsybulko.model.bookproject.entity.Book;

import java.util.Comparator;

public class BookComparator {
    public static class BookAuthorsComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            boolean flag=true;
            if (o1.getAuthors().size()== o2.getAuthors().size()) {
                for (int i=0;i<o1.getAuthors().size();i++) {
                    if (!o1.getAuthors().get(i).equals(o2.getAuthors().get(i))){
                        flag=false;
                    }
                }
            }
            if (flag) return o1.getAuthors().get(0).compareTo(o2.getAuthors().get(0));
            return o1.getAuthors().size() - o2.getAuthors().size();
        }
    }

    public static class BookIdComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    public static class BookPagesComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPages() - o2.getPages();
        }
    }

    public static class BookPublishingHouseComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPublishingHouse().compareTo(o2.getPublishingHouse());
        }
    }

    public static class BookTitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    public static class BookYearComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getYear() - o2.getYear();
        }
    }
}
