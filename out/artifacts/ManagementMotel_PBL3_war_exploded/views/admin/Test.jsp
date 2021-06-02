<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty success}">
    <span>${success}</span>
</c:if>
<form action="/admin/test" method="POST">
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" name="username" >
        <c:if test="${not empty username_error}">
            <span>${username_error}</span>
        </c:if>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" >
        <c:if test="${not empty password_error}">
            <span>${password_error}</span>
        </c:if>
    </div>
    <div class="form-group">
        <label for="phone">Phone</label>
        <input type="text" class="form-control" id="phone" name="phone" >
        <c:if test="${not empty phone_error}">
            <span>${phone_error}</span>
        </c:if>
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" >
        <c:if test="${not empty email_error}">
            <span>${email_error}</span>
        </c:if>
    </div>
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" >
<%--        <c:if test="${not empty errors.get['name']}">--%>
            <div>${errors["nik"]}</div>
        <%--</c:if>--%>
    </div>
    <button type="submit" class="btn btn-primary float-left">Login</button>
</form>
</body>
</html>
