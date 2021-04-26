package dao.implement;

import bean.DistrictModel;
import dao.IDistrictDAO;
import mapper.implement.DistrictMapper;

import java.util.List;

public class DistrictDAO extends AbstractDAO<DistrictModel> implements IDistrictDAO {

    @Override
    public List<DistrictModel> selectAll() {
        String sql = "SELECT * FROM district";
        return query(sql, new DistrictMapper());
    }

    @Override
    public Long insert(DistrictModel districtModel) {
        String sql = "INSERT INTO district(districtName) VALUES(?)";
        return insert(sql,districtModel.getDistrictName());
    }

    @Override
    public void update(DistrictModel districtModel) {
        String sql = "UPDATE district SET districtName = ? WHERE districtId = ?";
        update(sql,districtModel.getDistrictName(),districtModel.getDistrictId());
    }

    @Override
    public void delete(DistrictModel districtModel) {
        String sql = "DELETE district FROM districtId = ?";
        delete(sql, districtModel.getDistrictId());
    }
}
