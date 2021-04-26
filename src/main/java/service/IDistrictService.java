package service;

import bean.DistrictModel;

import java.util.List;

public interface IDistrictService {
    List<DistrictModel> selectAll();
    Long insert(DistrictModel districtModel);
    void update(DistrictModel districtModel);
    void delete(DistrictModel districtModel);
}
