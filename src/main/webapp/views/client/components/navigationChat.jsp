<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16/06/2021
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navigation">
    <div class="nav-group">
        <div class="img-logo"><a class="logo" href="#">
            <img src="${contextPath}/assets/images/logo-pbl3.png" class="lazyload m-0" alt="...">
        </a></div>
        <ul>
            <li><a href="/home" data-toggle="tooltip" data-placement="right" title="Home">
                <i class="ti-home"></i></a>
            </li>
            <li id="allFriendBtn"><a class="notifiy_badge active" data-navigation-target="chats" href="#"
                                     data-toggle="tooltip" data-placement="right" title="Chats">
                <i class="ti-comment-alt"></i></a>
            </li>
            <li class="dropright dropdown-hotel">
                <a href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-hotel"></i></a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/home?districtName=lien+chieu" >Liên Chiểu</a>
                    <a class="dropdown-item" href="/home?districtName=thanh+khe">Thanh Khê</a>
                    <a class="dropdown-item" href="/home?districtName=son+tra">Sơn Trà</a>
                    <a class="dropdown-item" href="/home?districtName=hai+chau">Hải Châu</a>
                    <a class="dropdown-item" href="/home?districtName=cam+le">Cẩm Lệ</a>
                    <a class="dropdown-item" href="/home?districtName=hoa+vang">Hòa Vang</a>
                    <a class="dropdown-item" href="/home?districtName=ngu+hanh+son">Ngũ Hành Sơn</a>
                </div>
            </li>
            <li class="brackets" ><a href="/contact" data-toggle="tooltip" data-placement="right" title="Liên hệ">
                <i class="far fa-address-card"></i></a>
            </li>
            <li><a href="#" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-caret-down"></i></a>
                <div class="dropdown-menu bg-dark text-light border-0 p-0">
                    <!-- Dropdown menu links -->
                    <a class="dropdown-item py-2" href="/personal-post">
                        <i class="far fa-list-alt mr-1"></i>Quản lý bài đăng</a>
                    <a class="dropdown-item py-2" href="/edit-profile">
                        <i class="ti-pencil mr-1"></i>Edit profile</a>
                    <a class="dropdown-item py-2" href="/logout">
                        <i class="ti-power-off mr-1"></i>Log out</a>
                </div>
            </li>
            <li><a href="#" data-toggle="modal" data-target="#settingModal"><i class="ti-settings"></i></a></li>
        </ul>
    </div>
</nav>
