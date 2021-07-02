package dao.implement;

import bean.PostModel;
import criteria.Criteria;
import dao.IPostDAO;
import mapper.implement.PostMapper;
import paging.Pageble;

import java.util.List;

public class PostDAO extends AbstractDAO<PostModel> implements IPostDAO {

    private static PostDAO instance;
    public static PostDAO getInstance() {
        if (instance == null) {
            instance = new PostDAO();
        }
        return instance;
    }

    private StringBuilder sqlQuery() {
        StringBuilder sql = new StringBuilder("SELECT * FROM post");
        sql.append(" INNER JOIN users ON post.userId = users.userId ");
        sql.append(" INNER JOIN role ON users.roleId = role.roleId");
        sql.append(" INNER JOIN village ON post.villageId = village.villageId");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        return sql;
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
    public List<PostModel> selectAllByStatusPost(Pageble pageble, boolean statusPost) {
        StringBuilder sql = sqlQuery();
        sql.append(" where statusPost = ? LIMIT ?, ?");
        return query(sql.toString(), new PostMapper(), statusPost, pageble.getOffset(), pageble.getMaxPageItem());
    }

    @Override
    public List<PostModel> findByVillageId(Long villageId, Pageble pageble) {
        StringBuilder sql = sqlQuery();
        sql.append(" where post.villageId = ? LIMIT ?, ?");
        return query(sql.toString(), new PostMapper(),villageId, pageble.getOffset(), pageble.getMaxPageItem());
    }

    private StringBuilder clauseWhereFindByCriteria(Criteria criteria, StringBuilder sql) {
        sql.append(" where statusPost = 1");
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
    public int getTotalItem(String searchText) {
        StringBuilder sql = new StringBuilder("SELECT count(*) FROM post");
        sql.append(" INNER JOIN users ON post.userId = users.userId ");
        sql.append(" INNER JOIN role ON users.roleId = role.roleId");
        sql.append(" INNER JOIN village ON post.villageId = village.villageId");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        if(searchText != null && searchText != "") {
            sql.append(" where villageName like N?");
            return count(sql.toString(), "%" + searchText + "%");
        }
        return count(sql.toString());
    }

    @Override
    public int getTotalItemByStatusPost(boolean statusPost) {
        String sql = "SELECT count(*) FROM post where statusPost = ?";
        return count(sql, statusPost);
    }

    @Override
    public int getTotalIemByUserId(Long userId) {
        String sql = "SELECT count(*) FROM post where post.userId = ?";
        return count(sql, userId);
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

    @Override
    public PostModel findByPostId(Long postId) {
        StringBuilder sql = sqlQuery();
        sql.append(" where postId = ? LIMIT 1");
        List<PostModel> postModels = query(sql.toString(), new PostMapper(), postId);
        return postModels.isEmpty() ? null : postModels.get(0);
    }

    @Override
    public List<PostModel> findByUserId(Pageble pageble, Long userId) {
        StringBuilder sql = sqlQuery();
        sql.append(" where post.userId = ? LIMIT ?, ?");
        return query(sql.toString(), new PostMapper(), userId, pageble.getOffset(), pageble.getMaxPageItem());
    }

    @Override
    public void deleteByPostId(Long postId) {
        String sql = "delete from post where postId = ?";
        delete(sql, postId);
    }

    @Override
    public void updateStatusPostByPostId(Long postId, boolean statusPost) {
        String sql = "update post set statusPost = ? where postId = ?";
        update(sql, statusPost, postId);
    }

    @Override
    public List<PostModel> selectAll(String searchText, Pageble pageble) {
        StringBuilder sql = sqlQuery();
        if(searchText != null && searchText != "") {
            sql.append(" where villageName like N?");
            sql.append(" LIMIT ?, ?");
            return query(sql.toString(),new PostMapper(),"%" + searchText + "%", pageble.getOffset(), pageble.getMaxPageItem());
        }
        sql.append(" LIMIT ?, ?");
        return query(sql.toString(),  new PostMapper(), pageble.getOffset(), pageble.getMaxPageItem());
    }

    @Override
    public void updateByPostId(PostModel postModel) {
        StringBuilder sql = new StringBuilder("update post set title = ?, description = ?, price = ?, square = ?,");
        sql.append(" address = ?, villageId = ?, postSlug = ? where postId = ?");
        update(sql.toString(), postModel.getTitle(), postModel.getDescription(), postModel.getPrice(),
                postModel.getSquare(), postModel.getAddress(), postModel.getVillageModel().getVillageId(),
                postModel.getPostSlug(), postModel.getPostId());
    }

    @Override
    public void updateStatusRentalByPostId(Long postId, boolean statusRental) {
        String sql = "update post set statusRental = ? where postId = ?";
        update(sql, statusRental, postId);
    }

    @Override
    public int countByPostId(Long postId) {
        String sql = "SELECT count(*) FROM post where postId = ?";
        return count(sql, postId);
    }

    public static void main(String[] args) {
        Pageble pageble = new Pageble();
        pageble.setMaxPageItem(10);
        pageble.setPage(1);
        List<PostModel> postModels = new PostDAO().selectAll(null,pageble);
        postModels.forEach(System.out::println);

    }
}
