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
    <title>Register</title>
    <link href="${contextPath}/assets/css/register.css" rel="stylesheet">
    <%@include file="../../common/csslib.jsp"%>
</head>

<body>
    <!-- Sign up form -->
    <div class="container h-100 w-100 p-0 d-flex align-items-center justify-content-center pt-lg-4">
        <div class="signup-content row col-lg-8 m-0">
            <div class="row col-lg-6 m-0">
                <h2 class="form-title w-100">Sign up</h2>
                <form action="/register" method="POST" class="register-form w-100" id="register-form"
                      onsubmit="return validateUserPW()">
                    <div class="form-group mt-2 mb-1">
                        <label for="username" ><i class="far fa-user text-primary"></i></label>
                        <input type="text" name="username" id="username" placeholder="User Name such as vanhoang" autofocus
                               required pattern="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$"/>
                    </div>
                    <small class="text-muted">Username must be at least 5 characters and contain no special characters(*)</small>
                    <div class="form-group mt-2 mb-1">
                        <label for="email"><i class="fas fa-envelope-open text-primary"></i></label>
                        <input type="email" name="email" id="email" placeholder="Your Email" required />
                    </div>
                    <div class="form-group mt-2 mb-1">
                        <label for="pass"><i class="fas fa-lock text-primary"></i></label>
                        <input type="password" name="password" id="pass" placeholder="Password"
                               required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"/>
                    </div>
                    <small class="text-muted">Password must minimum 8 characters, at least one uppercase letter,
                        one lowercase letter and one number(*)</small>
                    <div class="form-group mt-2 mb-1">
                        <label for="repeat-password"><i class="fas fa-redo text-primary"></i></label>
                        <input type="password" name="repeat-password" id="repeat-password" placeholder="Repeat your password"
                            required />
                    </div>
                    <div class="wrapper p-0 m-0">
                        <div class="box d-flex justify-content-center align-items-center">
                            <c:forEach items="${ROLEMODEL}" var="role">
                                <c:if test="${role.roleName eq 'USER'}">
                                    <div class="mh-100 my-2 w-50 m-2">
                                        <input type="radio" name="select" id="value-2" data-id="${role.roleId}">
                                        <label for="value-2" class="value-2 h-100 w-100 m-0 py-2">
                                            <div class="select-dots"></div>
                                            <div class="text">Renter</div>
                                        </label>
                                    </div>
                                </c:if>
                                <c:if test="${role.roleName eq 'LANDLORD'}">
                                    <div class = "mh-100 my-2 w-50 m-2">
                                        <input type="radio" name="select" id="value-1" checked data-id="${role.roleId}">
                                        <label for="value-1" class="value-1 h-100 w-100 m-0 py-2">
                                            <div class="select-dots"></div>
                                            <div class="text">Landlord</div>
                                        </label>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="number" name="roleId" hidden>

                    <div class="form-group">
                        <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                        <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all
                            statements in <a href="#" class="term-service">Terms of service</a></label>
                    </div>
                    <button type="submit" name="signup" id="signup"
                        class="form-submit btn btn-primary float-right mb-3">Register</button>
                    <a href="/login" class="d-block text-decoration-none text-center m-3">I am already Account?</a>
                </form>
            </div>
            <div class="row col-lg-6 m-0">
                <figure class="d-flex justify-content-center align-items-center">
                    <img class="img-thumbnail lazyload" data-src="${contextPath}/assets/images/signup-image.jpg"
                         alt="sing up image" style="width: 100%">
                </figure>
            </div>
        </div>
    </div>
    <%@include file="../../common/javasciptlib.jsp"%>
    <script>
        if (${!ERRORS.isEmpty()}) {
            <c:forEach items="${ERRORS}" var="error">
                toastr.error('${error}', 'Error!');
            </c:forEach>
        }

        const validateUserPW = () => {
            let password = $("input[name='password']").val();
            let repeatPass = $("input[name='repeat-password']").val();

            if (password != repeatPass) {
                toastr.error('Repeat password invalid', 'Error');
                return false;
            }
            return true;
        }

        $(document).ready(() => {

            $('#register-form').submit(() => {
                let roleId = $("input[name = 'select']:checked").data('id');
                $("input[name = 'roleId']").val(roleId);
                return;
            })
        })
    </script>
</body>
</html>