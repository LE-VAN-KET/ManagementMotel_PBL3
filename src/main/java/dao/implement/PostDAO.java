package dao.implement;

import bean.PostModel;
import criteria.Criteria;
import dao.IPostDAO;
import mapper.implement.PostMapper;
import paging.Pageble;

import java.util.List;

public class PostDAO extends AbstractDAO<PostModel> implements IPostDAO {

    private StringBuilder sqlQuery() {
        StringBuilder _sqlQuery = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        _sqlQuery.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        _sqlQuery.append("village.villageId INNER JOIN district ON village.districtId = district.districtId");
        return _sqlQuery;
    }

    @Override
    public Long insert(PostModel postModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO post(title, description, linkImages, price, square, ");
        sql.append("address, villageId, userId, statusPost, statusRental, postSlug) VALUES(?, ?, ?, ?, ?, ?, ");
        sql.append("? , ?, ?, ?, ?)");
        return  insert(sql.toString(), postModel.getTitle(), postModel.getDescription(), postModel.getLinkImages(),
                postModel.getPrice(), postModel.getSquare(), postModel.getAddress(),
                postModel.getVillageModel().getVillageId(), postModel.getUserModel().getUserId(),
                postModel.getStatusPost(), postModel.getStatusRental(), postModel.getPostSlug());
    }

    @Override
    public void updateLinkImages(Long postId, String linkImages) {
        String sql = "update post set linkImages = ? where postId = ?";
        update(sql, linkImages, postId);
    }

    @Override
    public List<PostModel> selectAll(Pageble pageble) {
        StringBuilder sql = sqlQuery();
        sql.append(" LIMIT ?, ?");
        return query(sql.toString(), new PostMapper(), pageble.getOffset(), pageble.getMaxPageItem());
    }

    @Override
    public List<PostModel> findByVillageId(Long villageId, Pageble pageble) {
        StringBuilder sql = sqlQuery();
        sql.append(" where post.villageId = ? LIMIT ?, ?");
        return query(sql.toString(), new PostMapper(),villageId, pageble.getOffset(), pageble.getMaxPageItem());
    }

    private StringBuilder clauseWhereFindByCriteria(Criteria criteria, StringBuilder sql) {
        sql.append(" where 1=1");
        if (criteria.getVillageId() != 0) {
            sql.append(" and post.villageId = " + criteria.getVillageId());
        }
        if (criteria.getPrice_from() != null) {
            sql.append(" and price >= " + criteria.getPrice_from());
        }
        if (criteria.getPrice_to() != null) {
            sql.append(" and price <= " + criteria.getPrice_to());
        }
        if (criteria.getSquare_from() != null) {
            sql.append(" and square >= " + criteria.getSquare_from());
        }
        if (criteria.getSquare_to() != null) {
            sql.append(" and square <= " + criteria.getSquare_to());
        }
        return sql;
    }

    @Override
    public List<PostModel> findByCriteria(Criteria criteria, Pageble pageble) {
        StringBuilder sql = sqlQuery();
        sql = clauseWhereFindByCriteria(criteria, sql);
        if ((pageble.getSorter().getSort_by() != null) && (pageble.getSorter().getOrder_by() != null)) {
            sql.append( " ORDER BY " + pageble.getSorter().getSort_by());
            sql.append(" " + pageble.getSorter().getOrder_by());
        }
        sql.append(" LIMIT ?, ?");
        return query(sql.toString(), new PostMapper(), pageble.getOffset(), pageble.getMaxPageItem());
    }


    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM post";
        return count(sql);
    }

    @Override
    public int getTotalItemByCriteria(Criteria criteria) {
        StringBuilder query = new StringBuilder(" select count(*) from post");
        query = clauseWhereFindByCriteria(criteria, query);
        return count(query.toString());
    }

    @Override
    public PostModel findOneByPostSlug(String postSLug) {
        StringBuilder sql = sqlQuery();
        sql.append(" where postSlug = ? LIMIT 1");
        List<PostModel> postModels = query(sql.toString(), new PostMapper(), postSLug);
        return postModels.isEmpty() ? null : postModels.get(0);
    }
}
