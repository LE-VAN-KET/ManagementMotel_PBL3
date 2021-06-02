package service.implement;

import bean.VillageModel;
import dao.IVillageDAO;
import dao.implement.DistrictDAO;
import paging.Pageble;
import service.IVillageService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
public class VillageService implements IVillageService {
    @Inject
    private IVillageDAO villageDAO;

    @Override
    public List<VillageModel> selectAll() {
        return villageDAO.selectAll();
    }

    @Override
    public List<VillageModel> selectAll(Pageble pageble) {
        return villageDAO.selectAll(pageble);
    }

    @Override
    public List<VillageModel> findOne(Long villageId) {
        return villageDAO.findOne(villageId);
    }

    @Override
    public Long insert(VillageModel villageModel) {
        return villageDAO.insert(villageModel);
    }

    @Override
    public void update(VillageModel villageModel) {
        villageDAO.update(villageModel);
    }

    @Override
    public void delete(VillageModel villageModel) {
        villageDAO.delete(villageModel);
    }

    public Map<String, String> validateVillage(VillageModel villageModel) {
        Map<String, String> errors = new HashMap<>();
        if (villageModel.getVillageName() == null
                || "".equals(villageModel.getVillageName()))
        errors.put("villageName_error", "Tên phường không được bỏ trống");

        if(new DistrictDAO().findOne(villageModel.getDistrictModel().getDistrictId()).size() == 0)
            errors.put("districtName_error","Quận không trùng khớp với dữ liệu");
        return errors;
    }

    @Override
    public int getTotalItem() {
        return villageDAO.getTotalItem();
    }

}
