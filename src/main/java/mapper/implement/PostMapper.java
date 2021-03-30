package mapper.implement;

import mapper.IRowMapper;
import bean.PostModel;

import java.sql.ResultSet;

public class PostMapper implements IRowMapper<PostModel> {
    @Override
    public PostModel mapRow(ResultSet resultSet) {
        return null;
    }
}
