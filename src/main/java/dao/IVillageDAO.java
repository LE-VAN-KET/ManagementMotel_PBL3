package dao;

import bean.VillageModel;

import java.util.List;

public interface IVillageDAO {
    List<VillageModel> selectAll();
    Long insert(VillageModel villageModel);
    void update(VillageModel villageModel);
    void delete(VillageModel villageModel);
}
