package mapper.implement;

import mapper.IRowMapper;
import bean.CommentModel;

import java.sql.ResultSet;

public class CommentMapper implements IRowMapper<CommentModel> {
    @Override
    public CommentModel mapRow(ResultSet resultSet) {
        return null;
    }
}
