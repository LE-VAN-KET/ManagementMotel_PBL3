package controller.admin.district;

import bean.DistrictModel;
import constant.SystemConstant;
import paging.Pageble;
import service.IDistrictService;
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

@WebServlet(urlPatterns = {"/admin/district"})
public class DistrictController extends HttpServlet {
    @Inject
    private IDistrictService districtService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        DistrictModel districtModel = FormUtil.toModel(DistrictModel.class, request);
        Map<String, String> errors = districtService.validateDistrict(districtModel);
        if (errors.size() != 0) {
            request.setAttribute("errors_add", errors);
            doGet(request, response);
        } else {
            districtService.insert(districtModel);
            response.sendRedirect("/admin/district");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pageble pageble = new Pageble();
        if (request.getParameter("page") == null) {
            pageble.setPage(1);
        } else {
            pageble.setPage(Integer.parseInt(request.getParameter("page")));
        }
        pageble.setTotalItem(districtService.getTotalItem());
        pageble.setMaxPageItem(SystemConstant.MAXPAGEITEM);

        List<DistrictModel> districtModels = districtService.selectAll(pageble);
        request.setAttribute("pageble", pageble);
        request.setAttribute("districts", districtModels);
        request.getRequestDispatcher("../views/admin/District.jsp").forward(request, response);
    }

}
