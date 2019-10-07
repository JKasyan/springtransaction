package spring.transaction.dao;


import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class BookDao {

    private final JdbcTemplate template;
    private static Logger logger = Logger.getLogger(BookDao.class);

    public BookDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public long insertBook(String title) {
        final String sql = "INSERT INTO Book (title) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
