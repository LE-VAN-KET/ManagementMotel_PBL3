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

                <div>
                    <div class="d-flex justify-content-end my-2 mr-3">
                        <button type="button" class="btn btn-primary " data-toggle="modal" data-target="#modalAdd">
                            <i class="fas fa-plus"></i>
                            <span>New account</span>
                        </button>
                    </div>

                    <%--                View list village--%>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="text-primary" scope="col">#</th>
                            <th class="text-primary" scope="col">Username</th>
                            <th class="text-primary" scope="col">Email</th>
                            <th class="text-primary" scope="col">Role</th>
                            <th class="text-primary" scope="col">Status</th>
                            <th class="text-primary" scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${accounts}">
                            <tr>
                                <td><c:out value="${item.accountId}" /></td>
                                <td id="username-${item.accountId}" class="text-info"><c:out value="${item.username}" /></td>
                                <td id="email-${item.accountId}"><c:out value="${item.user.email}" /></td>
                                <td id="roleId-${item.accountId}" class="text-danger"><c:out value="${item.user.roleModel.roleName}" /></td>
                                <td class="text-success">Active</td>
                                <td>
                                <span data-toggle="modal" data-target="#modalEdit">
                                    <button class="btn-edit btn btn-success btn-sm rounded-0" data-id="${item.accountId}"
                                            data-toggle="tooltip" data-placement="top" title="Edit"
                                    >
                                    <i class="fa fa-edit"></i>
                                    </button>
                                </span>
                                    <span data-toggle="modal" data-target="#modalDelete">
                                    <button class="btn-delete btn btn-danger btn-sm rounded-0" type="button"
                                            data-toggle="tooltip" data-placement="top" title="Delete"
                                            data-id="${item.accountId}">
                                    <i class="fa fa-trash"></i>
                                </button>
                                </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                  <%--  <!-- Modal add-->
                    <div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalAddLabel">Add village</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/admin/village?method=POST" method="POST" onsubmit="return dataDistrictId()">
                                        <div class="form-group">
                                            <label for="villageName">Village name</label>
                                            <input type="text" class="form-control" id="villageName" name="villageName" placeholder="Village name" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="district">District</label>
                                            <select id="district" class="form-control">
                                                <c:forEach var="item" items="${districts}">
                                                    <option data-id="${item.districtId}">${item.districtName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <input type="text" name="districtId" value="" hidden></input>
                                        <button type="submit" class="btn btn-primary float-left">Add</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    &lt;%&ndash;                Model Edit&ndash;%&gt;
                    <div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalEditLabel">Edit village</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/admin/village?method=PUT" method="POST" onsubmit="return submitEditForm()">
                                        <div class="form-group">
                                            <label for="villageName">Village name</label>
                                            <input type="text" class="form-control village-name-edit" name="villageName" placeholder="Village name" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="district">District</label>
                                            <select class="form-control district-edit">
                                                <c:forEach var="item" items="${districts}">
                                                    <option data-id="${item.districtId}">${item.districtName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <input type="text" name="districtId" value="" hidden>
                                        <input type="text" name="villageId" value="" hidden>
                                        <button type="submit" class="btn btn-primary float-left">Edit</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    &lt;%&ndash;                Modal Delete&ndash;%&gt;
                    <div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <p class="text-center">ARE YOU SURE ?</p>
                                    <form action="/admin/village?method=DELETE" method="POST">
                                        <input type="text" name="villageId" value="" hidden>
                                        <button type="button" class="btn btn-secondary float-right" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-danger float-right m-r-10">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>--%>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="../../common/javasciptlib.jsp"%>
<script src="../../assets/javascript/admin.home.js"></script>
</body>
</html>
