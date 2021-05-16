package service.implement;

import bean.PostModel;
import dao.IPostDAO;
import service.IPostService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
public class PostService implements IPostService {
    @Inject
    private IPostDAO postDAO;

    @Override
    public Long insert(PostModel postModel) {
        Long postId = null;
//        if (isAll_Fields_Empty(postModel.getTitle(), postModel.getDesciption(), postModel.getLinkImages(),
//                postModel.getPrice(), postModel.getSquare(), postModel.getAddress(), postModel.getVillageId(),
//                postModel.getUserId(), postModel.getStatusPost(), postModel.getStatusRental())) {
//            // handle errors
//        } else {
            // callback method postDAO
            postId = postDAO.insert(postModel);
//        }
        return postId;
    }

    private boolean isAll_Fields_Empty(Object... parameters) {
        for (Object parameter: parameters) {
            if ((parameter == null) || ("".equals((String) parameter))) {
                return true;
            }
        }
        return false;
    }

    public void updateLinkImages(Long postId, String linkImages) {
        postDAO.updateLinkImages(postId, linkImages);
    }

    @Override
    public List<PostModel> selectAll() {
        return postDAO.selectAll();
    }

    @Override
    public List<PostModel> findByVillageId(Long villageId) {
        return postDAO.findByVillageId(villageId);
    }

    private boolean isExpression(String str) {
        return (str.startsWith(">") || str.startsWith("<")) ? true: false;
    }

    @Override
    public List<PostModel> findByParameters(String villageId, String square, String price) {
        Long _villageId = Long.parseLong(villageId);
        List<PostModel> postModels = null;
        String[] priceOptions = price.split("-");
        String[] squareOptions = square.split("-");

        switch (validateParamsFind(_villageId, square, price)) {
            case 0:
                // findByVillageId
                postModels = findByVillageId(_villageId);
                break;
            case 1:
//                findByVillageIdAndPrice
                if (isExpression(price)) {
                    postModels = postDAO.findByVillageIdAndExpressionPrice(_villageId, price);
                } else {
                    postModels = postDAO.findByVillageIdAndPrice(_villageId, Double.parseDouble(priceOptions[0]),
                            Double.parseDouble(priceOptions[priceOptions.length - 1]));
                }
                break;
            case 2:
                // findByVillageIdAndSquare
                if (isExpression(square)) {
                    postModels = postDAO.findByVillageIdAndExpressionSquare(_villageId, square);
                } else {
                    postModels = postDAO.findByVillageIdAndSquare(_villageId, Long.parseLong(squareOptions[0]),
                            Long.parseLong(squareOptions[squareOptions.length - 1]));
                }
                break;
            case 3:
                // selectAllPost
                postModels = selectAll();
                break;
            case 4:
                // findBySquareAndPrice
                if (isExpression(square) && isExpression(price)) {
                    postModels = postDAO.findByExpressionPrice_ExpressionSquare(square, price);
                    break;
                }

                if ((!isExpression(square)) && (!isExpression(price))) {
                    postModels = postDAO.findByPriceAndSquare(Double.parseDouble(priceOptions[0]),
                            Double.parseDouble(priceOptions[priceOptions.length - 1]),
                            Long.parseLong(squareOptions[0]),
                            Long.parseLong(squareOptions[squareOptions.length - 1]));
                    break;
                }

                if (isExpression(square)) {
                    // express square
                    postModels = postDAO.findByPrice_ExpressionSquare(Double.parseDouble(priceOptions[0]),
                            Double.parseDouble(priceOptions[priceOptions.length - 1]),
                            square);
                    break;
                }

                postModels = postDAO.findByExpressionPrice_Square(price, Long.parseLong(squareOptions[0]),
                        Long.parseLong(squareOptions[squareOptions.length - 1]));
                break;
            case 5:
                // findByPrice
                if (isExpression(price)) {
                    // express price
                    postModels = postDAO.findByExpressionPrice(price);
                } else {
                    postModels = postDAO.findByPrice(Double.parseDouble(priceOptions[0]),
                            Double.parseDouble(priceOptions[priceOptions.length - 1]));
                }
                break;
            default:
                // findBySquare
                if (isExpression(square)) {
                    // express price
                    postModels = postDAO.findByExpressionSquare(square);
                } else {
                    postModels = postDAO.findBySquare(Long.parseLong(squareOptions[0]),
                            Long.parseLong(squareOptions[squareOptions.length - 1]));
                }
                break;
        }
        return postModels;
    }

    private int validateParamsFind(Long villageId, String square, String price) {
        boolean nullSquareAndPrice = ("all".equals(square) || (square == null))
                && ("all".equals(price) || (price == null));
        boolean nullSquare = "all".equals(square) || (square == null);
        boolean nullPrice = "all".equals(price) || (price == null);
        if (villageId != 0) {
            if (nullSquareAndPrice) {
                return 0; // findByVillageId
            } else if (nullSquare) {
                return 1; // findByVillageIdAndPrice
            }
            return 2; // findByVillageIdAndSquare
        }

        // selectAllPost
        if (nullSquareAndPrice) {
            return 3;
        }

        // findBySquareAndPrice
        if ((!nullSquare) && (!nullPrice)) {
            return 4;
        }

        if ("all".equals(square)) {
            return 5; //findByPrice
        } else {
            return 6; // findBySquare
        }
    }
}
