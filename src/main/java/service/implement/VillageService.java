package service.implement;

import bean.VillageModel;
import dao.IVillageDAO;
import service.IVillageService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
public class VillageService implements IVillageService {
    @Inject
    private IVillageDAO villageDAO;

    @Override
    public List<VillageModel> selectAll() {
        return villageDAO.selectAll();
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

}
