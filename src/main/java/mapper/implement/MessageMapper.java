package mapper.implement;

import bean.*;
import mapper.IRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MessageMapper implements IRowMapper<MessageModel> {
    @Override
    public MessageModel mapRow(ResultSet resultSet) {
        MessageModel messageModel = new MessageModel();
        try {
            messageModel.setMessageId(resultSet.getLong("message.messageId"));
            messageModel.setBody(resultSet.getString("message.body"));
            // map user sender
            messageModel.setSender(mapSender(resultSet));

            // map user receiver
            messageModel.setRecipient(mapReceiver(resultSet));

            messageModel.setSeen(resultSet.getBoolean("message.isSeen"));
            messageModel.setCreateAt(resultSet.getTimestamp("message.createAt"));
            messageModel.setModifiedAt(resultSet.getTimestamp("message.modifiedAt"));
            return messageModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserModel mapSender(ResultSet resultSet) throws SQLException {
        UserModel sender = new UserModel();
        sender.setUserId(resultSet.getLong("sender.userId"));
        sender.setFullName(resultSet.getString("sender.fullName"));
        sender.setEmail(resultSet.getString("sender.email"));
        sender.setSDT(resultSet.getString("sender.SDT"));
        sender.setCreateAt(resultSet.getTimestamp("sender.createAt"));
        sender.setModifiedAt(resultSet.getTimestamp("sender.modifiedAt"));
        // map role sender
        RoleModel senderRole = new RoleModel();
        senderRole.setRoleId(resultSet.getLong("sender_role.roleId"));
        senderRole.setRoleName(resultSet.getString("sender_role.roleName"));
        senderRole.setCreateAt(resultSet.getTimestamp("sender_role.createAt"));
        senderRole.setModifiedAt(resultSet.getTimestamp("sender_role.modifiedAt"));
        sender.setRoleModel(senderRole);
        return sender;
    }

    private UserModel mapReceiver(ResultSet resultSet) throws SQLException {
        UserModel recipient = new UserModel();
        recipient.setUserId(resultSet.getLong("recipient.userId"));
        recipient.setFullName(resultSet.getString("recipient.fullName"));
        recipient.setEmail(resultSet.getString("recipient.email"));
        recipient.setSDT(resultSet.getString("recipient.SDT"));
        recipient.setCreateAt(resultSet.getTimestamp("recipient.createAt"));
        recipient.setModifiedAt(resultSet.getTimestamp("recipient.modifiedAt"));
        // map role receiver
        RoleModel recipientRole = new RoleModel();
        recipientRole.setRoleId(resultSet.getLong("recipient_role.roleId"));
        recipientRole.setRoleName(resultSet.getString("recipient_role.roleName"));
        recipientRole.setCreateAt(resultSet.getTimestamp("recipient_role.createAt"));
        recipientRole.setModifiedAt(resultSet.getTimestamp("recipient_role.modifiedAt"));
        recipient.setRoleModel(recipientRole);
        return recipient;
    }
}
