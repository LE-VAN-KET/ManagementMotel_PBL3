<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>

<head>
    <title>Title</title>
    <%@include file="../../common/csslib.jsp" %>
    <link rel="stylesheet" href="../../assets/css/admin.home.css">
    <link rel="stylesheet" href="../../assets/css/slideShowImages.css">
    <link rel="stylesheet" href="../../assets/css/detailPost.css">
    <style>
        .nav-link:hover {
            border: none;
        }
    </style>
</head>
<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <%@include file="../../common/sidebar.jsp" %>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div id="content">
            <div class="container-fluid p-0 px-lg-0 px-md-0">
                <!-- Topbar -->
                <%@include file="../../common/navbar.jsp" %>

                <div class="container">
                    <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="nav-detail-post" data-toggle="tab" href="#detail-post"
                               role="tab" aria-controls="home" aria-selected="true">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="nav-comment" data-toggle="tab" href="#comment" role="tab"
                               aria-controls="profile" aria-selected="false">Profile</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane fade show active" id="detail-post">
                            <div class="col-lg-8 offset-lg-2 col-md-12 col-sm-12 col-xs-12">
                                <h1 class="title-post">
                                    ${POSTMODELS.title}
                                </h1>
                                <div class="address">
                                    <i class="fas fa-home mr-2"></i>
                                    <span>${POSTMODELS.address}</span>
                                </div>
                                <div class="imagesPreview p-2 border">
                                    <div class="nine columns">
                                        <div class="coverflow top10 bot10">
                                            <a class="prev-arrow"></a>
                                            <c:forEach items="${IMAGES}" var="image">
                                                <a href="" class="image-post">
                                                    <img data-src="https://drive.google.com/uc?export=view&id=${image}"
                                                         class="coverflow__image lazyload"/>
                                                </a>
                                            </c:forEach>
                                            <a class="image" href="">
                                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/2-1.jpg"
                                                     class="coverflow__image lazyload"/>
                                            </a>
                                            <a class="next-arrow"></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="description bg-white p-2 border">
                                    <h5>Mô tả Chi tiết</h5>
                                    <p>Cho thuê căn hộ sát bên cạnh chợ Non Nước – Ngũ Hành Sơn (06 Nam Thành) – Nằm ở
                                        vị trí 2 mặt
                                        tiền, diện tích 32m2, có gác lửng rộng rãi, thiết bị vệ sinh sinh cao cấp, giá
                                        cho thuê.
                                        2tr/căn
                                        hộ Liên hệ Đt, zalo: 0905 868 758 (A.Thịnh)</p>
                                    <p class="text-danger">${POSTMODELS.description}</p>
                                </div>
                                <div class="information bg-white p-2 border">
                                    <h5>Thông Tin</h5>
                                    <div class="container table-responsive py-2">
                                        <table class="table table-bordered table-hover">
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="bg-light">Địa chỉ:</th>
                                                <td class="text-primary">${POSTMODELS.address}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="bg-light">Giá cho thuê:</th>
                                                <td class="text-danger">${POSTMODELS.price} triệu/tháng</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="bg-light">Diện tích:</th>
                                                <td class="text-danger">${POSTMODELS.square}m<sup>2</sup></td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="bg-light">Liên hệ:</th>
                                                <td>${POSTMODELS.userModel.fullName}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="bg-light">Điện thoại:</th>
                                                <td class="text-success">${POSTMODELS.userModel.SDT}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="bg-light">Email:</th>
                                                <td>${POSTMODELS.userModel.email}</td>
                                            </tr>
                                            <tr class="status-post">
                                                <th scope="row" class="bg-light">Tình trạng bài đăng:</th>
                                                <c:if test="${POSTMODELS.statusPost}">
                                                    <td class="text-success">Đã duyệt</td>
                                                </c:if>
                                                <c:if test="${!POSTMODELS.statusPost}">
                                                    <td class="text-danger">Chờ duyệt
                                                        <button class="btn-update btn-success ml-2">DUYỆT</button>
                                                    </td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="bg-light">Ngày hết hạn:</th>
                                                <td class="text-danger">21/05/2021 13:14</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="comment">
                            <div class="comment-wrapper mt-3">
                                <h5>User Comments</h5>
                                <div class="comments-container">
                                    <ul id="comments-list" class="comments-list p-0">
                                        <c:forEach items="${POSTMODELS.commentModelList}" var="comment">
                                            <li>
                                                <div class="comment-main-level row m-0"
                                                     data-userid="${comment.userModel.userId}"
                                                     data-commentid="${comment.commentId}">
                                                    <!-- Avatar -->
                                                    <div class="comment-avatar col-1 p-0">
                                                        <img class="img-fluid img-thumbnail lazyload p-0"
                                                             data-src="${contextPath}/assets/images/143086968_2856368904622192_1959732218791162458_n.png"
                                                             alt="image...">
                                                    </div>
                                                    <!-- Contenedor del Comentario -->
                                                    <div class="comment-box col-10">
                                                        <div class="comment-head">
                                                            <h6 class="comment-name by-author">
                                                                <a href="#">${comment.userModel.fullName}</a>
                                                            </h6>
                                                            <span class="posted-time">
                                                        Posted on <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                                                  value="${comment.createAt}"/>
                                                    </span>
                                                            <div class="comments-list-action action-toggle bg-transparent">
                                                                <div class="dropdown">
                                                                    <a href="#" data-toggle="dropdown">
                                                                        <i class="fas fa-ellipsis-h"></i>
                                                                    </a>
                                                                    <div class="dropdown-menu dropdown-menu-right">
                                                                        <a class="dropdown-item text-dark"
                                                                           href="javascript:void(0)" data-toggle="modal"
                                                                           data-target="#confirm-delete">
                                                                            <i class="fas fa-trash mr-2"></i>Remove</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="comment-content">
                                                            <p class="p-0 m-0">${comment.content}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <span>
                                            Please Confirm delete comment this
                                            <i class="fas fa-check-circle ml-2"></i>
                                        </span>
                                    </div>
                                    <div class="modal-body mx-auto">
                                        <span>Are you sure?</span>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-danger text-light" id="btnRemove">
                                            <i class="far fa-trash-alt mr-1 text-light"></i>
                                            Delete
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="../../common/javasciptlib.jsp" %>
<script src="../../assets/javascript/admin.home.js"></script>
<script src="../../assets/javascript/slideShow.js"></script>
<script>
    $(() => {
        $('[data-toggle="tooltip"]').tooltip();
    });

    $(document).ready(function () {
        $('.btn-update').click(function (e) {
            e.preventDefault();
            $.ajax({
                type: "POST",
                url: "/admin/update-status-post/${POSTMODELS.postId}",
                data: {statusPost: ${!POSTMODELS.statusPost}},
                success: function (responseText) {
                    toastr.success("Bài đăng đã được duyệt");
                    $('.status-post td').remove();
                    $('.status-post').append('<td class="text-success">Đã duyệt</td>');
                }
            });
        });

        $('#btnRemove').click(function (e) {
            e.preventDefault();
            const commentId = $(this).data("commentid")
            $.ajax({
                type: "POST",
                url: "/admin/remove-comment",
                data: {commentId: commentId},
                success: function (responseText) {
                    deleteComment(commentId);
                    $('#confirm-delete').modal('hide')
                }
            });
        });

        $('#confirm-delete').on('show.bs.modal', function(e) {
            let btn = $(this).find('#btnRemove');
            btn.attr('data-userid', $(e.relatedTarget).parents(".comment-main-level").data("userid"));
            btn.attr('data-commentid', $(e.relatedTarget).parents(".comment-main-level").data("commentid"));
        });

        const deleteComment = function (commentId) {
            $(".comment-main-level").each(function () {
                if ($(this).data("commentid") == commentId) {
                    $(this).remove();
                }
            })
            toastr.success("delete comment successfully");
        }
    });
</script>
</body>

</html>

