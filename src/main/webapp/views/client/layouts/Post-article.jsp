<%--
  Created by IntelliJ IDEA.
  User: Van Ket
  Date: 03/05/2021
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng tin mới</title>
    <%@include file="../../../common/csslib.jsp"%>
</head>
<body class="hero-anime">
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

    <%--section new post--%>
    <%@include file="../components/formNewPost.jsp"%>
    <!-- footer -->
    <%@include file="../components/footer.jsp"%>

    <%@include file="../../../common/javasciptlib.jsp"%>
    <script src="../../../assets/javascript/template/ckeditor.js"></script>
    <script>
        if (${!ERRORS.isEmpty()}) {
            <c:forEach items="${ERRORS}" var="error">
            toastr.error('${error}', 'Error!');
            </c:forEach>
        }

        <%--if (${ACCOUNTMODEL != null}) {--%>
        <%--    $("input[name = 'userId']").val(${ACCOUNTMODEL.user.userId});--%>
        <%--    // $("a.singin_up").remove();--%>
        <%--}--%>



        $('#loading').addClass("show");
        setTimeout(function () {
            $('#loading').removeClass("show");
            $('#loading').addClass("hidden");
        }, 2000);

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
                const districtId = $( "#district option:selected" ).data('id');
                $("#village option").remove();
                <c:forEach items="${DISTRICTMODELS}" var="district">
                if (${district.districtId} === districtId) {
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
    <script src="../../../assets/javascript/newpost.js"></script>
</body>
</html>
