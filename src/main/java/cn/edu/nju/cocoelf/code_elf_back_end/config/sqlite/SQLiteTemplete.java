package cn.edu.nju.cocoelf.code_elf_back_end.config.sqlite;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SQLiteTemplete {
    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
