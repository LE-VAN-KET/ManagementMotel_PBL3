<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16/04/2021
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phong Tro Da Nang</title>
    <link href="${contextPath}/assets/css/home.css" rel="stylesheet">
    <%@include file="../../common/csslib.jsp"%>
</head>

<body class="hero-anime">
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
                                    <a class="nav-link" href="#"><i class="fas fa-home mr-1"></i></i>Home</a>
                                </li>
                                <li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                                        aria-haspopup="true" aria-expanded="false">Phòng Trọ</a>
                                    <div class="dropdown-menu border-0 bg-white">
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
                            <a type="button" href="dang-tin" class="animated-dang-tin btn btn-white ml-3"
                                style="color: #111;">
                                <span></span>
                                <span></span>
                                <span></span>
                                <span></span>
                                Đăng tin
                            </a>
                            <a type="button" href="#" class="singin_up text-decoration-none ml-lg-2"><i
                                    class="fas fa-sign-in-alt mr-1"></i>Singin</a>
                            <a type="button" href="#" class="singin_up text-decoration-none ml-2"><i
                                    class="fas fa-user-plus mr-1"></i>Singup</a>
                        </div>

                    </nav>
                </div>
            </div>
        </div>
    </div>
    <div class="section">
        <div class="container title m-0 mx-auto">
            <div class="row m-0">
                <div class="col-12">
                    <h4>
                        <span>W</span><span>e</span><span>l</span><span>c</span><span>o</span><span>m</span><span>e</span>
                        <span>P</span><span>h</span><span>ò</span><span>n</span><span>g</span>
                        <span>T</span><span>r</span><span>ọ</span>
                        <span>Đ</span><span>à</span>
                        <span>N</span><span>ẵ</span><span>n</span><span>g</span>
                    </h4>
                    <div class="box-search">
                        <div class="container">
                            <form action="" class="form-search row">
                                <div class="col-10 row">
                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="district">District</label>
                                        <select name="khu-vuc" id="district"
                                            class="form-control form-control-outline-none">
                                            <option value="all">All</option>
                                            <option value="lien-chieu">Liên chiểu</option>
                                            <option value="hai-chau">Hải Châu</option>
                                            <option value="thanh-khe">Thanh khê</option>
                                            <option value="hoa-vang">Hòa Vang</option>
                                            <option value="cam-le">Cẩm Lệ</option>
                                            <option value="son-tra">Sơn Trà</option>
                                            <option value="ngu-hanh-son">Ngũ Hành Sơn</option>
                                            <option value="huyen-hoang-sa">Huyện Hoàng Sa</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="village">Village</label>
                                        <select name="khu-vuc" id="village"
                                            class="form-control form-control-outline-none">
                                            <option value="all">All</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="square">Square metre</label>
                                        <select name="khu-vuc" id="square"
                                            class="form-control form-control-outline-none">
                                            <option value="all">All</option>
                                            <option value="1">Dưới 20m2</option>
                                            <option value="2">20m2 - 30m2</option>
                                            <option value="3">30m2 - 40m2</option>
                                            <option value="4">40m2 - 50m2</option>
                                            <option value="5">50m2 - 60m2</option>
                                            <option value="6">60m2 - 70m2</option>
                                            <option value="7">70m2 - 80m2</option>
                                            <option value="8">80m2 - 90m2</option>
                                            <option value="9">90m2 - 100m2</option>
                                            <option value="10">Trên 100m2</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="price">Price</label>
                                        <select name="khu-vuc" id="price"
                                            class="form-control form-control-outline-none">
                                            <option value="all">All</option>
                                            <option value="1">&lt; 500K</option>
                                            <option value="2">500K - 1 triệu</option>
                                            <option value="3">1 triệu - 1 triệu 5</option>
                                            <option value="4">1 triệu 5 - 2 triệu</option>
                                            <option value="5">2 triệu - 3 triệu</option>
                                            <option value="6">3 triệu - 4 triệu</option>
                                            <option value="7">4 triệu - 5 triệu</option>
                                            <option value="8">&gt; 5 triệu</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12 position-relative">
                                    <button type="submit" class="btn btn-primary btn-search"><i
                                            class="fab fa-searchengin mr-1"></i>Search</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container my-3"><span>Sắp xếp: </span>
        <a class="post-sort active" href="https://phongtro123.com/cho-thue-phong-tro">Mặc định</a>
        <a class="post-sort" href="https://phongtro123.com/cho-thue-phong-tro?orderby=moi-nhat">Mới nhất</a>
    </div>
    <!-- list post -->
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                    <div class="row mx-0 detail-list">
                        <div class="col-6 p-0">
                            <a href="#">
                                <img class="img-fluid lazyload"
                                    data-src="https://phongtrodn.com/wp-content/uploads/2021/03/IMG_5319.jpg"
                                    style="width: 100%; height: 200px" alt="...">
                            </a>
                        </div>
                        <div class="info-real col-6">
                            <h5>
                                <a href="#">
                                    Phòng trọ 15m2, đường Lê Sát, Nam Hòa Cường
                                </a>
                            </h5>
                            <p class="p-1 m-0">
                                <i class="fas fa-dollar-sign"></i>
                                Giá:
                                <strong>
                                    1 Triệu 800K ₫ / Tháng
                                </strong>
                            </p>
                            <p class="p-1 m-0"><i class="far fa-building"></i> Diện tích:
                                <strong>15m<sup>2</sup></strong></p>
                            <p class="p-1 m-0"><i class="fas fa-map-marker-alt"></i><span> Khu vực:
                                    <strong>Hải Châu</strong>
                                </span></p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                    <div class="row detail-list mx-0">
                        <div class="col-6 p-0">
                            <a href="#">
                                <img class="img-fluid lazyload"
                                    data-src="https://phongtrodn.com/wp-content/uploads/2021/03/IMG_5319.jpg"
                                    style="width: 100%; height: 200px" alt="...">
                            </a>
                        </div>
                        <div class="info-real col-6">
                            <h5>
                                <a href="#">
                                    Phòng trọ 15m2, đường Lê Sát, Nam Hòa Cường
                                </a>
                            </h5>
                            <p class="p-1 m-0">
                                <i class="fas fa-dollar-sign"></i>
                                Giá:
                                <strong>
                                    1 Triệu 800K ₫ / Tháng
                                </strong>
                            </p>
                            <p class="p-1 m-0"><i class="far fa-building"></i> Diện tích:
                                <strong>15m<sup>2</sup></strong></p>
                            <p class="p-1 m-0"><i class="fas fa-map-marker-alt"></i><span> Khu vực:
                                    <strong>Hải Châu</strong>
                                </span></p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- list area specific -->
    <section class="section-travel">
        <div class="container">
            <div class="title-area">
                <h4>
                    <span>Popular Locations</span>
                </h4>
            </div>
            <div class="section-content">
                <div class="row">
                    <div class="hot-place col-lg-12 col-md-12 col-sm-12">
                        <div class="row grid-destination">
                            <div class="grid-lg col-lg-8 col-md-6 col-sm-12">
                                <div class="grid-item">
                                    <a href="https://phongtrodn.com/lien-chieu" class="w-100">
                                        <img class="lazyload"
                                            data-src="https://phongtrodn.com/img/750x275/43/2018/06/lien-chieu.jpg"
                                            alt="Liên chiểu">
                                        <div class="backdrop-overlay">
                                            <div class="place-name">
                                                <h4 class="m-0">Liên chiểu</h4><span>&nbsp;</span>
                                            </div>
                                            <p class="place-info hidden-sm hidden-xs"><i class="fa fa-bed mr-1"
                                                    aria-hidden="true"></i>568 Tin đăng tại Liên chiểu</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="grid-lg col-lg-4 col-md-6 col-sm-12 row-mn">
                                <div class="grid-item">
                                    <a href="https://phongtrodn.com/hai-chau">
                                        <img class="lazyload"
                                            data-src="https://phongtrodn.com/img/360x260/43/2018/06/hanh-chinh.jpg"
                                            alt="Hải Châu">
                                        <div class="backdrop-overlay">
                                            <div class="place-name">
                                                <h4>Hải Châu</h4><span>&nbsp;</span>
                                            </div>
                                            <p class="place-info hidden-sm hidden-xs"><i class="fa fa-bed mr-1"
                                                    aria-hidden="true"></i>897 Tin đăng tại Hải Châu</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="grid-lg col-lg-4 col-md-6 col-sm-12 row-mn">
                                <div class="grid-item">
                                    <a href="https://phongtrodn.com/thanh-khe">
                                        <img class="lazyload"
                                            data-src="https://phongtrodn.com/img/360x260/43/2018/06/menu.jpg"
                                            alt="Thanh khê">
                                        <div class="backdrop-overlay">
                                            <div class="place-name">
                                                <h4>Thanh khê</h4><span>&nbsp;</span>
                                            </div>
                                            <p class="place-info hidden-sm hidden-xs"><i class="fa fa-bed mr-1"
                                                    aria-hidden="true"></i>565 Tin đăng tại Thanh khê</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="grid-lg col-lg-4 col-md-6 col-sm-12 row-mn">
                                <div class="grid-item">
                                    <a href="https://phongtrodn.com/hoa-vang">
                                        <img class="lazyload"
                                            data-src="https://phongtrodn.com/img/360x260/43/2018/06/hoa-vang.jpg"
                                            alt="Hòa Vang">
                                        <div class="backdrop-overlay">
                                            <div class="place-name">
                                                <h4>Hòa Vang</h4><span>&nbsp;</span>
                                            </div>
                                            <p class="place-info hidden-sm hidden-xs"><i class="fa fa-bed mr-1"
                                                    aria-hidden="true"></i>19 Tin đăng tại Hòa Vang</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="grid-lg col-lg-4 col-md-6 col-sm-12 row-mn">
                                <div class="grid-item">
                                    <a href="https://phongtrodn.com/cam-le">
                                        <img class="lazyload"
                                            data-src="https://phongtrodn.com/img/360x260/43/2018/06/1_Mq-Vr6OmIoy02I7TMwZzCQ.jpeg"
                                            alt="Cẩm Lệ">
                                        <div class="backdrop-overlay">
                                            <div class="place-name">
                                                <h4>Cẩm Lệ</h4><span>&nbsp;</span>
                                            </div>
                                            <p class="place-info hidden-sm hidden-xs"><i class="fa fa-bed mr-1"
                                                    aria-hidden="true"></i>616 Tin đăng tại Cẩm Lệ</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="grid-lg col-lg-4 col-md-6 col-sm-12 row-mn">
                                <div class="grid-item">
                                    <a href="https://phongtrodn.com/son-tra">
                                        <img class="lazyload"
                                            data-src="https://phongtrodn.com/img/360x260/43/2018/06/tour-ban-dao-son-tra.png"
                                            alt="Sơn Trà">
                                        <div class="backdrop-overlay">
                                            <div class="place-name">
                                                <h4>Sơn Trà</h4><span>&nbsp;</span>
                                            </div>
                                            <p class="place-info hidden-sm hidden-xs"><i class="fa fa-bed mr-1"
                                                    aria-hidden="true"></i>412 Tin đăng tại Sơn Trà</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="grid-lg col-lg-8 col-md-6 col-sm-12 row-mn">
                                <div class="grid-item">
                                    <a href="https://phongtrodn.com/ngu-hanh-son">
                                        <img class="lazyload"
                                            data-src="https://phongtrodn.com/img/750x275/43/2018/06/ngu-hanh-son.jpg"
                                            alt="Ngũ Hành Sơn">
                                        <div class="backdrop-overlay">
                                            <div class="place-name">
                                                <h4>Ngũ Hành Sơn</h4><span>&nbsp;</span>
                                            </div>
                                            <p class="place-info hidden-sm hidden-xs"><i class="fa fa-bed mr-1"
                                                    aria-hidden="true"></i>486 Tin đăng tại Ngũ Hành Sơn</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- footer -->
    <footer class="bg-dark">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6 col-xs-12 col-sm-12">
                    <h5>Absout Us</h5>
                    <ul>
                        <li>
                            <i class="far fa-address-card mr-1"></i>
                            <span>Address: </span>
                            <small>K8/10 Đồng kè, Hòa Khánh Bắc, Liên Chiểu, Đà Nẵng</small>
                        </li>
                        <li>
                            <i class="fas fa-phone-volume"></i>
                            <span>Phone: </span>
                            <small>0328560055 - 0906589823</small>
                        </li>
                        <li>
                            <i class="far fa-envelope"></i>
                            <span>Email: </span>
                            <small>anhle1512001@gmail.com</small>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-8 col-md-6 col-xs-12 col-sm-12 row">
                    <h5 class="col-12">Phòng Trọ, Nhà Trọ</h5>
                    <ul class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                        <li>
                            <a href="#">Phòng trọ Liên chiểu</a>
                        </li>
                        <li>
                            <a href="#">Phòng trọ Hải Châu</a>
                        </li>
                        <li>
                            <a href="#">Phòng trọ Cẩm Lệ</a>
                        </li>
                        <li>
                            <a href="#">Phòng trọ Hòa Vang</a>
                        </li>
                    </ul>
                    <ul class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                        <li>
                            <a href="#">Phòng trọ Thanh Khê</a>
                        </li>
                        <li>
                            <a href="#">Phòng trọ Sơn Trà</a>
                        </li>
                        <li>
                            <a href="#">Phòng trọ Ngũ Hành Sơn</a>
                        </li>
                    </ul>
                </div>
            </div>
            <p class="text-center py-2 m-0">@Copyright by Team Bker: Le van Ket && Nguyen tan Khanh</p>
        </div>
    </footer>
    <%@include file="../../common/javasciptlib.jsp"%>
    <script src="../../assets/javascript/home.js"></script>
</body>

</html>