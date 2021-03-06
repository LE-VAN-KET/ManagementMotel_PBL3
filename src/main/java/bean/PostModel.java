package bean;

import java.sql.Timestamp;
import java.util.List;

public class PostModel extends AbstractModel {
    private Long postId;
    private String title;
    private String description;
    private String linkImages;
    private Double price;
    private Long square;
    private String address;
    private String postSlug;
    private List<CommentModel> commentModelList;

    public List<CommentModel> getCommentModelList() {
        return commentModelList;
    }

    public void setCommentModelList(List<CommentModel> commentModelList) {
        this.commentModelList = commentModelList;
    }

    public String getPostSlug() {
        return postSlug;
    }

    public void setPostSlug(String postSlug) {
        this.postSlug = postSlug;
    }

    private VillageModel villageModel;
    private UserModel userModel;
    private Boolean statusPost;
    private Boolean statusRental;
    private Timestamp publishedAt;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkImages() {
        return linkImages;
    }

    public void setLinkImages(String linkImages) {
        this.linkImages = linkImages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getSquare() {
        return square;
    }

    public void setSquare(Long square) {
        this.square = square;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public VillageModel getVillageModel() {
        return villageModel;
    }

    public void setVillageModel(VillageModel villageModel) {
        this.villageModel = villageModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Boolean getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(Boolean statusPost) {
        this.statusPost = statusPost;
    }

    public Boolean getStatusRental() {
        return statusRental;
    }

    public void setStatusRental(Boolean statusRental) {
        this.statusRental = statusRental;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

}
