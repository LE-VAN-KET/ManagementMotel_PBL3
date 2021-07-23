<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link href="${contextPath}/assets/css/login.css" rel="stylesheet">
    <link href="${contextPath}/assets/css/backToTop.css" rel="stylesheet">
    <link href="${contextPath}/assets/css/fonts.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/assets/css/template/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet" />
    <link href="${contextPath}/assets/css/template/toastr.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body class="dark-edition">
    <div id="particles-js"></div>
    <div class="container">
        <div class="row d-flex align-items-center justify-content-center h-100">
            <div class="col-lg-4 col-md-8 col-xs-8 col-sm-8 card-login">
                <div class="card">
                    <div class="card-header login text-white text-center">
                        <div class="photo"></div>
                        <span class="text-dark">Please Sign in </span>
                    </div>

                    <div class="card-body">
                        <form action="/login" method="POST" id="login-form" onsubmit="return validateForm()">
                            <input type="hidden" name="_CSRFToken" value = "${sessionScope.CSRFToken}">
                            <div id="u" class="form-group">
                                <input class="form-control" spellcheck="false" id="username" type="text" name="username"
                                       size="18" alt="login" autofocus=''>
                                <span class="form-highlight"></span>
                                <span class="form-bar"></span>
                                <label for="username" class="float-label">Username or email</label>
                                <erroru>
                                    Email is required
                                    <i>
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                            <path d="M0 0h24v24h-24z" fill="none"></path>
                                            <path d="M1 21h22l-11-19-11 19zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z"></path>
                                        </svg>
                                    </i>
                                </erroru>
                            </div>
                            <div id="p" class="form-group">
                                <input class="form-control" spellcheck="false" id="password" type="password"
                                       name="password" size="18" alt="login">
                                <span class="form-highlight"></span>
                                <span class="form-bar"></span>
                                <label for="password" class="float-label">Password</label>
                                <errorp>
                                    Password is required
                                    <i>
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                            <path d="M0 0h24v24h-24z" fill="none"></path>
                                            <path d="M1 21h22l-11-19-11 19zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z"></path>
                                        </svg>
                                    </i>
                                </errorp>
                            </div>
                            <div class="checkbox">
                                <label><input class="mx-2" name="remember" type="checkbox"
                                              value="Remember Me" />Remember Me</label>
                            </div>
                            <button class="btn btn-login float-right border-0 btn-primary" type="submit" id="submit">
                                <span>Sign In</span>
                            </button>
                        </form>
                    </div>
                    <div class = "text-center pb-2">
                        <small class = "">Don't have an account?</small>
                        <a href="/register" class="text-primary font-weight-bold text-decoration-none" style="font-size: 14px">
                            Register Account
                        </a>
                    </div>
                    <a href="/forgot-password" class="text-center text-primary pb-2 text-decoration-none" style="font-size: 15px">
                        I forgot password
                    </a>
                </div>
            </div>
        </div>
    </div>

    <script src="${contextPath}/assets/javascript/particle.js"></script>
    <script src="${contextPath}/assets/javascript/particle.data.js"></script>
    <script src="${contextPath}/assets/javascript/validateLogin.js"></script>
    <%@include file="../../common/javasciptlib.jsp"%>
    <script>
        $(document).ready(() => {
            if ("${message}" != "" && "${alert}" == "danger") {
                toastr.error('${message}', 'Login falied!');
            }

            if ("${message}" != "" && "${alert}" == "success") {
                toastr.success('${message}', 'Notification');
            }
        });
    </script>
</body>
</html>