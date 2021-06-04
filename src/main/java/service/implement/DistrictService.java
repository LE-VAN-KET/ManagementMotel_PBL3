package service.implement;

import bean.DistrictModel;
import bean.VillageModel;
import dao.IDistrictDAO;
import dao.IVillageDAO;
import paging.Pageble;
import service.IDistrictService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
public class DistrictService implements IDistrictService {
    @Inject
    private IDistrictDAO districtDAO;
    @Inject
    private IVillageDAO villageDAO;

    @Override
    public List<DistrictModel> selectAll() {
        return districtDAO.selectAll();
    }

    @Override
    public List<DistrictModel> findOne(Long districtId) {
        return districtDAO.findOne(districtId);
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

    @Override
    public Map<String, String> validateDistrict(DistrictModel districtModel) {
        Map<String, String> errors = new HashMap<>();
        if (districtModel.getDistrictName() == null
                || "".equals(districtModel.getDistrictName()))
            errors.put("districtName_error", "Tên quận không được bỏ trống");
        return errors;
    }

    @Override
    public List<DistrictModel> selectViewAll() {
        List<DistrictModel> listDistrict = selectAll();
        List<VillageModel> listVillage = villageDAO.selectAll();
        for (DistrictModel district: listDistrict) {
            district.setListVillage(new ArrayList<VillageModel>());
            for (VillageModel village: listVillage) {
                if (district.getDistrictId() == village.getDistrictModel().getDistrictId()) {
                    district.getListVillage().add(village);
                }
            }
        }
        return listDistrict;
    }

    @Override
    public int getTotalItem(String searchText) {
        return districtDAO.getTotalItem(searchText);
    }

    public List<DistrictModel> selectAll(String searchText, Pageble pageble) {
        return districtDAO.selectAll(searchText, pageble);
    }
}
