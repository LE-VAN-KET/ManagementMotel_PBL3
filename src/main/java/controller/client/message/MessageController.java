package controller.client.message;

import bean.AccountModel;
import bean.MessageModel;
import constant.SystemConstant;
import service.IMessageService;
import utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/message"})
public class MessageController extends HttpServlet {
//    @Inject
//    private IUserService userService;

    @Inject
    private IMessageService messageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountModel sender = (AccountModel) SessionUtil.getInstance().getValue(req, SystemConstant.ACCOUNTMODEL);
        List<MessageModel> messageModelList = messageService.getLastMessageChat(sender.getUser().getUserId());
        if (!messageModelList.isEmpty()) {
//            req.setAttribute(SystemConstant.LISTLASTMESSAGE, messageModelList);
            req.setAttribute(SystemConstant.SENDER, sender.getUser());
//            req.setAttribute(SystemConstant.RECIPIENT, recipient);
            req.getRequestDispatcher("/views/client/layouts/Chat.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/home?message=no_message&&alert=danger");
        }
    }
}
