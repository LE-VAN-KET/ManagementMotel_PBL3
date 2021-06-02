package service.implement;

import bean.PostModel;
import dao.IPostDAO;
import dao.implement.DistrictDAO;
import dao.implement.VillageDAO;
import paging.Pageble;
import service.IPostService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@ManagedBean
public class PostService implements IPostService {
    @Inject
    private IPostDAO postDAO;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Override
    public List<PostModel> selectAll(Pageble pageble) {
        return postDAO.selectAll(pageble);
    }

    @Override
    public Long insert(PostModel postModel) {
        String postSlug = toSlug(postModel.getTitle() + " " + new Date().getTime());
        postModel.setPostSlug(postSlug);
        return postDAO.insert(postModel);
    }

    @Override
    public void updateLinkImages(Long postId, String linkImages) {
        postDAO.updateLinkImages(postId, linkImages);
    }

    @Override
    public int getTotalItem() {
        return postDAO.getTotalItem();
    }

    @Override
    public PostModel findOneByPostSlug(String postSLug) {
        return postDAO.findOneByPostSlug(postSLug);
    }

    @Override
    public PostModel findByPostId(Long postId) {
        return postDAO.findByPostId(postId);
    }

    @Override
    public void deleteByPostId(Long postId) {
        postDAO.deleteByPostId(postId);
    }

    @Override
    public Map<String, String> validatePost(PostModel postModel) {
        Map<String, String> errors = new HashMap<>();
        if (postModel.getTitle() == null || "".equals(postModel.getTitle()))
            errors.put("title_error", "Tiêu đề không được bỏ trống");

        if (postModel.getPrice() == 0)
            errors.put("price_error", "Giá trọ phải là số nguyên dương");

        if (postModel.getSquare() == 0)
            errors.put("square_error", "Diện tích phải là số nguyên dương");

        if(postModel.getAddress() == null || "".equals(postModel.getAddress()))
            errors.put("address_error", "Địa chỉ cụ thể không được bỏ trống");

        if(postModel.getDescription() == null || "".equals(postModel.getDescription()))
            errors.put("description_error", "Mô tả không được bỏ trống");

        if(new VillageDAO().findOne(postModel.getVillageModel().getVillageId()).size() == 0) {
            errors.put("village_error", "Phường/xã không hợp lệ");
        }
        return errors;
    }

    private String toSlug(String title) {
        String nowhitespace = WHITESPACE.matcher(title).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public void updateByPostId(PostModel postModel) {
        String postSlug = toSlug(postModel.getTitle() + " " + new Date().getTime());
        postModel.setPostSlug(postSlug);
        postDAO.updateByPostId(postModel);
    }

    @Override
    public void updateStatusPostByPostId(Long postId, boolean statusPost) {
        postDAO.updateStatusPostByPostId(postId, statusPost);
    }
}
