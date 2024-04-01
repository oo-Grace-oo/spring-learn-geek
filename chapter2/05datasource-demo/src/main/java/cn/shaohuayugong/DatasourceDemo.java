package cn.shaohuayugong;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2023/12/06
 */
@SpringBootApplication
@Slf4j
public class DatasourceDemo implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    public static void main(String[] args) {
        SpringApplication.run(DatasourceDemo.class, args);
    }

    private void showConnection() throws SQLException {
        log.warn(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.warn(conn.toString());
        conn.close();
    }

    private void showData() {
        jdbcTemplate.queryForList("SELECT * FROM FOO")
                .forEach(row -> log.warn(row.toString()));
    }
}
