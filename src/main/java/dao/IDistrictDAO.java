package dao;

import bean.DistrictModel;
import java.util.List;

public interface IDistrictDAO {
    List<DistrictModel> selectAll();
    Long insert(DistrictModel districtModel);
    void update(DistrictModel districtModel);
    void delete(DistrictModel districtModel);
}
