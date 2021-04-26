package dao.implement;

import bean.PostModel;
import dao.IPostDAO;
import mapper.implement.PostMapper;

import java.util.List;

public class PostDAO extends AbstractDAO<PostModel> implements IPostDAO {

    @Override
    public List<PostModel> selectAll() {
        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append("price = ?, square = ?, address = ?, villageId = ?,");
        sql.append("userId = ?, statusPost = ?, statusRental = ? ");
        sql.append("WHERE postId = ?");
        return query(sql.toString(),new PostMapper());
    }

    @Override
    public Long insert(PostModel postModel) {
        String sql = "INSERT INTO post values(?,?,?,?,?,?,?,?,?,?,?)";
        return insert(sql, postModel.getPostId());
    }

    @Override
    public void update(PostModel postModel) {
        StringBuffer sql = new StringBuffer("UPDATE post SET title = ?, description = ?, linkImages = ?,");
        sql.append("price = ?, square = ?, address = ?, villageId = ?,");
        sql.append("userId = ?, statusPost = ?, statusRental = ? ");
        sql.append("WHERE postId = ?");
        update(sql.toString(),postModel.getTitle(),postModel.getDesciption(), postModel.getLinkImages(),
                postModel.getPrice(),postModel.getSquare(),postModel.getAddress(),
                postModel.getVillageId(), postModel.getAccountModel(),postModel.getStatusPost(),
                postModel.getStatusRental(), postModel.getPostId());
    }
    
    @Override
    public void delete(PostModel postModel) {
        String sql = "DELETE FROM post WHERE postId = ?";
        delete(sql,postModel.getPostId());
    }
}
    