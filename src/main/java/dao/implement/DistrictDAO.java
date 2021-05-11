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
}
