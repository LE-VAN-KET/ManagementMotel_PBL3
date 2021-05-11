package dao;

import bean.DistrictModel;

import java.util.List;

public interface IDistrictDAO {
    List<DistrictModel> selectAll();
}
