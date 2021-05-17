package dao;

import mapper.IRowMapper;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {
    Connection getConnnection();
    <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
    Long insert(String sql, Object... parameters);
    void update(String sql, Object... parameters);
    void delete(String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
