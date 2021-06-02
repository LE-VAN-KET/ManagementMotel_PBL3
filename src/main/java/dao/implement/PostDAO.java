package dao.implement;

import bean.PostModel;
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
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM post";
        return count(sql);
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
    public void updateByPostId(PostModel postModel) {
        StringBuilder sql = new StringBuilder("update post set title = ?, description = ?, price = ?, square = ?,");
        sql.append(" address = ?, villageId = ?, postSlug = ? where postId = ?");
        update(sql.toString(), postModel.getTitle(), postModel.getDescription(), postModel.getPrice(),
                postModel.getSquare(), postModel.getAddress(), postModel.getVillageModel().getVillageId(),
                postModel.getPostSlug(), postModel.getPostId());
    }

    public static void main(String[] args) {
        /*Pageble pageble = new Pageble();
        pageble.setMaxPageItem(2);
        pageble.setPage(1);*/
        /*List<PostModel> postModels = new PostDAO().selectAll(pageble);
        postModels.forEach(System.out::println);*/

    }
}
    