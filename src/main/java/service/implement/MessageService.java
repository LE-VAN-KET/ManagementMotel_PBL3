package service.implement;

import bean.MessageModel;
import bean.UserModel;
import dao.IMessageDAO;
import dao.IUserDAO;
import dao.implement.MessageDAO;
import dao.implement.UserDAO;
import notificationMessage.NotificationMessage;
import service.IMessageService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
public class MessageService implements IMessageService {
    @Inject private IMessageDAO messageDAO;

    @Inject private IUserDAO userDAO;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    private static MessageService instance;
    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageService(MessageDAO.getInstance(), UserDAO.getInstance());
        }
        return instance;
    }

    private MessageService(IMessageDAO messageDAO, IUserDAO userDAO) {
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
    }

    public MessageService() {}

    @Override
    public Long insert(MessageModel messageModel) {
        return messageDAO.insert(messageModel);
    }

    @Override
    public void update(MessageModel messageModel) {
        messageDAO.update(messageModel);
    }

    @Override
    public void delete(Long messageId) {
        messageDAO.delete(messageId);
    }

    @Override
    public List<MessageModel> findBySenderIdOrRecipientId(Long senderId, Long recipientId) {
        return messageDAO.findBySenderIdOrRecipientId(senderId, recipientId);
    }

    @Override
    public MessageModel findByMessageId(Long messageId) {
        return messageDAO.findByMessageId(messageId);
    }

    private boolean isAll_Fields_Empty(Object... parameters) {
        for (Object parameter: parameters) {
            if ((parameter == null) || ("".equals(parameter.toString()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> validateMessage(MessageModel messageModel) {
        List<String> errors = new ArrayList<>();
        if (isAll_Fields_Empty(messageModel.getBody(), messageModel.getSender().getUserId(),
                messageModel.getRecipient().getUserId(), messageModel.getSeen())) {
            errors.add(resourceBundle.getString("all_fields_not_empty"));
        } else {
            // check userId not exist
            UserModel sender = userDAO.findOne(messageModel.getSender().getUserId());
            UserModel receiver = userDAO.findOne(messageModel.getRecipient().getUserId());
            if (sender == null || receiver == null) {
                errors.add(resourceBundle.getString("senderId_or_receiverId_not_exist"));
            }

            // check senderId equal receiverId
            if (messageModel.getSender().getUserId() == messageModel.getRecipient().getUserId()) {
                errors.add(resourceBundle.getString("not_sent_to_yourself"));
            }
        }
        return errors;
    }

    @Override
    public List<MessageModel> getLastMessageChat(Long senderId) {
        return messageDAO.getLastMessageChat(senderId);
    }

    @Override
    public void updateSeen(Long senderId, Long recipientId, Boolean isSeen) {
        messageDAO.updateSeen(senderId, recipientId, isSeen);
    }

    @Override
    public List<NotificationMessage> notificationMessage(Long recipientId, Boolean isSeen) {
        return messageDAO.notificationMessage(recipientId, isSeen);
    }
}
