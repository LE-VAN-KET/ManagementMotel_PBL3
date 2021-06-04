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
            </div>

            <div>
                <div class="d-flex justify-content-end my-2 mr-3">
                    <button type="button" class="btn btn-primary " data-toggle="modal" data-target="#modalAdd">
                        <i class="fas fa-plus"></i>
                        <span>New village</span>
                    </button>
                </div>

                <%--                View list village--%>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Village name</th>
                        <th scope="col">District name</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${villages}">
                        <tr id="village-${item.villageId}">
                            <td><c:out value="${item.villageId}"/></td>
                            <td id="villageName-${item.villageId}"><c:out value="${item.villageName}"/></td>
                            <td class="${item.districtModel.districtId}"><c:out
                                    value="${item.districtModel.districtName}"/></td>
                            <td>
                                <span data-toggle="modal" data-target="#modalEdit">
                                    <button class="btn-edit btn btn-success btn-sm rounded-0"
                                            data-id="${item.villageId}"
                                            data-toggle="tooltip" data-placement="top" title="Edit"
                                    >
                                    <i class="fa fa-edit"></i>
                                    </button>
                                </span>
                                <span data-toggle="modal" data-target="#modalDelete">
                                    <button class="btn-delete btn btn-danger btn-sm rounded-0" type="button"
                                            data-toggle="tooltip" data-placement="top" title="Delete"
                                            data-id="${item.villageId}">
                                    <i class="fa fa-trash"></i>
                                </button>
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <%--<form action="/admin/village" method="get" id="formSearch">
                    <input type="hidden" value="1" name = "page" id = "page">
                </form>--%>

                <div class="box">
                    <ul id="pagination" class="pagination"></ul>
                    <div class="show"></div>
                </div>

                <!-- Modal add-->
                <div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalAddLabel">Add village</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/admin/village?method=POST" method="POST"
                                      onsubmit="return dataDistrictId()">
                                    <div class="form-group">
                                        <label for="villageName">Village name</label>
                                        <input type="text" class="form-control" id="villageName" name="villageName"
                                               placeholder="Village name" required>
                                        <div class="text-danger">${errors_add["villageName_error"]}</div>
                                    </div>
                                    <div class="form-group">
                                        <label for="district">District</label>
                                        <select id="district" class="form-control">
                                            <c:forEach var="item" items="${districts}">
                                                <option data-id="${item.districtId}">${item.districtName}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="text-danger">${errors_add["districtName_error"]}</div>
                                    </div>
                                    <input type="text" name="districtId" value="" hidden>
                                    <input type="hidden" value="${pageble.page}" name = "page">
                                    <button type="submit" class="btn btn-primary float-left">Add</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--                Model Edit--%>
                <div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalEditLabel">Edit village</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/admin/village?method=PUT" method="POST"
                                      onsubmit="return submitEditForm()">
                                    <div class="form-group">
                                        <label for="villageName">Village name</label>
                                        <input type="text" class="form-control village-name-edit" name="villageName"
                                               placeholder="Village name" required>
                                        <div class="text-danger">${errors_edit["villageName_error"]}</div>
                                    </div>
                                    <div class="form-group">
                                        <label for="district">District</label>
                                        <select class="form-control district-edit">
                                            <c:forEach var="item" items="${districts}">
                                                <option data-id="${item.districtId}">${item.districtName}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="text-danger">${errors_edit["districtName_error"]}</div>
                                    </div>
                                    <input type="text" name="districtId" value="" hidden>
                                    <input type="text" name="villageId" value="" hidden>
                                    <input type="hidden" value="${pageble.page}" name = "page">
                                    <button type="submit" class="btn btn-primary float-left">Edit</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--                Modal Delete--%>
                <div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="modalAddLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <p class="text-center">ARE YOU SURE ?</p>
                                <form action="/admin/village?method=DELETE" method="POST">
                                    <input type="text" name="villageId" value="" hidden>
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
    if (${not empty errors_add}) {
        $(window).on('load', function () {
            $('#modalAdd').modal('show');
        });
    }

    if (${not empty errors_edit}) {
        $(window).on('load', function () {
            $('#modalEdit').modal('show');
            editVillage(${village_id});
        });
    }

    $(() => {
        $('[data-toggle="tooltip"]').tooltip();
    });

    const dataDistrictId = () => {
        let id = $('#district option:selected').data('id');
        $('#modalAdd input[name="districtId"]').val(id);
        // alert($('#district option:selected').data('id'));
    }

    const submitEditForm = () => {
        let id = $('.district-edit option:selected').data('id');
        $('#modalEdit input[name="districtId"]').val(id);
        return true;
    }

    const editVillage = (id) => {
        $('#modalEdit input[name="villageId"]').val(id);

        <c:forEach var="item" items="${villages}">
        if (id === ${item.villageId}) {
            $('.village-name-edit').val('${item.villageName}');
            let queryName = '.district-edit option[data-id=' + ${item.districtModel.districtId} +']';
            console.log(queryName)
            $(queryName).attr('selected', 'selected');
        }
        </c:forEach>
    }

    $(document).ready(function () {
        $('navbar-search').attr('action','/admin/village');
        $('.btn-edit').click(function () {
            let id = $(this).data('id');
            editVillage(id);
        });

        $('.btn-delete').click(function (e) {
            let id = $(this).data('id');
            $('#modalDelete input[name="villageId"]').val(id);
        });
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
        click: function(options,$target) { // 点击按钮事件
            /*$("#page").val(options.current);
            // $("#maxPageItem").val(options.length);
            $("#formSearch").submit();*/
            window.location.href = '/admin/village?page=' + options.current;
        }
    });

</script>
</body>
</html>
