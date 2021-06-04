package service;

import bean.VillageModel;
import paging.Pageble;

import java.util.List;
import java.util.Map;

public interface IVillageService {
    List<VillageModel> selectAll();
    List<VillageModel> selectAll(String searchText, Pageble pageble);
    List<VillageModel> findOne(Long villageId);
    Long insert(VillageModel villageModel);
    void update(VillageModel villageModel);
    void delete(VillageModel villageModel);
    Map<String, String> validateVillage(VillageModel villageModel);
    int getTotalItem(String searchText);
    VillageModel findOneByvillageId(Long villageId);
}
