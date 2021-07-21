<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21/07/2021
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${ACCOUNTMODEL != null}">
    <script src="${contextPath}/assets/javascript/notificationMessage.js"></script>
    <script>
        $(document).ready(function () {
            let userId = ${ACCOUNTMODEL.user.userId};
            notificationMsg(userId);
        })
    </script>
</c:if>
