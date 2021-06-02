package mapper.implement;

import bean.AccountModel;
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
            postModel.setPostId(resultSet.getLong("postId"));
            postModel.setTitle(resultSet.getString("title"));
            postModel.setDescription(resultSet.getString("description"));
            postModel.setLinkImages(resultSet.getString("linkImages"));
            postModel.setPrice(resultSet.getDouble("price"));
            postModel.setSquare(resultSet.getLong("square"));
            postModel.setAddress(resultSet.getString("address"));
            VillageModel villageModel = new VillageMapper().mapRow(resultSet);
            postModel.setVillageModel(villageModel);
            UserModel userModel = new UserMapper().mapRow(resultSet);
            postModel.setUserModel(userModel);
            postModel.setStatusPost(resultSet.getBoolean("statusPost"));
            postModel.setStatusRental(resultSet.getBoolean("statusRental"));
            postModel.setCreateAt(resultSet.getTimestamp("createAt"));
            postModel.setPublishedAt(resultSet.getTimestamp("publishedAt"));
            postModel.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
            postModel.setPostSlug(resultSet.getString("postSlug"));
            return postModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
