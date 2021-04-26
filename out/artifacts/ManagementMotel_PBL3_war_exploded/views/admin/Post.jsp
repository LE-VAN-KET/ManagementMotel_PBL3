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

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Post ID</th>
                        <th scope="col">Title</th>
                        <th scope="col">Username</th>
                        <th scope="col">Status post</th>
                        <th scope="col">Status motel</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
<%--                        <c:forEach></c:forEach>--%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="../../common/javasciptlib.jsp"%>
<script src="../../assets/javascript/admin.home.js"></script>
</body>
</body>
</html>
