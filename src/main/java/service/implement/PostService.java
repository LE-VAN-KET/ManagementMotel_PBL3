package service.implement;

import bean.PostModel;
import bean.VillageModel;
import criteria.Criteria;
import dao.IPostDAO;
import dao.implement.DistrictDAO;
import dao.implement.PostDAO;
import dao.implement.VillageDAO;
import paging.Pageble;
import service.IPostService;
import service.IVillageService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@ManagedBean
public class PostService implements IPostService {
    @Inject
    private IPostDAO postDAO;

    @Inject
    private IVillageService villageService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private String toSlug(String title) {
        String nowhitespace = WHITESPACE.matcher(title).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public Long insert(PostModel postModel) {
        String postSlug = toSlug(postModel.getTitle() + " " + new Date().getTime());
        postModel.setPostSlug(postSlug);
        return postDAO.insert(postModel);
    }

    @Override
    public List<String> validatePost(PostModel postModel) {
        List<String> errors = new ArrayList<>();
        if (isAll_Fields_Empty(postModel.getTitle(), postModel.getDescription(),
                postModel.getPrice(), postModel.getSquare(), postModel.getAddress(),
                postModel.getVillageModel().getVillageId(), postModel.getUserModel().getUserId(),
                postModel.getStatusPost(), postModel.getStatusRental())) {
            // handle errors
            errors.add(resourceBundle.getString("all_fields_not_empty"));
        } else {
            VillageModel villageModel = villageService.findOneByvillageId(postModel.getVillageModel().getVillageId());
            if (villageModel == null) {
                errors.add(resourceBundle.getString("village_not_exist"));
            }
        }
        return errors;
    }

    private boolean isAll_Fields_Empty(Object... parameters) {
        for (Object parameter: parameters) {
            if ((parameter == null) || ("".equals(parameter.toString()))) {
                return true;
            }
        }
        return false;
    }

    public void updateLinkImages(Long postId, String linkImages) {
        postDAO.updateLinkImages(postId, linkImages);
    }

    @Override
    public List<PostModel> selectAll(Pageble pageble) {
        return postDAO.selectAll(pageble);
    }

    @Override
    public List<PostModel> selectAllByStatusPost(Pageble pageble, boolean statusPost) {
        return postDAO.selectAllByStatusPost(pageble, statusPost);
    }

    @Override
    public List<PostModel> findByVillageId(Long villageId, Pageble pageble) {
        return postDAO.findByVillageId(villageId, pageble);
    }

    @Override
    public List<PostModel> findByCriteria(Criteria criteria, Pageble pageble) {
        List<PostModel> postModels = null;
        try {
            postModels = postDAO.findByCriteria(criteria, pageble);
            pageble.setTotalItem(postDAO.getTotalItemByCriteria(criteria));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postModels;
    }

    @Override
    public int getTotalItem(String searchText) {
        return postDAO.getTotalItem(searchText);
    }

    @Override
    public int getTotalItemByStatusPost(boolean statusPost) {
        return postDAO.getTotalItemByStatusPost(statusPost);
    }

    @Override
    public PostModel findOneByPostSlug(String postSlug) {
        return postDAO.findOneByPostSlug(postSlug);
    }

    @Override
    public List<PostModel> findByUserId(Pageble pageble, Long userId) {
        return postDAO.findByUserId(pageble, userId);
    }

    @Override
    public int getTotalIemByUserId(Long userId) {
        return postDAO.getTotalIemByUserId(userId);
    }

    @Override
    public void deleteByPostId(Long postId) {
        postDAO.deleteByPostId(postId);
    }

    @Override
    public PostModel findByPostId(Long postId) {
        return postDAO.findByPostId(postId);
    }

    @Override
    public void updateByPostId(PostModel postModel) {
        String postSlug = toSlug(postModel.getTitle() + " " + new Date().getTime());
        postModel.setPostSlug(postSlug);
        postDAO.updateByPostId(postModel);
    }

    @Override
    public Map<String, String> validatePostAdmin(PostModel postModel) {
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

    @Override
    public void updateStatusPostByPostId(Long postId, boolean statusPost) {
        postDAO.updateStatusPostByPostId(postId,statusPost);
    }

    @Override
    public List<PostModel> selectAll(String searchText, Pageble pageble) {
        return postDAO.selectAll(searchText, pageble);
    }

    @Override
    public void updateStatusRentalByPostId(Long postId, boolean statusRental) {
        postDAO.updateStatusRentalByPostId(postId, statusRental);
    }

    /*public static void main(String[] args) {
        Pageble pageble = new Pageble();
        pageble.setMaxPageItem(20);
        pageble.setPage(1);
        List<PostModel> postModels = new PostDAO().selectAll(pageble);
        postModels.forEach(System.out::println);
        for(PostModel post : postModels) {
            String postSlug = new PostService().toSlug(post.getTitle() + " " + new Date().getTime());
            post.setPostSlug(postSlug);
            new PostDAO().updateByPostId(post);
            System.out.println("Success");
        }
    }*/
}
