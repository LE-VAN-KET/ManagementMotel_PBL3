package controller.admin.district;

import bean.DistrictModel;
import service.IDistrictService;
import utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/edit-district"})
public class EditDistrictController extends HttpServlet {
    @Inject
    private IDistrictService districtService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        DistrictModel districtModel = FormUtil.toModel(DistrictModel.class, request);
        Map<String, String> errors = districtService.validateDistrict(districtModel);
        if (errors.size() != 0) {
            request.setAttribute("errors_edit", errors);
            request.setAttribute("district_id",districtModel.getDistrictId());
            request.getRequestDispatcher("/admin/district").forward(request, response);
        } else {
            districtService.update(districtModel);
            response.sendRedirect("/admin/district");
        }
    }
}
