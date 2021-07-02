package service;

import bean.MessageModel;
import notificationMessage.NotificationMessage;

import java.util.List;

public interface IMessageService {
    Long insert(MessageModel messageModel);
    void update(MessageModel messageModel);
    void delete(Long messageId);
    List<MessageModel> findBySenderIdOrRecipientId(Long senderId, Long recipientId);
    MessageModel findByMessageId(Long messageId);
    List<String> validateMessage(MessageModel messageModel);
    List<MessageModel> getLastMessageChat(Long senderId);
    void updateSeen(Long senderId, Long recipientId, Boolean isSeen);
    List<NotificationMessage> notificationMessage(Long recipientId, Boolean isSeen);
}
