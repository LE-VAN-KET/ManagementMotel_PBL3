package controller.admin.village;

import bean.VillageModel;
import service.IVillageService;
import utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/delete-village"})
public class DeleteVillageController extends HttpServlet {
    @Inject
    private IVillageService villageService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        VillageModel villageModel = FormUtil.toModel(VillageModel.class, request);
        villageService.delete(villageModel);
        response.sendRedirect("/admin/village");
    }
}
