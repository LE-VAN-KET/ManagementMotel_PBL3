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
    <title>Đăng tin mới</title>
    <link href="${contextPath}/assets/css/home.css" rel="stylesheet">
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

    <!-- Back to top button -->
    <a id="back-to-top"></a>
    <%@include file="../../../common/javasciptlib.jsp"%>
    <script src="../../../assets/javascript/home.js"></script>
    <script src="../../../assets/javascript/ckeditor.js"></script>
    <script>
        if (${ACCOUNTMODEL != null}) {
            $("input[name = 'userId']").val(${ACCOUNTMODEL.user.userId});
            $("a.singin_up").remove();
        }

        if (${!DISTRICTSMODELS.isEmpty()}) {
            // set list district option
            $("#district option").remove();
            <c:forEach items="${DISTRICTMODELS}" var="district">
            $('#district').append("<option data-id = ${district.districtId} value='${district.districtName}'>${district.districtName}</option>");
            </c:forEach>
        }

        $(document).ready(function () {
            $('#loading').addClass("show");
            setTimeout(function () {
                $('#loading').removeClass("show");
                $('#loading').addClass("hidden");
            }, 2000);
            // set list village by district
            $("#district").change(() => {
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
            })

            $("#postForm").submit(() => {
                $("input[name='villageId']").val($("#village option:selected").data('id'));
            })
        })
    </script>
    <script src="../../../assets/javascript/newpost.js"></script>
</body>
</html>
