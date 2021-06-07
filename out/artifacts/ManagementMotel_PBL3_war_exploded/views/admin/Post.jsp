<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang-chu</title>
    <%@include file="../../common/csslib.jsp" %>
    <link rel="stylesheet" href="../../assets/css/admin.home.css">
    <link rel="stylesheet" href="../../assets/css/pagination.min.css">
</head>
<body>
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

                <div class="d-flex justify-content-end my-2 mr-3">
                    <button type="submit" class="btn btn-primary"
                            onclick="window.location.href='/admin/new-post'">
                        <i class="fas fa-plus"></i>
                        <span>New post</span>
                    </button>
                </div>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Post ID</th>
                        <th scope="col">Title</th>
                        <%--           <th scope="col">Username</th>--%>
                        <th scope="col">Status post</th>
                        <th scope="col">Status motel</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${posts}">
                        <tr>
                            <td><c:out value="${item.postId}"/></td>
                            <td><c:out value="${item.title}"/></td>
                            <td>
                                <c:if test="${!item.statusRental}">
                                    <span class="text-success">Còn chỗ</span>
                                </c:if>
                                <c:if test="${item.statusRental}">
                                    <span class="text-danger">Hết chỗ</span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${item.statusPost}">
                                    <span class="text-success">Đã duyệt</span>
                                </c:if>
                                <c:if test="${!item.statusPost}">
                                    <span class="text-danger">Chờ duyệt</span>
                                </c:if>
                            <td>
                                <button class="btn-view btn btn-primary btn-sm rounded-0" type="button"
                                        onclick="window.location.href='/admin/detail-post/${item.postId}'"
                                        data-toggle="tooltip" data-placement="top" title="View">
                                    <i class="fas fa-eye"></i>
                                </button>
                                <button class="btn-edit btn btn-success btn-sm rounded-0"
                                        onclick="window.location.href='/admin/edit-post/${item.postSlug}'"
                                        data-toggle="tooltip" data-placement="top" title="Edit">
                                    <i class="fa fa-edit"></i>
                                </button>
                                <span data-toggle="modal" data-target="#modalDelete">
                                    <button class="btn-delete btn btn-danger btn-sm rounded-0" type="button"
                                            data-toggle="tooltip" data-placement="top" title="Delete"
                                            data-id="${item.postId}">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="box">
                    <ul id="pagination" class="pagination"></ul>
                    <div class="show"></div>
                </div>

                <%--modal delete--%>
                <div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <p class="text-center">ARE YOU SURE ?</p>
                                <form action="/admin/delete-post" method="POST">
                                    <input type="text" name="postId" value="" hidden>
                                    <button type="button" class="btn btn-secondary float-right" data-dismiss="modal">
                                        Close
                                    </button>
                                    <button type="submit" class="btn btn-danger float-right m-r-10">Delete</button>
                                </form>
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
<script src="../../assets/javascript/pagination.min.js"></script>
<script>
    $(() => {
        $('[data-toggle="tooltip"]').tooltip();
    });

    $('#pagination').pagination({
        total: ${pageble.totalItem}, // 总数据条数
        current: ${pageble.page}, // 当前页码
        length: ${pageble.maxPageItem}, // 每页数据量
        size: ${pageble.totalPage}, // 显示按钮个数
        /**
         * [click description]
         * @param  {[object]} options = {
         *      current: options.current,
         *      length: options.length,
         *      total: options.total
         *  }
         * @param  {[object]} $target [description]
         * @return {[type]}         [description]
         */
        click: function (options, $target) { // 点击按钮事件
            /*$("#page").val(options.current);
            // $("#maxPageItem").val(options.length);
            $("#formSearch").submit();*/
            window.location.href = '/admin/post?page=' + options.current;
        }
    });


    $(document).ready(function () {
        $('navbar-search').attr('action','/admin/post');

        if ("${message}" != "" && "${alert}" == "danger") {
            toastr.error('${message}', 'falied!');
        }

        $('.btn-delete').click(function (e) {
            let id = $(this).data('id');
            $('#modalDelete input[name="postId"]').val(id);
        });
    });

</script>
</body>
</html>
