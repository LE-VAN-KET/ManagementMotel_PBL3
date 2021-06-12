package service.implement;

import bean.AccountModel;
import bean.CommentModel;
import dao.ICommentDAO;
import dao.IPostDAO;
import service.ICommentService;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
public class CommentService implements ICommentService {
    @Inject
    private ICommentDAO commentDAO;

    @Inject
    private IPostDAO postDAO;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    public List<String> validateComment(CommentModel commentModel) {
        List<String> errors = new ArrayList<>();
        if (isAll_Fields_Empty(commentModel.getContent(), commentModel.getUserId(), commentModel.getPostId())) {
            errors.add(resourceBundle.getString("all_fields_not_empty"));
        } else {
            int count = postDAO.countByPostId(commentModel.getPostId());
            if (count == 0) {
                errors.add(resourceBundle.getString("comment_failed"));
            }
        }
        return errors;
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
    public Long insert(CommentModel commentModel) {
        return commentDAO.insert(commentModel);
    }

    @Override
    public void delete(Long commentId) {
        delete(commentId);
    }

    @Override
    public void update(CommentModel commentModel) {
        update(commentModel);
    }

    @Override
    public String authorizationUser(AccountModel accountModel) {
        if (accountModel != null) {
            // check information account
            if (accountModel.getUser().getFullName() == null || "".equals(accountModel.getUser().getFullName())
                    || accountModel.getUser().getEmail() == null || "".equals(accountModel.getUser().getEmail())
                    || accountModel.getUser().getSDT() == null || "".equals(accountModel.getUser().getSDT())) {
                return "/edit-profile?error=finish_filled_information_profile&&alert=danger";
            }
            return null;
        } else {
            /*
             * not permission access, done
             * redirect /home
             * */
            return "/home?message=not_permission_comment&&alert=danger";
        }
    }
}
