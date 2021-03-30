package service.implement;

import dao.IMessageDAO;
import service.IMessageService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class MessageService implements IMessageService {
    @Inject
    private IMessageDAO messageDAO;
}
