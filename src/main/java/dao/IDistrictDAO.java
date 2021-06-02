package dao;

import bean.DistrictModel;
import paging.Pageble;

import java.util.List;

public interface IDistrictDAO {
    List<DistrictModel> selectAll();
    Long insert(DistrictModel districtModel);
    void update(DistrictModel districtModel);
    void delete(DistrictModel districtModel);
    List<DistrictModel> findOne(Long districtId);
    int getTotalItem();
    List<DistrictModel> selectAll(Pageble pageble);
}
