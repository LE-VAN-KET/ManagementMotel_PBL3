package dao;

import bean.VillageModel;

import java.util.List;

public interface IVillageDAO {
    List<VillageModel> selectAll();
    VillageModel findOneByvillageId(Long villageId);
}
