package dao.implement;

import bean.VillageModel;
import dao.IVillageDAO;
import mapper.implement.VillageMapper;
import paging.Pageble;

import java.util.List;

public class VillageDAO extends AbstractDAO<VillageModel> implements IVillageDAO {

    @Override
    public List<VillageModel> selectAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM village");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        sql.append(" LIMIT ?,?");
        return query(sql.toString(),new VillageMapper(), pageble.getOffset(), pageble.getMaxPageItem());
    }

    @Override
    public List<VillageModel> selectAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM village");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        return query(sql.toString(),new VillageMapper());
    }

    @Override
    public List<VillageModel> findOne(Long villageId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM village");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        sql.append(" WHERE villageId = ?");
        return query(sql.toString(),new VillageMapper(),villageId);
    }

    @Override
    public Long insert(VillageModel villageModel) {
        String sql = "INSERT INTO village(villageName, districtId) VALUES(?, ?)";
        return insert(sql,villageModel.getVillageName(),villageModel.getDistrictModel().getDistrictId());
    }

    @Override
    public void update(VillageModel villageModel) {
        String sql = "UPDATE village SET villageName = ?, districtId = ? WHERE villageId = ?";
        update(sql,villageModel.getVillageName(),villageModel.getDistrictModel().getDistrictId(),villageModel.getVillageId());
    }

    @Override
    public void delete(VillageModel villageModel) {
        String sql = "DELETE FROM village WHERE villageId = ?";
        delete(sql,villageModel.getVillageId());
    }

    public int getTotalItem() {
        String sql = "SELECT count(*) FROM village";
        return count(sql);
    }

    public static void main(String[] args) {
        Pageble pageble = new Pageble();
        pageble.setMaxPageItem(2);
        pageble.setPage(3);
        VillageDAO villageDAO = new VillageDAO();
        List<VillageModel> villageModels = villageDAO.selectAll(pageble);
        villageModels.forEach(System.out::println);
    }
}
