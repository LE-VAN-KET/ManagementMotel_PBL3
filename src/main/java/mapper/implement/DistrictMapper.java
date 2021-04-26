package mapper.implement;

import bean.UserModel;
import mapper.IRowMapper;
import bean.DistrictModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistrictMapper implements IRowMapper<DistrictModel> {
    @Override
    public DistrictModel mapRow(ResultSet resultSet) {
        try {
            DistrictModel districtModel = new DistrictModel();
            districtModel.setDistrictId(resultSet.getLong("districtId"));
            districtModel.setDistrictName(resultSet.getString("districtName"));
            districtModel.setCreateAt(resultSet.getTimestamp("createAt"));
            districtModel.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
            return districtModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
