package cn.edu.nju.cocoelf.code_elf_back_end.repository;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.StubApi;
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

    public List<StubApi> searchResult(String classOrMethod, List<String> searchFunction){
        String like = "%";
        for (String str : searchFunction ) {
            like += str+"%";
        }

        String sql = "select * from stub WHERE " +
                "(class_name=? or package_name=? or NAME = ?) and" +
                " chinese like ? ";

        if(classOrMethod==null || classOrMethod.trim().equals("")){
             sql = "select * from stub WHERE " +
                    "(class_name=? or package_name=? or NAME = ? OR 1=1) and" +
                    " chinese like ? ";
        }
        String finalLike = like;
        Object obj = jdbcTemplate.execute(sql, new PreparedStatementCallback() {

            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1,classOrMethod);
                preparedStatement.setString(2,classOrMethod);
                preparedStatement.setString(3,classOrMethod);
                preparedStatement.setString(4, finalLike);
                ResultSet rs= preparedStatement.executeQuery();
                List<StubApi> list  = new ArrayList<>();
                while(rs.next()){
                    StubApi stubApi = new StubApi();
                    stubApi.setPackage_Name(rs.getString("package_name"));
                    stubApi.setClass_Name(rs.getString("class_name"));
                    stubApi.setName(rs.getString("name"));
                    stubApi.setChinese(rs.getString("chinese"));
                    stubApi.setType(rs.getString("type"));
                    stubApi.setPage(rs.getString("page"));
                    String pos = rs.getString("position");
                    int ind = pos.indexOf('(');
                    if(ind > 0){
                        pos = pos.substring(0,ind);
                    }
                    stubApi.setPosition(pos);
                    list.add(stubApi);
                }
                return list;
            }
        });

        return (List<StubApi>) obj;

    }
}
