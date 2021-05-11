package service.implement;

import bean.DistrictModel;
import bean.VillageModel;
import dao.IDistrictDAO;
import dao.IVillageDAO;
import service.IDistrictService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
}
