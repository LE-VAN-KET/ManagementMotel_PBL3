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
    <link rel="stylesheet" href="../../assets/css/newPost.css">

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

                <div class="content">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-8 offset-lg-2 <%--col-md-9 col-xs-12 col-sm-12 my-1--%>">
                                <div class="title-section">
                                    <h4>Đăng tin mới</h4>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <form action="/admin/new-post" method="POST" id="postForm" role="form"
                                              enctype="multipart/form-data">
                                            <div class="form-group">
                                                <label for="title">Tiêu đề: </label><br>
                                                <small>Ví dụ: <i class="fas fa-check mx-1 text-primary"></i>Cho thuê nhà
                                                    nguyên căn
                                                    mới xây đường trường chinh </small>
                                                <input type="text" name="title" class="form-control" id="title"
                                                       placeholder="Tiêu đề" required>
                                                <div class="text-danger">${errors["title_error"]}</div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                                    <div class="form-group">
                                                        <label for="price">Giá thuê / Tháng: </label><br>
                                                        <small>Đơn vị VNĐ (Ví dụ giá 2 triệu bạn cần nhập: <i
                                                                class="fas fa-check mx-1 text-primary"></i>2000)</small>
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" name="price"
                                                                   id="price"
                                                                   placeholder="Giá thuê" required min="0"
                                                                   max="999999999999">
                                                            <div class="input-group-prepend">
                                                                <div class="input-group-text">,000 ₫ / Tháng</div>
                                                            </div>
                                                        </div>
                                                        <div class="text-danger">${errors["price_error"]}</div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                                    <div class="form-group">
                                                        <label for="square">Diện tính: </label><br>
                                                        <small>Nhập chính xác diện tích nha! Đơn vị
                                                            m<sup>2</sup></small>
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" name="square"
                                                                   id="square"
                                                                   placeholder="Diện tích" required min="0">
                                                            <div class="input-group-prepend">
                                                                <div class="input-group-text">m<sup>2</sup></div>
                                                            </div>
                                                        </div>
                                                        <div class="text-danger">${errors["square_error"]}</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                                    <div class="form-group">
                                                        <label for="district">District</label>
                                                        <select id="district"
                                                                class="form-control form-control-outline-none">
                                                            <option value="all">All</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                                    <div class="form-group">
                                                        <label for="village">Village</label>
                                                        <select id="village"
                                                                class="form-control form-control-outline-none">
                                                            <option value="all">All</option>
                                                        </select>
                                                        <div class="text-danger">${errors["village_error"]}</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="address">Địa chỉ cụ thể: </label><br>
                                                <input type="text" class="form-control pac-target-input"
                                                       name="address"
                                                       autocomplete="off" id="address"
                                                       placeholder="Số nhà, tên đường..."
                                                       required>
                                                <div class="text-danger">${errors["address_error"]}</div>
                                            </div>
                                            <div class="form-group">
                                                <label for="description">Content</label>
                                                <textarea class="form-control editor" rows="50" name="description"
                                                          id="description"
                                                          placeholder="Content's name..."></textarea>
                                                <div class="text-danger">${errors["description_error"]}</div>
                                            </div>
                                            <input name="villageId" type="number" hidden>
                                            <%--                            <input name="userId" type="number" hidden>--%>
                                            <div class="form-group">
                                                <label for="files">Hình ảnh: </label>
                                                <small>Bạn phải nhập tối thiểu 1 hình ảnh, hình ảnh càng nhiều độ
                                                    tin
                                                    cậy càng
                                                    cao</small>
                                                <div class="img-list-img row"></div>
                                                <label for="files" class="add-img">
                                                    <span>+</span>
                                                    <input type="file" name="file" id="files" multiple="multiple"
                                                           accept=".png, .jpg, .jpeg, .gif" required>
                                                </label>
                                            </div>
                                            <button type="submit" class="btn btn-primary float-right">Đăng tin ngay
                                            </button>
                                        </form>
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

<button href="location.href='/admin/post?id=1"></button>


<%@include file="../../common/javasciptlib.jsp" %>
<script src="../../assets/javascript/admin.home.js"></script>
<script src="../../assets/javascript/ckeditor.js"></script>
<script>
    $(document).ready(function () {
        if (${!DISTRICTSMODELS.isEmpty()}) {
            // set list district option
            $("#district option").remove();
            <c:forEach items="${DISTRICTMODELS}" var="district">
            $('#district').append("<option data-id = ${district.districtId} value='${district.districtName}'>${district.districtName}</option>");
            </c:forEach>
        }
        // set list village by district
        $("#district").change(() => {
            setupVillage();
        })
        const setupVillage = () => {
            const districtId = $("#district option:selected").data('id');
            $("#village option").remove();
            <c:forEach items="${DISTRICTMODELS}" var="district">
            if (districtId === ${district.districtId}) {
                if (${!district.listVillage.isEmpty()}) {
                    <c:forEach items="${district.listVillage}" var="village">
                    $('#village').append("<option data-id = ${village.villageId} " +
                        "value='${village.villageName}'>${village.villageName}</option>");
                    </c:forEach>
                }
            }
            </c:forEach>
        }
        setupVillage();
        $("#postForm").submit((e) => {
            $("input[name='villageId']").val($("#village option:selected").data('id'));
        })
    })
</script>
<script src="../../assets/javascript/newPost.js"></script>
</body>
</html>

