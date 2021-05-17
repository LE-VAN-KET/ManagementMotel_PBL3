package dao;

import bean.PostModel;
import paging.Pageble;

import java.util.List;

public interface IPostDAO {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    List<PostModel> findByVillageId(Long villageId, Pageble pageble);
    List<PostModel> findByVillageIdAndExpressionPrice(Long villageId, String price, Pageble pageble);
    List<PostModel> findByVillageIdAndPrice(Long villageId, Double priceBefore, Double priceAfter, Pageble pageble);
    List<PostModel> findByVillageIdAndExpressionSquare(Long villageId, String square, Pageble pageble);
    List<PostModel> findByVillageIdAndSquare(Long villageId, Long squareBefore, Long squareAfter, Pageble pageble);
    List<PostModel> findByExpressionPrice_ExpressionSquare(String square, String price, Pageble pageble);
    List<PostModel> findByPriceAndSquare(Double priceBefore, Double priceAfter, Long squareBefore, Long squareAfter,
                                         Pageble pageble);
    List<PostModel> findByPrice_ExpressionSquare(Double priceBefore, Double priceAfter, String square, Pageble pageble);
    List<PostModel> findByExpressionPrice_Square(String price, Long squareBefore, Long squareAfter, Pageble pageble);
    List<PostModel> findByExpressionPrice(String price, Pageble pageble);
    List<PostModel> findByPrice(Double priceBefore, Double priceAfter, Pageble pageble);
    List<PostModel> findByExpressionSquare(String square, Pageble pageble);
    List<PostModel> findBySquare(Long squareBefore, Long squareAfter, Pageble pageble);

    int getTotalItem();
}
