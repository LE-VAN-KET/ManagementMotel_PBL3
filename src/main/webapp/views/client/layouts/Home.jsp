<%--
  Created by IntelliJ IDEA.
  User: Van Ket
  Date: 16/04/2021
  Time: 9:34 PM
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
    <title>Phong Tro Da Nang</title>
    <link href="${contextPath}/assets/css/home.css" rel="stylesheet">
    <%@include file="../../../common/csslib.jsp"%>
</head>

<body class="hero-anime">
<%--     block navigation--%>
    <%@include file="../components/navigation.jsp"%>
    <div class="section">
        <div class="container title m-0 mx-auto">
            <div class="row m-0">
                <div class="col-12">
                    <h4 class="text-white">
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
                                            <option value="all" data-id=0>All</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="village">Village</label>
                                        <select name="khu-vuc" id="village"
                                            class="form-control form-control-outline-none">
                                            <option value="all" data-id=0>All</option>
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
                <c:if test="${!POSTMODELS.isEmpty()}">
                    <c:forEach items="${POSTMODELS}" var="postModels">
                        <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                            <div class="row mx-0 detail-list">
                                <div class="col-6 p-0">
                                    <a href="#">
                                        <img class="img-fluid lazyload"
                                             data-src="https://drive.google.com/uc?export=view&id=${postModels.linkImages}"
                                             style="width: 100%; height: 200px" alt="...">
                                    </a>
                                </div>
                                <div class="info-real col-6">
                                    <h5>
                                        <a href="#">
                                            ${postModels.title}
                                        </a>
                                    </h5>
                                    <p class="p-1 m-0">
                                        <i class="fas fa-dollar-sign"></i>
                                        Giá:
                                        <strong>
                                            ${postModels.price} ₫ / Tháng
                                        </strong>
                                    </p>
                                    <p class="p-1 m-0"><i class="far fa-building"></i> Diện tích:
                                        <strong>${postModels.square}m<sup>2</sup></strong></p>
                                    <p class="p-1 m-0"><i class="fas fa-map-marker-alt"></i><span> Khu vực:
                                    <strong>${postModels.villageModel.villageName}-${postModels.villageModel.districtModel.districtName}</strong>
                                </span></p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

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
    <area class="container">
        <div class="row">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3833.8396925331685!2d108.1477255143661!3d16.07380644358591!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314218d68dff9545%3A0x714561e9f3a7292c!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBCw6FjaCBLaG9hIC0gxJDhuqFpIGjhu41jIMSQw6AgTuG6tW5n!5e0!3m2!1svi!2sus!4v1619884499308!5m2!1svi!2sus" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
        </div>
    </area>
    <!-- footer -->
    <%@include file="../components/footer.jsp"%>

    <!-- Back to top button -->
    <a id="back-to-top"></a>
    <%@include file="../../../common/javasciptlib.jsp"%>
    <script src="../../../assets/javascript/home.js"></script>
    <script>
        if (${!DISTRICTSMODELS.isEmpty()}) {
            // set list district option
            <c:forEach items="${DISTRICTMODELS}" var="district">
                $('#district').append("<option data-id = ${district.districtId} value='${district.districtName}'>${district.districtName}</option>");
            </c:forEach>
        }

        $(document).ready(function () {
            // set list village by district
            $("#district").change(() => {
                const districtId = $( "#district option:selected" ).data('id');
                $("#village option").remove();
                <c:forEach items="${DISTRICTMODELS}" var="district">
                    if(${district.districtId} === districtId) {
                        if (${!district.listVillage.isEmpty()}) {
                            <c:forEach items="${district.listVillage}" var="village">
                            $('#village').append("<option data-id = ${village.villageId} " +
                                "value='${village.villageName}'>${village.villageName}</option>");
                            </c:forEach>
                        } else {
                            $('#village').append("<option value='All' data-id = 0 >All</option>");
                        }
                    }
                </c:forEach>
                if (districtId == 0) {
                    $('#village').append("<option value='All' data-id=0>All</option>");
                }
            })
        })
    </script>
</body>

</html>