<%--
  Created by IntelliJ IDEA.
  User: Van Ket
  Date: 12/05/2021
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../common/taglib.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${POSTMODELS.postSlug}</title>
    <link rel="stylesheet" href="${contextPath}/assets/css/slideShowImages.css">
    <%@include file="../../../common/csslib.jsp"%>
</head>
<body style="background-color: #f5f5f5;">
    <%--    loading--%>
    <div id="loading" class="">
        <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
    <%--     block navigation--%>
    <%@include file="../components/navigation.jsp"%>

    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-0 col-sm-0 col-xs-0">
                <div class="sidebar-post">
                    <div class="title-sidebar">
                        <i class="fas fa-list-ul" class="bg-primary mr-2"></i>
                        <span>Phòng Trọ</span>
                    </div>
                    <ul class="navbar-nav bg-white">
                        <li class="nav-item py-1">
                            <a class="nav-link " href="/quan-li-dang-tin">
                                <i class="fas fa-search-location mr-1"></i>
                                <span>Phòng trọ tại Liên Chiểu</span>
                            </a>
                        </li>
                        <li class="nav-item py-1">
                            <a class="nav-link " href="/quan-li-dang-tin">
                                <i class="fas fa-search-location mr-1"></i>
                                <span>Phòng trọ tại Hải Châu</span>
                            </a>
                        </li>
                        <li class="nav-item py-1">
                            <a class="nav-link" href="#">
                                <i class="fas fa-search-location mr-1"></i>
                                <span>Phòng trọ tại Thanh Khê</span>
                            </a>
                        </li>
                        <li class="nav-item py-1">
                            <a class="nav-link " href="/quan-li-dang-tin">
                                <i class="fas fa-search-location mr-1"></i>
                                <span>Phòng trọ tại Cẩm Lệ</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                <h1 class="title-post mt-2">
                    ${POSTMODELS.title}
                </h1>
                <div class="address">
                    <i class="fas fa-home mr-2"></i>
                    <span>${POSTMODELS.address}, ${POSTMODELS.villageModel.villageName},
                        ${POSTMODELS.villageModel.districtModel.districtName}, Đà Nẵng</span>
                </div>
                <div class="imagesPreview">
                    <div class="nine columns">
                        <div class="coverflow top10 bot10">
                            <a class="prev-arrow"></a>
                            <%--                            <a href="">--%>
                            <%--                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/nha-06-nam-thanh1.jpg"--%>
                            <%--                                    class="coverflow__image lazyload" />--%>
                            <%--                            </a>--%>
                            <c:forEach items="${IMAGES}" var="image">
                                <a href="" class="image-post">
                                    <img data-src="https://drive.google.com/uc?export=view&id=${image}"
                                        class="coverflow__image lazyload" />
                                </a>
                            </c:forEach>
                            <%--                            <a href="">--%>
                            <%--                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/3.jpg"--%>
                            <%--                                    class="coverflow__image lazyload" />--%>
                            <%--                            </a>--%>

                            <a class="next-arrow"></a>
                        </div>
                    </div>
                </div>
                <div class="description bg-white p-1">
                    <h5>Mô tả chi tiết</h5>
                    ${POSTMODELS.description}
                </div>
                <div class="information bg-white p-1">
                    <h5>Thông Tin</h5>
                    <div class="row m-0">
                        <div class="col-6 p-1">
                            <i class="fas fa-money-bill-alt mr-1 text-primary"></i>
                            <span class="font-weight-bold">Price: </span>
                            <span class="font-italic">${POSTMODELS.price}00 đồng/Tháng</span>
                        </div>
                        <div class="col-6 p-1">
                            <span class="font-weight-bold">Square meters: </span>
                            <span class="font-italic">${POSTMODELS.square}m<sup>2</sup></span>
                        </div>
                        <div class="col-12 p-1">
                            <i class="fas fa-map-marker-alt mr-2 text-primary"></i>
                            <span class="font-weight-bold">Address: </span>
                            <span class="font-italic">${POSTMODELS.address}, ${POSTMODELS.villageModel.villageName},
                                ${POSTMODELS.villageModel.districtModel.districtName}, Đà Nẵng</span>
                        </div>
                        <div class="col-6 p-1">
                            <span class="font-weight-bold">Họ và tên: </span>
                            <span class="font-italic text-primary">${POSTMODELS.userModel.fullName}</span>
                        </div>
                        <div class="col-6 p-1">
                            <span class="font-weight-bold">SDT: </span>
                            <span class="font-italic text-primary">${POSTMODELS.userModel.SDT}</span>
                        </div>
                        <div class="col-12 p-1">
                            <span class="font-weight-bold">Email: </span>
                            <span class="font-italic text-primary">${POSTMODELS.userModel.email}</span>
                        </div>
                    </div>
                </div>
                <div class="information bg-white mt-3">
                    <h5>Bản Đồ</h5>
                    <div id="map"></div>
                </div>
                <div class="comment-wrapper mt-3">
                    <h5>User Comments</h5>
                    <div class="comments-container">
                        <ul id="comments-list" class="comments-list p-0">
                            <li>
                                <div class="comment-main-level row m-0">
                                    <!-- Avatar -->
                                    <div class="comment-avatar col-1 p-0">
                                        <img class="img-fluid img-thumbnail lazyload p-0"
                                             data-src="${contextPath}/assets/images/143086968_2856368904622192_1959732218791162458_n.png"
                                             alt="image...">
                                    </div>
                                    <!-- Contenedor del Comentario -->
                                    <div class="comment-box col-10">
                                        <div class="comment-head">
                                            <h6 class="comment-name by-author"><a href="#">User Name</a></h6>
                                            <span class="posted-time">Posted on 10-FEB-2015 12:00</span>
                                            <div class="comments-list-action action-toggle bg-transparent">
                                                <div class="dropdown">
                                                    <a href="#" data-toggle="dropdown">
                                                        <i class="fas fa-ellipsis-h"></i>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <a class="dropdown-item text-dark" href="#">
                                                            <i class="far fa-edit mr-2"></i>Edit</a>
                                                        <a class="dropdown-item text-dark" href="#">
                                                            <i class="fas fa-trash mr-2"></i>Remove</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="comment-content">
                                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi
                                            et iure laudantium vitae, praesentium optio, sapiente distinctio illo?
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="comment-main-level row m-0">

                                    <div class="comment-avatar col-1 p-0">
                                        <img class="img-fluid img-thumbnail lazyload p-0"
                                             data-src="${contextPath}/assets/images/143086968_2856368904622192_1959732218791162458_n.png"
                                             alt="image...">
                                    </div>

                                    <div class="comment-box col-10">
                                        <div class="comment-head">
                                            <h6 class="comment-name"><a href="#">Lorena Rojero</a></h6>
                                            <span class="posted-time">Posted on DD-MM-YYYY HH:MM</span>
                                        </div>
                                        <div class="comment-content">
                                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi
                                            et iure laudantium vitae, praesentium optio, sapiente distinctio illo?
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <form action="" method="post" class="mx-5" id="formComment">
                            <div class="form-group">
                                <textarea name="content" class="form-control" rows="1"
                                          placeholder="Enter your comment here..." required></textarea>
                            </div>
                            <button class="btn btn-light bg-primary float-right mb-2" type="submit">
                                <span class="text-light mr-1">comment</span>
                                <i class="far fa-paper-plane text-light"></i>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <%@include file="../components/footer.jsp"%>

    <%@include file="../../../common/javasciptlib.jsp"%>
    <script src="../../../assets/javascript/slideShow.js"></script>
    <script>
        let width = $(".comment-avatar").width()/2;
        $('head').append('<style>.comments-list:before{left:' + width + 'px !important;}</style>');
        $(document).ready(function () {
            $('#loading').addClass("show");
            setTimeout(function () {
                $('#loading').removeClass("show");
                $('#loading').addClass("hidden");
            }, 1500);

            let address = '${POSTMODELS.address}, ${POSTMODELS.villageModel.villageName},' +
                '${POSTMODELS.villageModel.districtModel.districtName}, Đà Nẵng';
            let query = address.split(" ");
            query = query.join("+");
            $("#map").append('<iframe' +
                ' width="100%"' +
                ' height="400"' +
                ' style="border:0"' +
                ' loading="lazy"' +
                ' allowfullscreen' +
                ' src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBm5cVeHG1x831muJ_Q7NiNUbFRt0R3Mug&q=' +
                query + '"></iframe>');

            $('textarea[name="content"]').each(function () {
                this.setAttribute('style', 'overflow-y:hidden;');
            }).on('input', function () {
                this.style.height = 'auto';
                this.style.height = (this.scrollHeight) + 'px';
            });

            $("#formComment").submit( function (e) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/comment/add",
                    data: JSON.stringify({
                        content : $.trim($("textarea[name='content']").val()),
                        postId : ${POSTMODELS.postId}
                    }),
                    contentType: "application/json;charset=utf-8",
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        if (data.urlRedirect) {
                            location.replace(data.urlRedirect);
                            return;
                        }
                        let listErrors = Object.keys(data.errors).length;
                        if (listErrors > 0) {
                            data.errors.forEach(err => {
                                toastr.error(err, "Comment failed");
                            })
                        } else {
                            $("#comments-list").append('<li><div class="comment-main-level row m-0">' +
                                '<div class="comment-avatar col-1 p-0">' +
                                '<img class="img-fluid img-thumbnail lazyload p-0 "' +
                                'data-src="${contextPath}/assets/images/143086968_2856368904622192_1959732218791162458_n.png" ' +
                                'alt="image..."></div><div class="comment-box col-10"><div class="comment-head">' +
                                '<h6 class="comment-name by-author"><a href="#">' + '${ACCOUNTMODEL.user.fullName}' +
                                '</a></h6><span class="posted-time">Posted on ' + data.createAt + '</span>' +
                                '<div class="comments-list-action action-toggle bg-transparent">' +
                                '<div class="dropdown"><a href="#" data-toggle="dropdown">' +
                                '<i class="fas fa-ellipsis-h"></i></a><div class="dropdown-menu dropdown-menu-right">' +
                                '<a class="dropdown-item text-dark" href="#"><i class="far fa-edit mr-2"></i>Edit</a>' +
                                '<a class="dropdown-item text-dark" href="#"><i class="fas fa-trash mr-2"></i>Remove</a>' +
                                '</div></div></div></div><div class="comment-content">' + data.comment.content +
                                '</div></div></div></li>');
                        }
                    },
                    error: function (e) {
                        toastr.error("Comment failed");
                    }
                });
                $('textarea[name="content"]').val('');
                e.preventDefault();
            })
        })
    </script>
</body>
</html>