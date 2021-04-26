package service.implement;

import bean.DistrictModel;
import dao.IDistrictDAO;
import service.IDistrictService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
public class DistrictService implements IDistrictService {
    @Inject
    private IDistrictDAO districtDAO;

    @Override
    public List<DistrictModel> selectAll() {
        return districtDAO.selectAll();
    }

    @Override
    public Long insert(DistrictModel districtModel) {
        return districtDAO.insert(districtModel);
    }

    @Override
    public void update(DistrictModel districtModel) {
        districtDAO.update(districtModel);
    }

    @Override
    public void delete(DistrictModel districtModel) {
        districtDAO.delete(districtModel);
    }
}
