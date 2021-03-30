package mapper.implement;

import mapper.IRowMapper;
import bean.MessageModel;

import java.sql.ResultSet;

public class MessageMapper implements IRowMapper<MessageModel> {
    @Override
    public MessageModel mapRow(ResultSet resultSet) {
        return null;
    }
}
