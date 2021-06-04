<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang-chu</title>
    <%@include file="../../common/csslib.jsp"%>
    <link rel="stylesheet" href="../../assets/css/admin.home.css">
</head>
<body class="dark-edition">
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
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid px-lg-4">
                    <div class="row">
                        <div class="col-md-12 mt-lg-4 mt-4">
                            <!-- Page Heading -->
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-sm-3">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title mb-4">Users</h5>
                                            <h1 class="display-5 mt-1 mb-3">2.382</h1>
                                            <div class="mb-1">
                                                    <span class="text-danger"> <i
                                                            class="mdi mdi-arrow-bottom-right"></i> -3.65% </span>
                                                <span class="text-muted">Since last week</span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-sm-3">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title mb-4">Post</h5>
                                            <h1 class="display-5 mt-1 mb-3">2.382</h1>
                                            <div class="mb-1">
                                                    <span class="text-danger"> <i
                                                            class="mdi mdi-arrow-bottom-right"></i> -3.65% </span>
                                                <span class="text-muted">Since last week</span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-sm-3">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title mb-4">Comment</h5>
                                            <h1 class="display-5 mt-1 mb-3">2.382</h1>
                                            <div class="mb-1">
                                                    <span class="text-danger"> <i
                                                            class="mdi mdi-arrow-bottom-right"></i> -3.65% </span>
                                                <span class="text-muted">Since last week</span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-sm-3">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title mb-4">Earnings</h5>
                                            <h1 class="display-5 mt-1 mb-3">$21.300</h1>
                                            <div class="mb-1">
                                                    <span class="text-success"> <i
                                                            class="mdi mdi-arrow-bottom-right"></i> 6.65% </span>
                                                <span class="text-muted">Since last week</span>
                                            </div>
                                        </div>
                                    </div>

                                </div>


                            </div>
                        </div>



                        <!-- column -->
                        <div class="col-md-12 mt-4">
                            <div class="card">
                                <div class="card-body">
                                    <!-- title -->
                                    <div class="d-md-flex align-items-center">
                                        <div>
                                            <h4 class="card-title">Top View Post</h4>
                                            <h5 class="card-subtitle">Overview of Top View Post</h5>
                                        </div>
                                        <div class="ml-auto">
                                            <div class="dl">
                                                <select class="custom-select">
                                                    <option value="0" selected="">Monthly</option>
                                                    <option value="1">Daily</option>
                                                    <option value="2">Weekly</option>
                                                    <option value="3">Yearly</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- title -->
                                </div>
                                <div class="table-responsive">
                                    <table class="table v-middle">
                                        <thead>
                                        <tr class="bg-light">
                                            <th class="border-top-0">User</th>
                                            <th class="border-top-0">Title Post</th>
                                            <th class="border-top-0">View</th>
                                            <th class="border-top-0">Comment</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <div class="m-r-10"><a
                                                            class="btn btn-circle btn-info text-white">EA</a>
                                                    </div>
                                                    <div class="">
                                                        <h4 class="m-b-0 font-16">Elite Admin</h4>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>Cho thue phong tro quan Lien Chieu</td>
                                            <td>
                                                <label class="label label-danger">500</label>
                                            </td>
                                            <td>20</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>


            <footer class="footer">
                <div class="container-fluid">
                    <div class="row text-muted">
                        <div class="col-6 text-left">
                            <p class="mb-0">
                                <a href="index.html" class="text-muted"><strong>Dashboard Vishweb Design
                                </strong></a> &copy
                            </p>
                        </div>
                        <div class="col-6 text-right">
                            <ul class="list-inline">
                                <li class="footer-item">
                                    <a class="text-muted" href="#">Support</a>
                                </li>
                                <li class="footer-item">
                                    <a class="text-muted" href="#">Help Center</a>
                                </li>
                                <li class="footer-item">
                                    <a class="text-muted" href="#">Privacy</a>
                                </li>
                                <li class="footer-item">
                                    <a class="text-muted" href="#">Terms</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>

        </div>
    </div>
    <!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->

<!-- Optional JavaScript -->
<%@include file="../../common/javasciptlib.jsp"%>
<script src="../../assets/javascript/admin.home.js"></script>
</body>
</html>