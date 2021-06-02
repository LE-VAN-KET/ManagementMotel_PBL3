package controller.admin.village;

import bean.DistrictModel;
import bean.VillageModel;
import constant.SystemConstant;
import paging.Pageble;
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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/village"})
public class VillageController extends HttpServlet {
    @Inject
    private IVillageService villageService;

    @Inject
    private IDistrictService districtService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        VillageModel villageModel = FormUtil.toModel(VillageModel.class, request);
        /*get district*/
        getDistrictFromRequest(request, villageModel);
        Map<String, String> errors = villageService.validateVillage(villageModel);
        if (errors.size() != 0) {
            request.setAttribute("errors_add", errors);
            doGet(request, response);
        } else {
            villageService.insert(villageModel);
            response.sendRedirect("/admin/village");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        set list districts id = districtId value districtName
        Pageble pageble = new Pageble();
        if (request.getParameter("page") == null) {
            pageble.setPage(1);
        } else {
            pageble.setPage(Integer.parseInt(request.getParameter("page")));
        }
        pageble.setTotalItem(villageService.getTotalItem());
        pageble.setMaxPageItem(SystemConstant.MAXPAGEITEM);

        List<DistrictModel> districtModels = districtService.selectAll();
        List<VillageModel> villageModels = villageService.selectAll(pageble);

        request.setAttribute("pageble", pageble);
        request.setAttribute("districts", districtModels);
        request.setAttribute("villages", villageModels);
        request.getRequestDispatcher("../views/admin/Village.jsp").forward(request, response);
    }

    private void getDistrictFromRequest(HttpServletRequest request, VillageModel villageModel) {
        Long districtId = Long.parseLong(request.getParameter("districtId"));
        DistrictModel districtModel = new DistrictModel();
        districtModel.setDistrictId(districtId);
        villageModel.setDistrictModel(districtModel);
    }
}
