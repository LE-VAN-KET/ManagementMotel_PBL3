<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30/05/2021
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
    <div class="sidebar-post bg-white">
        <div class="title-sidebar bg-primary p-2">
            <span class="text-light">Thông tin</span>
        </div>
        <div class="user-info bg-white">
            <a href="javascript:void(0)" class="clearfix text-center">
                <figure class="avatar">
                    <img class="lazyload img-thumbnail rounded-circle" width="100"
                         data-src="https://static123.com/uploads/images/2018/12/12/boy_1544603222.png">
                </figure>
                <p class="p-0 text-primary text-center">${ACCOUNTMODEL.user.fullName}</p>
            </a>
        </div>
        <ul class="navbar-nav bg-white">
            <div class="py-1">
                <span class="text-primary">Phone number:</span>
                <span class="ml-1 text-muted">${ACCOUNTMODEL.user.SDT}</span>
            </div>
            <div class="py-1">
                <span class="text-primary">Email:</span>
                <small class="ml-1 text-muted">${ACCOUNTMODEL.user.email}</small>
            </div>
        </ul>
    </div>
</div>
