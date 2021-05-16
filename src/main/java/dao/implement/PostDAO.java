package dao.implement;

import bean.PostModel;
import dao.IPostDAO;
import mapper.implement.PostMapper;

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

    @Override
    public List<PostModel> findByVillageId(Long villageId) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where post.villageId = ?");
        return query(sql.toString(), new PostMapper(), villageId);
    }

    @Override
    public List<PostModel> findByVillageIdAndExpressionPrice(Long villageId, String price) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where post.villageId = ? and price " + price);
        return query(sql.toString(), new PostMapper(), villageId);
    }

    @Override
    public List<PostModel> findByVillageIdAndPrice(Long villageId, Double priceBefore, Double priceAfter) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where post.villageId = ? and price BETWEEN ? AND ?");
        return query(sql.toString(), new PostMapper(), villageId, priceBefore, priceAfter);
    }

    @Override
    public List<PostModel> findByVillageIdAndExpressionSquare(Long villageId, String square) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where post.villageId = ? and square " + square);
        return query(sql.toString(), new PostMapper(), villageId);
    }

    @Override
    public List<PostModel> findByVillageIdAndSquare(Long villageId, Long squareBefore, Long squareAfter) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where post.villageId = ? and (price BETWEEN ? AND ?)");
        return query(sql.toString(), new PostMapper(), villageId, squareBefore, squareAfter);
    }

    @Override
    public List<PostModel> findByExpressionPrice_ExpressionSquare(String square, String price) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where square " + square + " and price " + price);
        return query(sql.toString(), new PostMapper());
    }

    @Override
    public List<PostModel> findByPriceAndSquare(Double priceBefore, Double priceAfter, Long squareBefore, Long squareAfter) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where (price BETWEEN ? AND ?) and (square BETWEEN ? AND ?)");
        return query(sql.toString(), new PostMapper(), priceBefore, priceAfter, squareBefore, squareAfter);
    }

    @Override
    public List<PostModel> findByPrice_ExpressionSquare(Double priceBefore, Double priceAfter, String square) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where (price BETWEEN ? AND ?) and square" + square);
        return query(sql.toString(), new PostMapper(), priceBefore, priceAfter);
    }

    @Override
    public List<PostModel> findByExpressionPrice_Square(String price, Long squareBefore, Long squareAfter) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where (square BETWEEN ? AND ?) and price" + price);
        return query(sql.toString(), new PostMapper(), squareBefore, squareAfter);
    }

    @Override
    public List<PostModel> findByExpressionPrice(String price) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where price " + price);
        return query(sql.toString(), new PostMapper());
    }

    @Override
    public List<PostModel> findByPrice(Double priceBefore, Double priceAfter) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where price BETWEEN ? AND ?");
        return query(sql.toString(), new PostMapper(), priceBefore, priceAfter);
    }

    @Override
    public List<PostModel> findByExpressionSquare(String square) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where square " + square);
        return query(sql.toString(), new PostMapper());
    }

    @Override
    public List<PostModel> findBySquare(Long squareBefore, Long squareAfter) {
        StringBuilder sql = new StringBuilder("select * from post INNER JOIN users ON post.userId = users.userId ");
        sql.append("INNER JOIN role ON users.roleId = role.roleId INNER JOIN village ON post.villageId = ");
        sql.append("village.villageId INNER JOIN district ON village.districtId = district.districtId ");
        sql.append("where square BETWEEN ? AND ?");
        return query(sql.toString(), new PostMapper(), squareBefore, squareAfter);
    }
}
