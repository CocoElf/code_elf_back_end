package cn.edu.nju.cocoelf.code_elf_back_end.repository;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.FunctionAPI;
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
        Object obj = jdbcTemplate.execute(sql, new PreparedStatementCallback() {

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

    public List<FunctionAPI> searchResult(String classOrMethod, List<String> searchFunction){
        String like = "%";
        for (String str : searchFunction ) {
            like += str+"%";
        }
        String sql = "select * from api WHERE (class_name=? or package_name=?) and chinese like ? ";
        String finalLike = like;
        Object obj = jdbcTemplate.execute(sql, new PreparedStatementCallback() {

            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1,classOrMethod);
                preparedStatement.setString(2,classOrMethod);
                preparedStatement.setString(3, finalLike);
                ResultSet rs= preparedStatement.executeQuery();
                List<FunctionAPI> list  = new ArrayList<>();
                while(rs.next()){
                    FunctionAPI functionAPI= new FunctionAPI();
                    functionAPI.setPackage_Name(rs.getString("package_name"));
                    functionAPI.setClass_Name(rs.getString("class_name"));
                    functionAPI.setName(rs.getString("name"));
                    functionAPI.setChinese(rs.getString("chinese"));
                    functionAPI.setType(rs.getString("type"));
                    list.add(functionAPI);
                }
                return list;
            }
        });

        return (List<FunctionAPI>) obj;

    }
}
