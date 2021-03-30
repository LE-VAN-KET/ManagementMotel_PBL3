package bean;

import java.sql.Timestamp;

public class PostModel extends AbstractModel {
    private Long postId;
    private String title;
    private String desciption;
    private String linkImages;
    private Double price;
    private Long square;
    private String address;
    private String villageId;
    private Long userId;
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

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
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

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
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
}
