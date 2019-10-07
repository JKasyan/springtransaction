package spring.transaction.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class AuthorDao {

    private final SimpleJdbcInsert insertAuthor;

    public AuthorDao(DataSource dataSource) {
        this.insertAuthor = new SimpleJdbcInsert(dataSource)
                .withTableName("author")
                .usingGeneratedKeyColumns("author_id");
    }

    public long insertAuthor(String firstName, String lastName, long bookId) {
        Map<String,Object> m = new HashMap<>();
        m.put("first_name", firstName);
        m.put("last_name", lastName);
        m.put("book_id", bookId);
        return (long)insertAuthor.executeAndReturnKey(m);
    }
}
