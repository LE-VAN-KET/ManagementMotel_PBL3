<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30/05/2021
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit post</title>
    <%@include file="../../../common/csslib.jsp"%>
</head>
<body class="hero-anime" style="background-color: #f5f5f5;">
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
            <%@include file="../components/sidebarPersonalPost.jsp"%>
            <div class="col-lg-9 col-md-12 col-xs-12 col-sm-12 my-1">
                <div class="title-section">
                    <h4>Update tin</h4>
                </div>
                <form action="/post/update/${POSTMODEL.postId}" method="POST" id="postForm" role="form"
                      accept-charset="utf-8"
                      enctype="multipart/form-data" onsubmit="return isEmptyField()">
                    <div class="form-group">
                        <label for="title">Tiêu đề: </label><br>
                        <small>Ví dụ: <i class="fas fa-check mx-1 text-primary"></i>Cho thuê nhà nguyên căn
                            mới xây đường trường chinh </small>
                        <input type="text" name="title" class="form-control" id="title"
                               placeholder="Tiêu đề" required="required" value="${POSTMODEL.title}">
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                            <div class="form-group">
                                <label for="price">Giá thuê / Tháng: </label><br>
                                <small>Đơn vị VNĐ (Ví dụ giá 2 triệu bạn cần nhập: <i
                                        class="fas fa-check mx-1 text-primary"></i>2000)</small>
                                <div class="input-group">
                                    <input type="number" class="form-control" name="price" id="price"
                                           placeholder="Giá thuê" required min="0" max="999999999999"
                                            value="${POSTMODEL.price}">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">,000 ₫ / Tháng</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                            <div class="form-group">
                                <label for="square">Diện tính: </label><br>
                                <small>Nhập chính xác diện tích nha! Đơn vị m<sup>2</sup></small>
                                <div class="input-group">
                                    <input type="number" class="form-control" name="square" id="square"
                                           placeholder="Diện tích" required min="0" value="${POSTMODEL.square}">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">m<sup>2</sup></div>
                                    </div>
                                </div>
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
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address">Địa chỉ cụ thể: </label><br>
                        <input type="text" class="form-control pac-target-input" name="address"
                               autocomplete="off" id="address" placeholder="Số nhà, tên đường..."
                               required="required" value="${POSTMODEL.address}">
                    </div>
                    <div class="form-group">
                        <label for="description">Content</label>
                        <div class="error-msg error-description">
                            <i class="fa fa-times-circle"></i>
                            This field is required.
                        </div>
                        <textarea class="form-control editor" rows="50" name="description" id="description"
                                  placeholder="Content's name...">${POSTMODEL.description}</textarea>
                    </div>
                    <input name="villageId" type="number" hidden>
                    <div class="form-group">
                        <label for="files">Hình ảnh: </label>
                        <small>Bạn phải nhập tối thiểu 1 hình ảnh, hình ảnh càng nhiều độ tin cậy càng
                            cao</small>
                        <div class="img-list-img row"></div>
                        <div class="error-msg error-file">
                            <i class="fa fa-times-circle"></i>
                            Field file not empty.
                        </div>
                        <label for="files" class="add-img">
                            <span>+</span>
                            <input type="file" name="file"  id="files" multiple="multiple"
                                   accept=".png, .jpg, .jpeg, .gif">
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary float-right">Update post</button>
                </form>
                <div class="widget switch">
                    <fieldset>
                        <legend>Trọ này được thuê chưa?</legend>
                        <span>
                            <c:if test="${POSTMODEL.statusRental == true}">
                                <input type="checkbox" id="chsw1" name="status_rent" value="1" checked>
                                <label for="chsw1" class="mb-0" id="btn_status"></label>
                                <span class="_status_rental text-primary">Thuê rồi</span>
                            </c:if>
                            <c:if test="${POSTMODEL.statusRental == false}">
                                <input type="checkbox" id="chsw1" name="status_rent" value="0">
                                <label for="chsw1" class="mb-0" id="btn_status"></label>
                                <span class="_status_rental text-primary">Chưa thuê</span>
                            </c:if>
                        </span>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
    <%@include file="../components/footer.jsp"%>

    <%@include file="../../../common/javasciptlib.jsp"%>
    <script src="../../../assets/javascript/template/ckeditor.js"></script>
    <script>
        $(document).ready(function () {
            $('#loading').addClass("show");
            setTimeout(function () {
                $('#loading').removeClass("show");
                $('#loading').addClass("hidden");
            }, 1500);

            if (${!ERRORS.isEmpty()}) {
                <c:forEach items="${ERRORS}" var="error">
                    toastr.error('${error}', 'Error!');
                </c:forEach>
            }

            if (${!DISTRICTSMODELS.isEmpty()}) {
                // set list district option
                $("#district option").remove();
                <c:forEach items="${DISTRICTMODELS}" var="district">
                    if (${POSTMODEL.villageModel.districtModel.districtId} == ${district.districtId}) {
                        $('#district').append("<option data-id = ${district.districtId} " +
                            "value='${district.districtName}' selected>${district.districtName}</option>");
                    } else {
                        $('#district').append("<option data-id = ${district.districtId} " +
                            "value='${district.districtName}'>${district.districtName}</option>");
                    }
                </c:forEach>
            }

            // set list village by district
            $("#district").change(() => {
                setupVillage();
            })

            const setupVillage = () => {
                const districtId = $( "#district option:selected" ).data('id');
                $("#village option").remove();
                <c:forEach items="${DISTRICTMODELS}" var="district">
                if (${district.districtId} === districtId) {
                    if (${!district.listVillage.isEmpty()}) {
                        <c:forEach items="${district.listVillage}" var="village">
                        if (${POSTMODEL.villageModel.villageId} == ${village.villageId}) {
                            $('#village').append("<option data-id = ${village.villageId} " +
                                "value='${village.villageName}' selected>${village.villageName}</option>");
                        } else {
                            $('#village').append("<option data-id = ${village.villageId} " +
                                "value='${village.villageName}'>${village.villageName}</option>");
                        }
                        </c:forEach>
                    }
                }
                </c:forEach>
            }
            setupVillage();

            if (${!IMAGES.isEmpty()}) {
                <c:forEach items="${IMAGES}" var="image">
                    $(".img-list-img").append('<div class="col-2"><img class="img-thumbnail lazyload"' +
                        'data-src="https://drive.google.com/uc?export=view&id=${image}">' +
                        '<a class="delete" data-id="${image}">' +
                        '<i class="far fa-trash-alt"></i></a></div>');
                </c:forEach>

                $("a.delete").click(function () {
                    let id = $(this).data("id");
                    if (typeof (id) !== "undefined" || id !== "") {
                        // console.log("ajax delete " + id);
                        $.ajax({
                            type: "POST",
                            url : "/post/delete-image/${POSTMODEL.postSlug}",
                            data: {fileId: id},
                            success: function(responseText){
                                toastr.success("Delete file success");
                            }
                        });
                    }
                    $(this).parent(".col-2").remove();
                })
            }

            $("#postForm").submit((e) => {
                $("input[name='villageId']").val($("#village option:selected").data('id'));
            })

            $("#btn_status").click(function (e) {
                e.preventDefault();
                let statusRental = $("input:checkbox").prop("checked") ? true: false;
                $.ajax({
                    type: "POST",
                    url : "/post/update-status-rental/${POSTMODEL.postId}",
                    data: {statusRental: statusRental},
                    success: function (result) {
                        if (result.success == "OK") {
                            if (result.statusRental == true) {
                                $("#chsw1").prop("checked", true);
                                $("._status_rental").text("Thuê rồi");
                            } else {
                                $("#chsw1").prop("checked", false);
                                $("._status_rental").text("Chưa thuê");
                            }
                            toastr.success("update status rental success");
                        } else {
                            toastr.error(result.success);
                        }
                    }
                })
            })
        });
    </script>
    <script src="../../../assets/javascript/newpost.js"></script>
    <%@include file="../components/notifyMessage.jsp"%>
</body>
</html>
