package com.tsybulko.model.bookproject.util;

import com.tsybulko.model.bookproject.entity.Book;
import com.tsybulko.model.bookproject.entity.BookWarehouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {
    BookWarehouse bookStorage = BookWarehouse.getInstance();
    IdGenerator idGenerator = IdGenerator.getInstance();

    private static final int FIELD_COUNT = 4;
    private static final String FIELD_SPLITTER = ", ";
    private static final String ARRAY_SPLITTER = "\n";
    private static final int TITLE_FIELD_NUMBER = 0;
    private static final int AUTHOR_FIELD_NUMBER = 1;
    private static final int YEAR_FIELD_NUMBER = 2;
    private static final int PAGES_FIELD_NUMBER = 3;
    private static final int HOUSE_FIELD_NUMBER = 4;

    public void fromFile(String path) {
        File file = new File(path);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException exception) {
            return;
        }

        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Book book = parseBook(line);
                bookStorage.add(book);
            }
        } catch (IllegalStateException | NoSuchElementException | IllegalArgumentException exception) {

        } finally {
            scanner.close();
        }
    }

    public Book parseBook(String value) {
        String[] fields = value.split(FIELD_SPLITTER);
        if (fields.length != FIELD_COUNT) {
            throw new IllegalArgumentException("Wrong book format");
        }
        String id = String.valueOf(idGenerator.generateNewId());

        String title = fields[TITLE_FIELD_NUMBER];

        String[] authorsArray = fields[AUTHOR_FIELD_NUMBER].split(ARRAY_SPLITTER);
        LinkedList<String> authorsList = (LinkedList<String>) Arrays.asList(authorsArray);

        int year = Integer.parseInt(fields[YEAR_FIELD_NUMBER]);

        int pages = Integer.parseInt(fields[PAGES_FIELD_NUMBER]);

        String house = fields[HOUSE_FIELD_NUMBER];

        return new Book(id, title, authorsList, year, pages, house);
    }
}
