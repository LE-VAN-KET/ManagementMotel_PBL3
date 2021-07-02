<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--// content navigation--%>
<div class="navigation-wrap bg-light start-header start-style p-0 bg-white">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="navbar navbar-expand-md navbar-light p-0">

                    <a class="navbar-brand p-0" href="#">
                        <img data-src="${contextPath}/assets/images/logo-pbl3.png" class="lazyload m-0" alt="...">
                    </a>

                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto py-4 py-md-0">
                            <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4 active">
                                <a class="nav-link" href="/home"><i class="fas fa-home mr-1"></i></i>Home</a>
                            </li>

                            <li class="nav-item dropdown dropdown-slide dropdown-hover pl-4 pl-md-0 ml-0 ml-md-4">
                                <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Phòng Trọ
                                    <i class="fas fa-caret-down ml-1"></i>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <a class="dropdown-item" href="#">Liên Chiểu</a>
                                    <a class="dropdown-item" href="#">Thanh Khê</a>
                                    <a class="dropdown-item" href="#">Sơn Trà</a>
                                    <a class="dropdown-item" href="#">Hải Châu</a>
                                    <a class="dropdown-item" href="#">Cẩm Lệ</a>
                                </div>
                            </li>

                            <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                                <a class="nav-link" href="#">Contact</a>
                            </li>
                        </ul>

                        <c:if test="${'LANDLORD'.equals(ACCOUNTMODEL.user.roleModel.roleName)}">
                            <a type="button" href="/post" class="animated-dang-tin btn btn-white ml-3"
                               style="color: #111;">
                                <span></span>
                                <span></span>
                                <span></span>
                                <span></span>
                                Đăng tin
                            </a>
                        </c:if>
                        <c:if test="${ACCOUNTMODEL != null}">
                            <a type="button" class="message ml-0 ml-md-4 btn btn-light d-inline-block" href="/message" data-toggle="tooltip"
                               data-placement="bottom" title="Message">
                                <i class="far fa-comment-dots"></i>
                            </a>
                            <div class="dropdown dropdown-slide dropdown-hover ml-0 ml-md-4 d-inline-block">
                                <a type="button" class="dropdown-toggle setting btn btn-light" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                                        <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
                                        <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
                                    </svg>
                                        <%--                                        Setting--%>
                                </a>
                                <div class="dropdown-menu dropdown-setting" aria-labelledby="navbarDropdownMenuLink">
                                    <c:if test="${'LANDLORD'.equals(ACCOUNTMODEL.user.roleModel.roleName)}">
                                        <a class="dropdown-item" href="/personal-post">
                                            <i class="far fa-list-alt mr-1"></i>Quản lý bài đăng</a>
                                    </c:if>
                                    <a class="dropdown-item" href="/edit-profile">
                                        <i class="far fa-user-circle mr-1"></i>Tài khoản</a>
                                    <a class="dropdown-item" href="/logout">
                                        <i class="fas fa-power-off mr-1"></i>Log out</a>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${ACCOUNTMODEL == null}">
                            <a type="button" href="/login" class="singin_up text-decoration-none ml-lg-2"><i
                                    class="fas fa-sign-in-alt mr-1"></i>Singin</a>
                            <a type="button" href="/register" class="singin_up text-decoration-none ml-2"><i
                                    class="fas fa-user-plus mr-1"></i>Singup</a>
                        </c:if>
                    </div>

                </nav>
            </div>
        </div>
    </div>
</div>


