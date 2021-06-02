package controller.admin.village;

import bean.DistrictModel;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/edit-village"})
public class EditVillageController extends HttpServlet {
    @Inject
    private IVillageService villageService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        VillageModel villageModel = FormUtil.toModel(VillageModel.class, request);
        getDistrictFromRequest(request, villageModel);
        Map<String, String> errors = villageService.validateVillage(villageModel);
        if (errors.size() != 0) {
            request.setAttribute("errors_edit", errors);
            request.setAttribute("village_id", villageModel.getVillageId());
            request.getRequestDispatcher("/admin/village").forward(request, response);
        } else {
            villageService.update(villageModel);
            response.sendRedirect("/admin/village");
        }
    }

    private void getDistrictFromRequest(HttpServletRequest request, VillageModel villageModel) {
        Long districtId = Long.parseLong(request.getParameter("districtId"));
        DistrictModel districtModel = new DistrictModel();
        districtModel.setDistrictId(districtId);
        villageModel.setDistrictModel(districtModel);
    }
}
