package dao.implement;

import bean.MessageModel;
import dao.IMessageDAO;
import mapper.implement.MessageMapper;
import mapper.implement.NotificationMessageMapper;
import notificationMessage.NotificationMessage;

import java.util.List;

public class MessageDAO extends AbstractDAO<MessageModel> implements IMessageDAO {
    private static MessageDAO instance;
    public static MessageDAO getInstance() {
        if (instance == null) {
            instance = new MessageDAO();
        }
        return instance;
    }

    private StringBuilder sqlQuery() {
        StringBuilder _query = new StringBuilder("select * from message inner join users as sender on");
        _query.append(" message.fromUserId = sender.userId inner join role as sender_role on sender.roleId");
        _query.append(" = sender_role.roleId inner join users as recipient on message.toUserId = recipient.userId");
        _query.append(" inner join role as recipient_role on recipient_role.roleId = recipient.roleId ");
        return _query;
    }

    @Override
    public Long insert(MessageModel messageModel) {
        String sql = "INSERT INTO message(body, fromUserId, toUserId, isSeen) values(?, ?, ?, ?)";
        return insert(sql, messageModel.getBody(), messageModel.getSender().getUserId(),
                messageModel.getRecipient().getUserId(), messageModel.getSeen());
    }

    @Override
    public void update(MessageModel messageModel) {
        String sql = "update message set body = ? where messageId = ?";
        update(sql, messageModel.getBody(), messageModel.getMessageId());
    }

    @Override
    public void delete(Long messageId) {
        String sql = "delete from message where messageId = ?";
        delete(sql, messageId);
    }

    @Override
    public List<MessageModel> findBySenderIdOrRecipientId(Long senderId, Long recipientId) {
        StringBuilder sql = sqlQuery();
        sql.append(" where (message.fromUserId = ? AND message.toUserId = ?) OR (message.fromUserId = ?");
        sql.append(" AND message.toUserId = ?) ORDER BY message.createAt ASC");
        return query(sql.toString(), new MessageMapper(), senderId, recipientId, recipientId, senderId);
    }

    @Override
    public MessageModel findByMessageId(Long messageId) {
        StringBuilder sql = sqlQuery();
        sql.append(" where messageId = ?");
        List<MessageModel> messageModels = query(sql.toString(), new MessageMapper(), messageId);
        return messageModels.isEmpty() ? null: messageModels.get(0);
    }

    @Override
    public List<MessageModel> getLastMessageChat(Long senderId) {
        StringBuilder sql = sqlQuery();
        sql.append(" INNER JOIN ( SELECT LEAST(fromUserId, toUserId) AS user_1,");
        sql.append(" GREATEST(fromUserId, toUserId) AS user_2, MAX(messageId) AS max_id FROM message WHERE ? in");
        sql.append(" (fromUserId, toUserId) GROUP BY LEAST(fromUserId, toUserId), GREATEST(fromUserId, toUserId))");
        sql.append(" AS message_last ON LEAST(message.fromUserId, message.toUserId) = message_last.user_1 AND");
        sql.append(" GREATEST(message.fromUserId, message.toUserId) = message_last.user_2 AND");
        sql.append(" message.messageId = message_last.max_id ORDER BY message.createAt DESC");
        return query(sql.toString(), new MessageMapper(), senderId);
    }

    @Override
    public void updateSeen(Long senderId, Long recipientId, Boolean isSeen) {
        String sql = "update message set isSeen = ? where fromUserId = ? and toUserId = ? and isSeen = ?";
        update(sql, isSeen, senderId, recipientId, !isSeen);
    }

    @Override
    public List<NotificationMessage> notificationMessage(Long recipientId, Boolean isSeen) {
        StringBuilder sql = new StringBuilder("select message.fromUserId, count(*) as amount from message");
        sql.append(" where toUserId = ? and isSeen = ? group by fromUserId");
        return query(sql.toString(), new NotificationMessageMapper(), recipientId, isSeen);
    }
}
