package dao.implement;

import bean.VillageModel;
import dao.IVillageDAO;
import mapper.implement.VillageMapper;
import paging.Pageble;

import java.util.List;

public class VillageDAO extends AbstractDAO<VillageModel> implements IVillageDAO {

    private StringBuilder sqlQuery() {
        StringBuilder sql = new StringBuilder("SELECT * FROM village");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        return sql;
    }
    @Override
    public List<VillageModel> selectAll(String searchText, Pageble pageble) {
        StringBuilder sql = sqlQuery();
        if(searchText != null && searchText != "") {
            sql.append(" where villageName like N? or districtName like N?");
            sql.append(" LIMIT ?, ?");
            return query(sql.toString(), new VillageMapper(),
                    "%" + searchText + "%", "%" + searchText + "%",
                    pageble.getOffset(), pageble.getMaxPageItem());
        }
        sql.append(" LIMIT ?, ?");
        return query(sql.toString(), new VillageMapper(), pageble.getOffset(), pageble.getMaxPageItem());
    }

    @Override
    public List<VillageModel> selectAll() {
        StringBuilder sql = sqlQuery();
        return query(sql.toString(),new VillageMapper());
    }

    @Override
    public List<VillageModel> findOne(Long villageId) {
        StringBuilder sql = sqlQuery();
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

    public int getTotalItem(String searchText) {
        StringBuilder sql = new StringBuilder("SELECT count(*) FROM village");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        if(searchText != null && searchText != "") {
            sql.append(" where villageName like N? or districtName like N?");
            return count(sql.toString(), "%" + searchText + "%",
                    "%" + searchText + "%");
        }
        return count(sql.toString());
    }

    public static void main(String[] args) {
        Pageble pageble = new Pageble();
        pageble.setMaxPageItem(10);
        pageble.setPage(1);
        VillageDAO villageDAO = new VillageDAO();
        List<VillageModel> villageModels = villageDAO.selectAll("", pageble);
        villageModels.forEach(System.out::println);

        System.out.println(new VillageDAO().getTotalItem(""));
    }

    @Override
    public VillageModel findOneByvillageId(Long villageId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM village INNER JOIN district ON village.districtId");
        sql.append(" = district.districtId WHERE villageId = ? LIMIT 1");
        List<VillageModel> villageModels = query(sql.toString(), new VillageMapper(), villageId);
        return villageModels.isEmpty() ? null: villageModels.get(0);
    }
}
