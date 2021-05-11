package dao.implement;

import bean.VillageModel;
import dao.IVillageDAO;
import mapper.implement.VillageMapper;

import java.util.List;

public class VillageDAO extends AbstractDAO<VillageModel> implements IVillageDAO {
    @Override
    public List<VillageModel> selectAll() {
        String sql = "SELECT * FROM village INNER JOIN district ON village.districtId = district.districtId";
        return query(sql,new VillageMapper());
    }
}
