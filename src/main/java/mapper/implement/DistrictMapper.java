package mapper.implement;

import mapper.IRowMapper;
import bean.DistrictModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistrictMapper implements IRowMapper<DistrictModel> {
    @Override
    public DistrictModel mapRow(ResultSet resultSet) {
        try {
            DistrictModel districtModel = new DistrictModel();
            districtModel.setDistrictId(resultSet.getLong("district.districtId"));
            districtModel.setDistrictName(resultSet.getString("district.districtName"));
            districtModel.setCreateAt(resultSet.getTimestamp("district.createAt"));
            districtModel.setModifiedAt(resultSet.getTimestamp("district.modifiedAt"));
            return districtModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
