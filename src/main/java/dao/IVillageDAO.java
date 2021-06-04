package dao;

import bean.VillageModel;
import paging.Pageble;

import java.util.List;

public interface IVillageDAO {
    List<VillageModel> selectAll();
    List<VillageModel> selectAll(String searchText, Pageble pageble);
    List<VillageModel> findOne(Long villageId);
    Long insert(VillageModel villageModel);
    void update(VillageModel villageModel);
    void delete(VillageModel villageModel);
    int getTotalItem(String searchText);
    VillageModel findOneByvillageId(Long villageId);
}
