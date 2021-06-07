<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang-chu</title>
    <%@include file="../../common/csslib.jsp"%>
    <link rel="stylesheet" href="../../assets/css/admin.home.css">
</head>
<body>
<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <%@include file="../../common/sidebar.jsp"%>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div id="content">
            <div class="container-fluid p-0 px-lg-0 px-md-0">
                <!-- Topbar -->
                <%@include file="../../common/navbar.jsp"%>

                <div class="container-fluid mt-2">
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2 border">
                            <form class="my-2" action="/admin/post?method=PUT" method="post">
                                <div class="form-group">
                                    <label for="title">Tiêu đề:</label>
                                    <small class="form-text text-muted">
                                        Ví dụ: Cho thuê nhà nguyên căn mới xây đường trường chinh
                                    </small>
                                    <input type="text" class="form-control" id="title" placeholder="Tiêu đề">
                                </div>
                                <div class="form-group">
                                    <label for="description">Nội dung chi tiết:</label>
                                    <textarea class="form-control" id="description" rows="3"></textarea>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-lg-6">
                                        <label for="price">Giá thuê / Tháng:</label>
                                        <small class="form-text text-muted">Đơn vị VNĐ (Ví dụ giá 2 triệu bạn cần nhập: 2000000)</small>
                                        <input type="text" class="form-control" id="price" placeholder="Giá thuê">
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label for="square">Diện tích:</label>
                                        <small class="form-text text-muted">Nhập chính xác diện tích nha! Đơn vị (m2)</small>
                                        <input type="text" class="form-control" id="square" placeholder="Diện tích">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-lg-6">
                                        <label for="district">Quận / Huyện:</label>
                                        <input type="text" class="form-control" id="district" placeholder="Giá thuê">
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label for="village">Phường / Xã:</label>
                                        <input type="text" class="form-control" id="village" placeholder="Diện tích">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="address">Địa chỉ cụ thể:</label>
                                    <input type="text" class="form-control" id="address" placeholder="Số nhà, tên đường...">
                                </div>
                                <div class="form-group">
                                    <label for="formFileMultiple">Hình ảnh:</label>
                                    <input class="form-control" type="file" id="formFileMultiple" multiple/>
                                </div>
                                <label>Thông tin liên hệ:</label>
                                <div class="form-row">
                                    <div class="form-group col-lg-4">
                                        <input type="text" class="form-control" id="fullName" placeholder="Họ và tên">
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <input type="text" class="form-control" id="email" placeholder="Email">
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <input type="text" class="form-control" id="sdt" placeholder="Số điện thoại">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../common/javasciptlib.jsp"%>
<script src="../../assets/javascript/admin.home.js"></script>
</body>
</html>
