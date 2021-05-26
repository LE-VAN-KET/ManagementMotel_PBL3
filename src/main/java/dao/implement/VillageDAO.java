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

    @Override
    public VillageModel findOneByvillageId(Long villageId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM village INNER JOIN district ON village.districtId");
        sql.append(" = district.districtId WHERE villageId = ? LIMIT 1");
        List<VillageModel> villageModels = query(sql.toString(), new VillageMapper(), villageId);
        return villageModels.isEmpty() ? null: villageModels.get(0);
    }
}
