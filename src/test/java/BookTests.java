import com.tsybulko.model.bookproject.dao.IBookDao;
import com.tsybulko.model.bookproject.dao.impl.BookDao;
import com.tsybulko.model.bookproject.entity.Book;
import com.tsybulko.model.bookproject.service.BookComparator;
import com.tsybulko.model.bookproject.service.BookService;
import com.tsybulko.model.bookproject.util.IdGenerator;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertEquals;

public class BookTests {

    private BookService service = new BookService();
    private IBookDao dao = new BookDao();
    private LinkedList<Book> reference = new  LinkedList<>();

    @BeforeMethod
    public void initDao() {
        reference.clear();
        IdGenerator idgen = new IdGenerator();
        reference.add(new Book(String.valueOf(idgen.generateNewId()),"Voina and Mir", new LinkedList<String>(Collections.singleton("Lev Tolstoy")),1865, 2000, "No"));
        reference.add(new Book(String.valueOf(idgen.generateNewId()),"Anna Korenina", new LinkedList<String>(Collections.singleton("Lev Tolstoy")),1877, 1000, "No"));
        reference.add(new Book(String.valueOf(idgen.generateNewId()),"Jen Sincero", new LinkedList<String>(Collections.singleton("Jen Sincero")),2018, 800, "Viking"));
        reference.add(new Book(String.valueOf(idgen.generateNewId()),"The Great Gatsby", new LinkedList<String>(Collections.singleton(" F. Scott Fitzgerald")),1926, 216, "Charles Scribner's Sons"));
        try {
            for (Book book : reference) {
                dao.addBook(book);
            }
        } catch (exception.DaoException exception) {

        }

    }

    @Test
    public void testRemoveFromDao() {
        try {
            service.removeFromDaoById("2");
        } catch (exception.ServiceException exception) {

        }
        reference.remove(2);
        LinkedList<Book> actual = service.findAllInDao();
        LinkedList<Book> expected = reference;
        assertEquals(actual, expected);
    }

    @Test
    public void testFindAllInDao() {
        LinkedList<Book> actual = service.findAllInDao();
        LinkedList<Book> expected = reference;
        assertEquals(actual, expected);
    }

}
