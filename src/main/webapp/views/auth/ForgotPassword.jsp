<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09/04/2021
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot password</title>
    <link href="${contextPath}/assets/css/forgot-password.css" rel="stylesheet">
    <link href="${contextPath}/assets/fonts/material-icon/css/material-design-iconic-font.min.css">
    <%@include file="../../common/csslib.jsp"%>
</head>
<body>
    <div class="container padding-bottom-3x mb-2 mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10">
                <div class="forgot">
                    <h5>Forgot your password?</h5>
                </div>
                <form action="/forgot-password" class="card mt-4" method="post">
                    <div class="card-body">
                        <div class="form-group">
                            <label for="email">Enter your email address</label>
                            <input class="form-control" type="email" id="email" name = "email" autofocus=""  required>
                            <small class="form-text text-muted">Enter the email address you used during the registration. Then we'll email a link to this address.</small>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="/login" class="btn btn-danger">Back to Login</a>
                        <button class="btn btn-success" type="submit">Get New Password</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="../../common/javasciptlib.jsp"%>
    <script>
        $(document).ready(() => {
            if ("${message}" != "") {
                toastr.error('${message}', 'Login falied!');
            }
        })
    </script>
</body>
</html>
