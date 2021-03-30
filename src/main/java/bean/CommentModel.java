package bean;

public class CommentModel extends AbstractModel {
    private Long commnentId;
    private String content;
    private Long postId;
    private Long userId;

    public Long getCommnentId() {
        return commnentId;
    }

    public void setCommnentId(Long commnentId) {
        this.commnentId = commnentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
