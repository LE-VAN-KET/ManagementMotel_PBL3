<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18/05/2021
  Time: 8:56 PM
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
    <title>Edit profile</title>
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

<%--    edit profile--%>
    <%@include file="../components/editProfile.jsp"%>
    <!-- footer -->
    <%@include file="../components/footer.jsp"%>

    <%@include file="../../../common/javasciptlib.jsp"%>
    <script>
        $(document).ready(function () {
            if ("${message}" != "" && "${alert}" == "danger") {
                toastr.info('${message}', 'falied!');
            }

            if (${!ERRORS.isEmpty()}) {
                <c:forEach items="${ERRORS}" var="error">
                toastr.error('${error}', 'Error!');
                </c:forEach>
            }

            $('#loading').addClass("show");
            setTimeout(function () {
                $('#loading').removeClass("show");
                $('#loading').addClass("hidden");
            }, 2000);

            $("#changePassword").click(function () {
                $("input[name='password']").prop("disabled", false).focus();
            })

            $("#changeUsername").click(function () {
                $("input[name='username']").prop("disabled", false).focus();
            })

            $("#changeEmail").click(function () {
                $("input[name='email']").prop("disabled", false).focus();
            })
        })
    </script>
</body>
</html>
