package dao;

import bean.PostModel;

import java.util.List;

public interface IPostDAO {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll();
    List<PostModel> findByVillageId(Long villageId);
    List<PostModel> findByVillageIdAndExpressionPrice(Long villageId, String price);
    List<PostModel> findByVillageIdAndPrice(Long villageId, Double priceBefore, Double priceAfter);
    List<PostModel> findByVillageIdAndExpressionSquare(Long villageId, String square);
    List<PostModel> findByVillageIdAndSquare(Long villageId, Long squareBefore, Long squareAfter);
    List<PostModel> findByExpressionPrice_ExpressionSquare(String square, String price);
    List<PostModel> findByPriceAndSquare(Double priceBefore, Double priceAfter, Long squareBefore, Long squareAfter);
    List<PostModel> findByPrice_ExpressionSquare(Double priceBefore, Double priceAfter, String square);
    List<PostModel> findByExpressionPrice_Square(String price, Long squareBefore, Long squareAfter);
    List<PostModel> findByExpressionPrice(String price);
    List<PostModel> findByPrice(Double priceBefore, Double priceAfter);
    List<PostModel> findByExpressionSquare(String square);
    List<PostModel> findBySquare(Long squareBefore, Long squareAfter);
}
