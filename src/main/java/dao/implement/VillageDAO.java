package dao.implement;

import bean.VillageModel;
import dao.IVillageDAO;
import mapper.implement.VillageMapper;

import java.util.List;

public class VillageDAO extends AbstractDAO<VillageModel> implements IVillageDAO {

    @Override
    public List<VillageModel> selectAll() {
        String sql = "SELECT * FROM Village";
        return query(sql,new VillageMapper());
    }

    @Override
    public Long insert(VillageModel villageModel) {
        String sql = "INSERT INTO village(villageName, districtId) VALUES(?, ?)";
        return insert(sql,villageModel.getVillageName(),villageModel.getDistrictId());
    }

    @Override
    public void update(VillageModel villageModel) {
        String sql = "UPDATE village SET villageName = ?, districtId = ? WHERE villageId = ?";
        update(sql,villageModel.getVillageName(),villageModel.getDistrictId(),villageModel.getVillageId());
    }

    @Override
    public void delete(VillageModel villageModel) {
        String sql = "DELETE FROM village WHERE villageId = ?";
        delete(sql,villageModel.getVillageId());
    }

}
