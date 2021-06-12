package mapper.implement;

import bean.UserModel;
import bean.VillageModel;
import mapper.IRowMapper;
import bean.PostModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements IRowMapper<PostModel> {
    @Override
    public PostModel mapRow(ResultSet resultSet) {
        PostModel postModel = new PostModel();
        try {
            postModel.setPostId(resultSet.getLong("post.postId"));
            postModel.setTitle(resultSet.getString("post.title"));
            postModel.setDescription(resultSet.getString("post.description"));
            postModel.setLinkImages(resultSet.getString("post.linkImages"));
            postModel.setPrice(resultSet.getDouble("post.price"));
            postModel.setSquare(resultSet.getLong("post.square"));
            postModel.setAddress(resultSet.getString("post.address"));

            VillageModel villageModel = new VillageMapper().mapRow(resultSet);
            postModel.setVillageModel(villageModel);
            UserModel userModel = new UserMapper().mapRow(resultSet);
            postModel.setUserModel(userModel);

            postModel.setPostSlug(resultSet.getString("post.postSlug"));
            postModel.setStatusPost(resultSet.getBoolean("post.statusPost"));
            postModel.setStatusRental(resultSet.getBoolean("post.statusRental"));
            postModel.setCreateAt(resultSet.getTimestamp("post.createAt"));
            postModel.setPublishedAt(resultSet.getTimestamp("post.publishedAt"));
            postModel.setModifiedAt(resultSet.getTimestamp("post.modifiedAt"));
            return postModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
