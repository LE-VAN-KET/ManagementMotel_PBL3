package mapper.implement;

import bean.DistrictModel;
import mapper.IRowMapper;
import bean.VillageModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VillageMapper implements IRowMapper<VillageModel> {
    @Override
    public VillageModel mapRow(ResultSet resultSet) {
        VillageModel villageModel = new VillageModel();
        try {
            villageModel.setVillageId(resultSet.getLong("villageId"));
            villageModel.setVillageName(resultSet.getString("villageName"));
            DistrictModel districtModel = new DistrictMapper().mapRow(resultSet);
            villageModel.setDistrictModel(districtModel);
            villageModel.setCreateAt(resultSet.getTimestamp("createAt"));
            villageModel.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
            return villageModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
