package controller.admin;

import bean.DistrictModel;
import bean.VillageModel;
import service.IDistrictService;
import service.IVillageService;
import utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/village"})
public class VillageController extends HttpServlet {
    @Inject
    private IVillageService villageService;

    @Inject
    private IDistrictService districtService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        switch (method) {
            case "PUT":
            {
                doPut(request,response);
                break;
            }
            case "POST":
            {
                VillageModel villageModel = FormUtil.toModel(VillageModel.class, request);
                villageService.insert(villageModel);
                response.sendRedirect("/admin/village");
                break;
            }
            case "DELETE":
            {
                doDelete(request,response);
                break;
            }
        }
        return;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        set list districts id = districtId value districtName
        List<DistrictModel> districtModels = districtService.selectAll();
        List<VillageModel> villageModels = villageService.selectAll();

        request.setAttribute("districts",districtModels);
        request.setAttribute("villages",villageModels);
        request.getRequestDispatcher("../views/admin/Village.jsp").forward(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        VillageModel villageModel = FormUtil.toModel(VillageModel.class, request);
        villageService.update(villageModel);
        response.sendRedirect("/admin/village");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        VillageModel villageModel = FormUtil.toModel(VillageModel.class, request);
        villageService.delete(villageModel);
        response.sendRedirect("/admin/village");
    }
}
