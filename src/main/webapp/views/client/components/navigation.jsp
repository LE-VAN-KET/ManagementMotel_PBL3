<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--// content navigation--%>
<div class="navigation-wrap bg-light start-header start-style p-0 bg-white">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="navbar navbar-expand-md navbar-light py-0">

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
                            <c:if test="${ACCOUNTMODEL != null}">
                                <li class="nav-item dropdown dropdown-slide dropdown-hover pl-4 pl-md-0 ml-0 ml-md-4">
                                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Setting
                                        <i class="fas fa-user-cog ml-1 text-primary"></i>
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                        <a class="dropdown-item" href="/personal-post">Quản lý bài đăng</a>
                                        <a class="dropdown-item" href="/edit-profile">Tài khoản</a>
                                        <a class="dropdown-item" href="#">Log out</a>
                                    </div>
                                </li>
                            </c:if>
                        </ul>

                        <a type="button" href="/post" class="animated-dang-tin btn btn-white ml-3"
                           style="color: #111;">
                            <span></span>
                            <span></span>
                            <span></span>
                            <span></span>
                            Đăng tin
                        </a>
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


