package dao.implement;

import bean.DistrictModel;
import dao.IDistrictDAO;
import mapper.implement.DistrictMapper;
import paging.Pageble;

import java.util.List;

public class DistrictDAO extends AbstractDAO<DistrictModel> implements IDistrictDAO {

    public static void main(String[] args) {
        System.out.println(new DistrictDAO().findOne(100L).size());
    }

    @Override
    public List<DistrictModel> selectAll() {
        String sql = "SELECT * FROM district";
        return query(sql, new DistrictMapper());
    }

    public List<DistrictModel> selectAll(Pageble pageble) {
        String sql = "SELECT * FROM district LIMIT ?,?";
        return query(sql, new DistrictMapper(),pageble.getOffset(),pageble.getMaxPageItem());
    }

    @Override
    public List<DistrictModel> findOne(Long districtId) {
        String sql = "SELECT * FROM district WHERE districtId = ? ";
        return query(sql, new DistrictMapper(), districtId);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM district";
        return count(sql);
    }

    @Override
    public Long insert(DistrictModel districtModel) {
        String sql = "INSERT INTO district(districtName) VALUES(?)";
        return insert(sql, districtModel.getDistrictName());
    }

    @Override
    public void update(DistrictModel districtModel) {
        String sql = "UPDATE district SET districtName = ? WHERE districtId = ?";
        update(sql, districtModel.getDistrictName(), districtModel.getDistrictId());
    }

    @Override
    public void delete(DistrictModel districtModel) {
        String sql = "DELETE FROM district WHERE districtId = ?";
        delete(sql, districtModel.getDistrictId());
    }
}
