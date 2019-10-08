package spring.transaction.service;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import spring.transaction.dao.AuthorDao;
import spring.transaction.dao.BookDao;

import java.nio.charset.StandardCharsets;

public class LibraryService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private RabbitTemplate rabbitTemplate;
    private Logger logger = Logger.getLogger(this.getClass());

    public LibraryService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public int insertBook() {
        try {
            Message msg = this.rabbitTemplate.receive("hello", 1000L);
            String title = new String(msg.getBody(), StandardCharsets.UTF_8);
            logger.debug("title: " + title);
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
