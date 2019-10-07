package spring.transaction.service;
import org.apache.log4j.Logger;
import spring.transaction.dao.AuthorDao;
import spring.transaction.dao.BookDao;

public class LibraryService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private Logger logger = Logger.getLogger(this.getClass());

    public LibraryService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public int insertBook(String title) {
        try {
            long bookId = bookDao.insertBook(title);
            logger.debug("bookId: " + bookId);
            long authorId = authorDao.insertAuthor("foo", "bar", bookId);
            logger.debug("authorId: " + authorId);
            return 0;
        } finally {
            throw new RuntimeException();
        }
    }
}
