package service.implement;

import dao.ICommentDAO;
import service.ICommentService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class CommentService implements ICommentService {
    @Inject
    private ICommentDAO commentDAO;
}
