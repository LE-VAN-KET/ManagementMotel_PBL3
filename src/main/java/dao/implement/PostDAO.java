package dao.implement;

import bean.PostModel;
import dao.IPostDAO;
import mapper.implement.PostMapper;

import java.sql.Timestamp;
import java.util.List;

public class PostDAO extends AbstractDAO<PostModel> implements IPostDAO {
    @Override
    public Long insert(PostModel postModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO post(title, description, linkImages, price, square, ");
        sql.append("address, villageId, userId, statusPost, statusRental) VALUES(?, ?, ?, ?, ?, ?, ? , ?, ?, ?)");
        return  insert(sql.toString(), postModel.getTitle(), postModel.getDescription(), postModel.getLinkImages(),
                postModel.getPrice(), postModel.getSquare(), postModel.getAddress(),
                postModel.getVillageModel().getVillageId(), postModel.getUserModel().getUserId(),
                postModel.getStatusPost(), postModel.getStatusRental());
    }

    @Override
    public void updateLinkImages(Long postId, String linkImages) {
        String sql = "update post set linkImages = ? where postId = ?";
        update(sql, linkImages, postId);
    }

    @Override
    public List<PostModel> selectAll() {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId");
        return query(sql.toString(), new PostMapper());
    }
}
