<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../common/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
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

                <div>
                    <div class="d-flex justify-content-end my-2 mr-3">
                        <button type="button" class="btn btn-primary " data-toggle="modal" data-target="#modalAdd">
                            <i class="fas fa-plus"></i>
                            <span>New user</span>
                        </button>
                    </div>

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Fullname</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone number</th>
                            <th scope="col">Role</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${users}">
                            <tr>
                                <td><c:out value="${item.userId}"/></td>
                                <td id="fullName-${item.userId}"><c:out value="${item.fullName}"/></td>
                                <td id="email-${item.userId}"><c:out value="${item.email}"/></td>
                                <td id="SDT-${item.userId}"><c:out value="${item.SDT}"/></td>
                                <td id="roleId-${item.userId}"><c:out value="${item.roleModel.roleName}"/></td>
                                <td>
                                <span data-toggle="modal" data-target="#modalEdit">
                                    <button class="btn-edit btn btn-success btn-sm rounded-0"
                                            data-id="${item.userId}"
                                            data-toggle="tooltip" data-placement="top" title="Edit">
                                    <i class="fa fa-edit"></i>
                                    </button>
                                </span>
                                    <span data-toggle="modal" data-target="#modalDelete">
                                    <button class="btn-delete btn btn-danger btn-sm rounded-0" type="button"
                                            data-toggle="tooltip" data-placement="top" title="Delete"
                                            data-id="${item.userId}">
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
                </div>

                <div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalAddLabel">Add user</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/admin/user" method="POST" onsubmit="addDataRoleId()">
                                    <div class="form-group">
                                        <label for="fullName">Fullname</label>
                                        <input type="text" class="form-control" id="fullName" name="fullName"
                                               placeholder="Fullname" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" id="email" name="email"
                                               placeholder="Email" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="SDT">Phone number</label>
                                        <input type="text" class="form-control" id="SDT" name="SDT"
                                               placeholder="Phone number" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="role">Role name</label>
                                        <select id="role" class="form-control">
                                            <c:forEach var="item" items="${roles}">
                                                <option data-id="${item.roleId}">${item.roleName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <input type="text" name="roleId" value="" hidden>
                                    <button type="submit" class="btn btn-primary float-left">Add</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--Model Edit--%>
                <div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalEditLabel">Edit user</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/admin/edit-user" method="POST" onsubmit="return submitEditForm()">
                                    <div class="form-group">
                                        <label for="fullName">Fullname</label>
                                        <input type="text" class="form-control" name="fullName" placeholder="Fullname"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" name="email" placeholder="Email"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="SDT">Phone number</label>
                                        <input type="text" class="form-control" name="SDT" placeholder="Phone number"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="role">Role name</label>
                                        <select <%--id="role"--%> class="form-control role-edit">
                                            <c:forEach var="item" items="${roles}">
                                                <option data-id="${item.roleId}">${item.roleName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <input type="text" name="roleId" value="" hidden>
                                    <input type="text" name="userId" value="" hidden>
                                    <button type="submit" class="btn btn-primary float-left">Edit</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--Modal delete--%>
                <div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <p class="text-center">ARE YOU SURE YOU WANT TO DELETE THIS ACCOUNT ?</p>
                                <div class="d-flex justify-content-center" style="height: 150px" ;>
                                    <i class="fas fa-trash-alt" style="font-size: 120px; color: #ff0000"></i>
                                </div>
                                <form action="/admin/delete-user" method="POST">
                                    <input type="text" name="userId" value="" hidden>
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

    const addDataRoleId = () => {
        let id = $('#role option:selected').data('id');
        $('#modalAdd input[name="roleId"]').val(id);
        // alert($('#district option:selected').data('id'));
    }

    const submitEditForm = () => {
        let id = $('.role-edit option:selected').data('id');
        $('#modalEdit input[name="roleId"]').val(id);
        return true;
    }

    const editUser = (id) => {
        $('#modalEdit input[name="userId"]').val(id);

        <c:forEach var="item" items="${users}">
        if (id === ${item.userId}) {
            $('#modalEdit input[name="fullName"]').val('${item.fullName}');
            $('#modalEdit input[name="email"]').val('${item.email}');
            $('#modalEdit input[name="SDT"]').val('${item.SDT}');
             let queryName = '.role-edit option[data-id=' + ${item.roleModel.roleId} +']';
            console.log(queryName)
            $(queryName).attr('selected', 'selected');
        }
        </c:forEach>
    }

    $(document).ready(function () {
        $('.btn-edit').click(function () {
            let id = $(this).data('id');
            editUser(id)
        });

        $('.btn-delete').click(function (e) {
            let id = $(this).data('id');
            $('#modalDelete input[name="userId"]').val(id);
        });
    });

    $('#pagination').pagination({
        total: ${pageble.totalItem}, // 总数据条数
        current: ${pageble.page}, // 当前页码
        length: ${pageble.maxPageItem}, // 每页数据量
        size: 2, // 显示按钮个数
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
        click: function(options,$target) { // 点击按钮事件
            /*$("#page").val(options.current);
            // $("#maxPageItem").val(options.length);
            $("#formSearch").submit();*/
            window.location.href = '/admin/user?page=' + options.current;
        }
    });
</script>
</body>
</html>
