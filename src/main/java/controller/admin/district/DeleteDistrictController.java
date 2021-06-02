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

@WebServlet(urlPatterns = {"/admin/delete-district"})
public class DeleteDistrictController extends HttpServlet {
    @Inject
    private IDistrictService districtService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        DistrictModel districtModel = FormUtil.toModel(DistrictModel.class, request);
        districtService.delete(districtModel);
        response.sendRedirect("/admin/district");
    }
}
