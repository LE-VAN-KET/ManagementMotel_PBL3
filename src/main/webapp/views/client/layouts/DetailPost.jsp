<%--
  Created by IntelliJ IDEA.
  User: Van Ket
  Date: 12/05/2021
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>

<head>
    <title>Title</title>
    <%@include file="../../../common/csslib.jsp"%>
    <link rel="stylesheet" href="${contextPath}/assets/css/slideShowImages.css">
    <link rel="stylesheet" href="${contextPath}/assets/css/detailPost.css">
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

    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-0 col-sm-0 col-xs-0">
                <div class="position-fixed sidebar-post">
                    <div class="title-sidebar">
                        <i class="fas fa-list-ul" class="bg-primary mr-2"></i>
                        <span>Phòng Trọ</span>
                    </div>
                    <ul class="navbar-nav bg-white">
                        <li class="nav-item py-1">
                            <a class="nav-link " href="/quan-li-dang-tin">
                                <i class="fas fa-search-location mr-1"></i>
                                <span>Phòng trọ tại Liên Chiểu</span>
                            </a>
                        </li>
                        <li class="nav-item py-1">
                            <a class="nav-link " href="/edit-user-info">
                                <a class="nav-link " href="/quan-li-dang-tin">
                                    <i class="fas fa-search-location mr-1"></i>
                                    <span>Phòng trọ tại Hải Châu</span>
                                </a>
                            </a>
                        </li>
                        <li class="nav-item py-1">
                            <a class="nav-link" href="https://phongtro123.com/lien-he">
                                <a class="nav-link " href="/quan-li-dang-tin">
                                    <i class="fas fa-search-location mr-1"></i>
                                    <span>Phòng trọ tại Thanh Khê</span>
                                </a>
                            </a>
                        </li>
                        <li class="nav-item py-1">
                            <a class="nav-link" href="https://phongtro123.com/thoat">
                                <a class="nav-link " href="/quan-li-dang-tin">
                                    <i class="fas fa-search-location mr-1"></i>
                                    <span>Phòng trọ tại Cẩm Lệ</span>
                                </a>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                <h1 class="title-post">
                    Cho thuê trọ gần hòa khánh
                </h1>
                <div class="address">
                    <i class="fas fa-home mr-2"></i>
                    <span>K34/10 Đồng Kè, Hòa Khánh Bắc, Liên CHiểu, Đà Nẵng</span>
                </div>
                <div class="imagesPreview">
                    <div class="nine columns">
                        <div class="coverflow top10 bot10">
                            <a class="prev-arrow"></a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/nha-06-nam-thanh1.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/3.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/2-1.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/2-1.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/2-1.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/2-1.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/2-1.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a href="">
                                <img data-src="https://phongtrodn.com/wp-content/uploads/2021/03/2-1.jpg"
                                    class="coverflow__image lazyload" />
                            </a>
                            <a class="next-arrow"></a>
                        </div>
                    </div>
                </div>
                <div class="description bg-white p-1">
                    <h5>Mô tả Chi tiết</h5>
                    <p>Cho thuê căn hộ sát bên cạnh chợ Non Nước – Ngũ Hành Sơn (06 Nam Thành) – Nằm ở vị trí 2 mặt
                        tiền, diện tích 32m2, có gác lửng rộng rãi, thiết bị vệ sinh sinh cao cấp, giá cho thuê. 2tr/căn
                        hộ Liên hệ Đt, zalo: 0905 868 758 (A.Thịnh)</p>
                </div>
                <div class="information bg-white p-1">
                    <h5>Thông Tin</h5>
                    <div class="row m-0">
                        <div class="col-6 p-1">
                            <i class="fas fa-money-bill-alt mr-1"></i>
                            <span class="font-weight-bold">Price: </span>
                            <span class="font-italic">3000000 đồng/Tháng</span>
                        </div>
                        <div class="col-6 p-1">
                            <span class="font-weight-bold">Square meters: </span>
                            <span class="font-italic">25m<sup>2</sup></span>
                        </div>
                        <div class="col-12 p-1">
                            <i class="fas fa-map-marker-alt mr-2"></i>
                            <span class="font-weight-bold">Address: </span>
                            <span class="font-italic">8/10 Đồng Kè, Hòa Khánh Bắc village, Liên Chiểu district</span>
                        </div>
                        <div class="col-6 p-1">
                            <span class="font-weight-bold">Họ và tên: </span>
                            <span class="font-italic text-primary">Trần Văn Thịnh</span>
                        </div>
                        <div class="col-6 p-1">
                            <span class="font-weight-bold">SDT: </span>
                            <span class="font-italic text-primary">0328560055</span>
                        </div>
                        <div class="col-12 p-1">
                            <span class="font-weight-bold">Email: </span>
                            <span class="font-italic text-primary">anhle1512001@gmail.com</span>
                        </div>
                    </div>
                    <div id="map"></div>
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <%@include file="../components/footer.jsp"%>

    <script src="${contextPath}/assets/javascript/template/jquery.min.js"></script>
<%--    <script src="${contextPath}/assets/javascript/jquery-3.2.1.slim.min.js"></script>--%>
    <script src="${contextPath}/assets/javascript/template/bootstrap/popper.min.js"></script>
    <script src="${contextPath}/assets/javascript/template/bootstrap.min.js"></script>
    <script src="${contextPath}/assets/javascript/template/lazyloading.min.js"></script>
    <script src="../../../assets/javascript/navigation.js"></script>
    <script src="../../../assets/javascript/slideShow.js"></script>
    <script>
        $(document).ready(function () {
            $('#loading').addClass("show");
            setTimeout(function () {
                $('#loading').removeClass("show");
                $('#loading').addClass("hidden");
            }, 2000);
            let address = "8/10 Dong Ke, Lien CHieu, Da Nang";
            let query = address.split(" ");
            query = query.join("+");
        })
    </script>
    <script>
        let map;
        let service;
        let infowindow;

        function initMap() {
            const sydney = new google.maps.LatLng(-33.867, 151.195);
            infowindow = new google.maps.InfoWindow();
            map = new google.maps.Map(document.getElementById("map"), {
                center: sydney,
                zoom: 15,
            });
            const request = {
                query: "Museum of Contemporary Art Australia",
                fields: ["name", "geometry"],
            };
            service = new google.maps.places.PlacesService(map);
            service.findPlaceFromQuery(request, (results, status) => {
                if (status === google.maps.places.PlacesServiceStatus.OK && results) {
                    for (let i = 0; i < results.length; i++) {
                        createMarker(results[i]);
                    }
                    map.setCenter(results[0].geometry.location);
                }
            });
        }

        function createMarker(place) {
            if (!place.geometry || !place.geometry.location) return;
            const marker = new google.maps.Marker({
                map,
                position: place.geometry.location,
            });
            google.maps.event.addListener(marker, "click", () => {
                infowindow.setContent(place.name || "");
                infowindow.open(map);
            });
        }
    </script>
    <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBm5cVeHG1x831muJ_Q7NiNUbFRt0R3Mug&callback=initMap&libraries=places&v=weekly"
            async
    ></script>
</body>

</html>