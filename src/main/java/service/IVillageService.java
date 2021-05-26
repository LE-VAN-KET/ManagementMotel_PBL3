package service;

import bean.VillageModel;

import java.util.List;

public interface IVillageService {
    List<VillageModel> selectAll();
    VillageModel findOneByvillageId(Long villageId);
}
