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
    <%@include file="../../../common/csslib.jsp"%>
    <link rel="stylesheet" href="${contextPath}/assets/css/template/pagination.min.css">
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
                            <form action="/home" class="form-search row" method="get" id="formSearch">
                                <div class="col-lg-10 col-md-12 col-xs-12 col-sm-12 row">
                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="district">District</label>
                                        <select <%--name="district"--%> id="district"
                                            class="form-control form-control-outline-none">
                                            <option value="all" data-id=0>All</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="village">Village</label>
                                        <select name="villageId" id="village"
                                            class="form-control form-control-outline-none">
                                            <option value="0" data-id=0>All</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="square">Square metre</label>
                                        <select id="square"
                                            class="form-control form-control-outline-none">
                                            <option value="all" data-square="all">All</option>
                                            <option value="<=20" data-square="<=20">Dưới 20m2</option>
                                            <option value="20-30" data-square="20-30">20m2 - 30m2</option>
                                            <option value="30-40" data-square="30-40">30m2 - 40m2</option>
                                            <option value="40-50" data-square="40-50">40m2 - 50m2</option>
                                            <option value="50-60" data-square="50-60">50m2 - 60m2</option>
                                            <option value="60-70" data-square="60-70">60m2 - 70m2</option>
                                            <option value="70-80" data-square="70-80">70m2 - 80m2</option>
                                            <option value="80-90" data-square="80-90">80m2 - 90m2</option>
                                            <option value="90-100" data-square="90-100">90m2 - 100m2</option>
                                            <option value=">=100" data-square=">=100">Trên 100m2</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                                        <label for="price">Price</label>
                                        <select id="price"
                                            class="form-control form-control-outline-none">
                                            <option value="all" data-price="all">All</option>
                                            <option value="<=500" data-price="<=500">&lt; 500K</option>
                                            <option value="500-1000" data-price="500-1000">500K - 1 triệu</option>
                                            <option value="1000-1500" data-price="1000-1500">1 triệu - 1 triệu 5</option>
                                            <option value="1500-2000" data-price="1500-2000">1 triệu 5 - 2 triệu</option>
                                            <option value="2000-3000" data-price="2000-3000">2 triệu - 3 triệu</option>
                                            <option value="3000-4000" data-price="3000-4000">3 triệu - 4 triệu</option>
                                            <option value="4000-5000" data-price="4000-5000">4 triệu - 5 triệu</option>
                                            <option value=">=5000" data-price=">=5000">&gt; 5 triệu</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="text" name="square_from" hidden>
                                <input type="text" name="square_to" hidden>
                                <input type="text" name="price_from" hidden>
                                <input type="text" name="price_to" hidden>
                                <input type="text" name="sort_by" hidden disabled>
                                <input type="text" name="order_by" hidden disabled>
                                <input type="hidden" value="1" name = "page" id = "page">
<%--                                <input type="hidden" value="" name = "maxPageItem" id = "maxPageItem">--%>
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
    <div class="container my-3">
        <div class="row mx-2">
            <span class="mr-2 text-dark">Sắp xếp: </span>
            <select class="post-sort p-1" id="sort">
                <option value="none">Thông thường</option>
                <option value="publishedAt_desc">Tin mới nhất</option>
                <option value="price_asc">Giá từ bé đến lớn</option>
                <option value="price_desc">Giá từ lớn đến bé</option>
                <option value="square_asc">Diện tích từ bé đến lớn</option>
                <option value="square_desc">Diện tích từ lớn đến bé</option>
            </select>
        </div>
    </div>
    <!-- list post -->
    <section>
        <div class="container">
            <div class="row">
                <c:if test="${!POSTMODELS.isEmpty()}">
                    <c:forEach items="${POSTMODELS}" var="postModels">
                        <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                            <div class="row mx-0 detail-list">
                                <div class="col-5 p-0">
                                    <a href="/post/show/${postModels.postSlug}">
                                        <img class="img-fluid lazyload"
                                             data-src="https://drive.google.com/uc?export=view&id=${postModels.linkImages}"
                                             style="width: 100%; height: 200px" alt="...">
                                    </a>
                                </div>
                                <div class="info-real col-7">
                                    <h6>
                                        <a href="/post/show/${postModels.postSlug}">
                                            ${postModels.title}
                                        </a>
                                    </h6>
                                    <p class="p-1 m-0">
                                        <i class="fas fa-dollar-sign text-info"></i>
                                        Giá:
                                        <strong>
                                            ${postModels.price}00 ₫ / Tháng
                                        </strong>
                                    </p>
                                    <p class="p-1 m-0"><i class="far fa-building text-info"></i> Diện tích:
                                        <strong>${postModels.square}m<sup>2</sup></strong></p>
                                    <p class="p-1 m-0"><i class="fas fa-map-marker-alt text-info"></i><span> Khu vực:
                                    <strong>${postModels.villageModel.villageName}-${postModels.villageModel.districtModel.districtName}</strong>
                                </span></p>
                                    <time class="float-right text-primary" id="datetime${postModels.postId}"></time>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="box">
                <ul id="pagination" class="pagination"></ul>
                <div class="show"></div>
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

    <%@include file="../../../common/javasciptlib.jsp"%>
    <script src="../../../assets/javascript/home.js"></script>
    <script src="../../../assets/javascript/template/pagination.min.js"></script>
    <script>
        <c:forEach items="${POSTMODELS}" var="postModels">
            $("#datetime${postModels.postId}").text(timeDifference(new Date(), new Date('${postModels.createAt}')));
        </c:forEach>
        function timeDifference(date1,date2) {
            let difference = date1.getTime() - date2.getTime();

            let daysDifference = Math.floor(difference/1000/60/60/24);
            if (daysDifference !== 0) {
                return daysDifference + ' day ago';
            }
            difference -= daysDifference*1000*60*60*24;

            let hoursDifference = Math.floor(difference/1000/60/60);
            if (hoursDifference !== 0) {
                return hoursDifference + ' hours ago';
            }
            difference -= hoursDifference*1000*60*60;

            let minutesDifference = Math.floor(difference/1000/60);
            if (minutesDifference !== 0) {
                return minutesDifference + ' minutes ago';
            }
            difference -= minutesDifference*1000*60;

            let secondsDifference = Math.floor(difference/1000);

            return secondsDifference + ' seconds ago';
        }
    </script>
    <script>
        if (window.location.href == "http://localhost:8080/home") {
            sessionStorage.clear();
        }

        if (${!DISTRICTSMODELS.isEmpty()}) {
            // set list district option
            let district_id = sessionStorage.getItem("district_id");
            <c:forEach items="${DISTRICTMODELS}" var="district">
                if (district_id === ${district.districtId}) {
                    $('#district').append("<option data-id = ${district.districtId} value='${district.districtName}' selected>${district.districtName}</option>");
                } else {
                    $('#district').append("<option data-id = ${district.districtId} value='${district.districtName}'>${district.districtName}</option>");
                }
            </c:forEach>
        }
        if (typeof(Storage) !== "undefined") {
            sessionStorage.setItem("district_id", $("#district option:selected").data("id"));
        }

        if (${POSTMODELS.isEmpty()}) {
            toastr.info("No posts found", "Notification:");
        }

        $(document).ready(function () {
            if ("${message}" != "" && "${alert}" == "danger") {
                toastr.error('${message}', 'falied!');
            }
            // set list village by district
            $("#district").change(() => {
                setupVillage();
            })

            let square = sessionStorage.getItem("square");
            let price = sessionStorage.getItem("price");
            let village_id = sessionStorage.getItem("village_id");

            $("#village option").each(function()
            {
                if ($(this).val() == village_id) {
                    $(this).attr("selected", "selected");
                }
            });

            $("#square option").each(function()
            {
                if ($(this).val() == square) {
                    $(this).attr("selected", "selected");
                }
            });

            $("#price option").each(function()
            {
                if ($(this).val() == price) {
                    $(this).attr("selected", "selected");
                }
            });

            $("#sort option").each(function () {
                if ($(this).val() == sessionStorage.getItem("sort")) {
                    $(this).attr("selected", "selected");
                }
            })

            $("#village").change(function () {
                if (typeof(Storage) !== "undefined") {
                    sessionStorage.setItem("village_id", $(this).val());
                }
            })

            $("#square").change(function () {
                if (typeof(Storage) !== "undefined") {
                    sessionStorage.setItem("square", $(this).val());
                }
            })

            $("#price").change(function () {
                if (typeof(Storage) !== "undefined") {
                    sessionStorage.setItem("price", $(this).val());
                }
            })

            //set value param square and price when recieve event submit form
            $("#formSearch").submit(() => {
                let square = $("#square option:selected").val();
                let price = $("#price option:selected").val();
                swithchSquare(square);
                swithchPrice(price);
                let sorter = $("#sort option:selected").val();
                if (sorter !== "none") {
                    sorter = sorter.split("_");
                    $("input[name='sort_by']").val(sorter[0]).attr("disabled", false);
                    $("input[name='order_by']").val(sorter[sorter.length - 1]).attr("disabled", false);
                }
            })

            $("#sort").change(function () {
                $("#formSearch").submit();
                if (typeof (Storage) !== "undefined") {
                    sessionStorage.setItem("sort", $(this).val());
                }
            })

            $('#pagination').pagination({
                total: ${PAGEABLE.totalItem}, // 总数据条数
                current: ${PAGEABLE.page}, // 当前页码
                length: ${PAGEABLE.maxPageItem}, // 每页数据量
                size: 2, // 显示按钮个数
                /**
                 * [click description]
                 * @param  {[object]} options = {
                 *      current: options.current,
                 *      length: options.length,
                 *      total: options.total
                 *  }
                 * @param  {[object]} $target [description]
                 * @return {[type]}         [description]
                 */
                click: function(options,$target) { // 点击按钮事件
                    $("#page").val(options.current);
                    // $("#maxPageItem").val(options.length);
                    $("#formSearch").submit();
                }
            });

            const swithchSquare = (square) => {
                // case square equals all
                if (square === "all") {
                    $("input[name='square_from']").prop("disabled", true);
                    $("input[name='square_to']").prop("disabled", true);
                    return;
                }
                if (square.startsWith(">=")) {
                    // greater than
                    $("input[name='square_from']").val(square.replace(">=", ""));
                    $("input[name='square_to']").prop("disabled", true);
                } else if (square.startsWith("<=")) {
                    // less than
                    $("input[name='square_to']").val(square.replace("<=", ""));
                    $("input[name='square_from']").prop("disabled", true);
                } else {
                    let _square = square.split("-");
                    $("input[name='square_from']").val(_square[0]);
                    $("input[name='square_to']").val(_square[_square.length - 1]);
                }
            }

            const swithchPrice = (price) => {
                // case price equals all
                if (price === "all") {
                    $("input[name='price_from']").prop("disabled", true);
                    $("input[name='price_to']").prop("disabled", true);
                    return;
                }
                if (price.startsWith(">=")) {
                    // greater than
                    $("input[name='price_from']").val(price.replace(">=", ""));
                    $("input[name='price_to']").prop("disabled", true);
                } else if (price.startsWith("<=")) {
                    // less than
                    $("input[name='price_to']").val(price.replace("<=", ""));
                    $("input[name='price_from']").prop("disabled", true);
                } else {
                    let _price = price.split("-");
                    $("input[name='price_from']").val(_price[0]);
                    $("input[name='price_to']").val(_price[_price.length - 1]);
                }
            }

            const setupVillage = function () {
                const districtId = $( "#district option:selected" ).data('id');
                $("#village option").remove();
                <c:forEach items="${DISTRICTMODELS}" var="district">
                if(districtId === ${district.districtId}) {
                    if (${!district.listVillage.isEmpty()}) {
                        <c:forEach items="${district.listVillage}" var="village">
                        $('#village').append("<option " +
                            "value='${village.villageId}'>${village.villageName}</option>");
                        </c:forEach>
                    } else {
                        $('#village').append("<option value='0' >All</option>");
                    }
                }
                </c:forEach>
                if (typeof(Storage) !== "undefined") {
                    sessionStorage.setItem("villaged_id", $("#village option:selected").val());
                    sessionStorage.setItem("district_id", $("#district option:selected").data("id"));
                }
                if (districtId == 0) {
                    $('#village').append("<option value='0' data-id=0>All</option>");
                }
            }
            setupVillage();
        })
    </script>
</body>

</html>