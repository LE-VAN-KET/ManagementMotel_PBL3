<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27/05/2021
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../common/taglib.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Personal post</title>
	<%@include file="../../../common/csslib.jsp"%>
	<link rel="stylesheet" href="${contextPath}/assets/css/detailPost.css">
	<link rel="stylesheet" href="${contextPath}/assets/css/template/pagination.min.css">
</head>

<body class="hero-anime" style="background-color: #f5f5f5;">
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
            <%@include file="../components/sidebarPersonalPost.jsp"%>
			<div class="col-lg-9 col-md-12 col-xs-12 col-sm-12 my-1">
				<div class="personal-post bg-dark p-2">
					<span class="text-light">Tin đăng</span>
				</div>
					<c:forEach items="${POSTMODELS}" var="postModels">
						<div class="detail-personal-post row m-0">
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
									<span class="text-danger">
								${postModels.price}00 ₫ / Tháng
							</span>
								</p>
								<p class="p-1 m-0"><i class="far fa-building text-info"></i> Diện tích:
									<span>25m<sup>2</sup></span></p>
								<p class="p-1 m-0"><i class="fas fa-map-marker-alt text-info"></i><span> Address:
								<span>${postModels.address}-${postModels.villageModel.villageName}-
										${postModels.villageModel.districtModel.districtName}</span>
							</span></p>
								<div class="row">
									<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
										<span>Ngày đăng:</span>
										<time class="ml-1">
											<fmt:formatDate pattern = "yyyy-MM-dd" value = "${postModels.createAt}" />
										</time>
									</div>
									<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
										<span>Trạng thái:</span>
										<c:if test="${postModels.statusPost}">
											<span class="text-success ml-1">Đã duyệt</span>
										</c:if>
										<c:if test="${!postModels.statusPost}">
											<span class="text-danger ml-1">Chờ duyệt</span>
										</c:if>
									</div>
									<div class="mx-auto">
										<a class="pure-button m-1 px-1 text-light bg-success"
										   href="/post/update/${postModels.postSlug}">update</a>
										<a class="pure-button bg-danger m-1 px-1 text-light"
                                           data-href="/personal-post/delete/${postModels.postId}" data-toggle="modal"
                                           data-target="#confirm-delete" href="#">delete</a>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</c:forEach>
                <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					 aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
							<div class="modal-header">
							<span>
								Please Confirm
								<i class="fas fa-check-circle ml-2"></i>
							</span>
							</div>
							<div class="modal-body mx-auto">
								<span>Are you sure?</span>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<form action="" method="post" id="deletePost" class="mb-0">
									<button type="submit" class="btn btn-danger text-light">
										<i class="far fa-trash-alt mr-1 text-light"></i>Delete</button>
								</form>
							</div>

                        </div>
                    </div>
                </div>
				<form action="/personal-post" method="get" id="changePage">
					<input type="hidden" value="1" name = "page" id = "page">
				</form>
				<div class="box">
					<ul id="pagination" class="pagination"></ul>
					<div class="show"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<%@include file="../components/footer.jsp"%>

	<%@include file="../../../common/javasciptlib.jsp"%>
	<script src="../../../assets/javascript/template/pagination.min.js"></script>
	<script>
		$(document).ready(function () {
			$('#loading').addClass("show");
			setTimeout(function () {
				$('#loading').removeClass("show");
				$('#loading').addClass("hidden");
			}, 1500);

			if("${message}" !== "") {
				toastr.info('${message}');
			}

			$('#confirm-delete').on('show.bs.modal', function(e) {
				$(this).find('#deletePost').attr('action', $(e.relatedTarget).data('href'));
			});

			$('#pagination').pagination({
				total: ${PAGEABLE.totalItem}, // 总数据条数
				current: ${PAGEABLE.page}, // 当前页码
				length: ${PAGEABLE.maxPageItem}, // 每页数据量
				size: ${PAGEABLE.totalPage}, // 显示按钮个数
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
					$("#changePage").submit();
				}
			});
		});
	</script>
</body>

</html>