package dao;

import mapper.IRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    Connection getConnnection();
    <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
    Long insert(String sql, Object... parameters);
    void update(String sql, Object... parameters);
    void delete(String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
