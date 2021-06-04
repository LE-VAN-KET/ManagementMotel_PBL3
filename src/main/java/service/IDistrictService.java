package service;

import bean.DistrictModel;
import paging.Pageble;

import java.util.List;
import java.util.Map;

public interface IDistrictService {
    List<DistrictModel> selectAll();
    List<DistrictModel> findOne(Long districtId);
    Long insert(DistrictModel districtModel);
    void update(DistrictModel districtModel);
    void delete(DistrictModel districtModel);
    Map<String, String> validateDistrict(DistrictModel districtModel);
    List<DistrictModel> selectViewAll();
    int getTotalItem(String searchText);
    List<DistrictModel> selectAll(String searchText, Pageble pageble);
}
