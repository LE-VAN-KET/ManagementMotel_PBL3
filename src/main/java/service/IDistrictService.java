package service;

import bean.DistrictModel;

import java.util.List;

public interface IDistrictService {
    List<DistrictModel> selectAll();
    List<DistrictModel> selectViewAll();
}
