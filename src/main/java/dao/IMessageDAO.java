package dao;

import bean.MessageModel;
import notificationMessage.NotificationMessage;

import java.util.List;

public interface IMessageDAO {
    Long insert(MessageModel messageModel);
    void update(MessageModel messageModel);
    void delete(Long messageId);
    List<MessageModel> findBySenderIdOrRecipientId (Long senderId, Long recipientId);
    MessageModel findByMessageId(Long messageId);
    List<MessageModel> getLastMessageChat(Long senderId);
    void updateSeen(Long senderId, Long recipientId, Boolean isSeen);
    List<NotificationMessage> notificationMessage(Long recipientId, Boolean isSeen);
}
