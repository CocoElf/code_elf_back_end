package cn.edu.nju.cocoelf.code_elf_back_end.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PythonAPIDao {
    private  JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public String testConnection(){

        String sql = "select * from api where id=1";
        Object obj = jdbcTemplate.execute(sql,new PreparedStatementCallback(){

            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                ResultSet rs= preparedStatement.executeQuery();
                List<String> list  = new ArrayList<>();
                while(rs.next()){
                    list.add(rs.getString("name"));
                }
                return list;
            }
        });

        List<String> liststr  = (List) obj;
        return liststr.get(0);

    }
}
