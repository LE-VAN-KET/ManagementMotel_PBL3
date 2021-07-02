package mapper.implement;

import mapper.IRowMapper;
import notificationMessage.NotificationMessage;

import java.sql.ResultSet;

public class NotificationMessageMapper implements IRowMapper<NotificationMessage> {
    @Override
    public NotificationMessage mapRow(ResultSet resultSet) {
        NotificationMessage notificationMessage = new NotificationMessage();
        try {
            notificationMessage.setRecipinetId(resultSet.getLong("message.fromUserId"));
            notificationMessage.setAmount(resultSet.getLong("amount"));
            return notificationMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
