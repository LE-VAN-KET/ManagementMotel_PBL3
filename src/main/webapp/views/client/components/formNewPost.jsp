<%--
  Created by IntelliJ IDEA.
  User: Van Ket
  Date: 03/05/2021
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="sidebar col-lg-3 col-md-3 col-xs-12 col-sm-12 py-3">
                <div class="user-info">
                    <a href="javascript:void(0)" class="clearfix text-center">
                        <figure class="avatar">
                            <img class="lazyload img-thumbnail rounded-circle" width="100"
                                 data-src="https://static123.com/uploads/images/2018/12/12/boy_1544603222.png">
                        </figure>
                        <p class="p-0 text-white text-center">Le Van Ket</p>
                    </a>
                </div>
                <ul class="navbar-nav">
                    <li class="nav-item py-1">
                        <a class="nav-link " href="/quan-li-dang-tin">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-file-text">
                                <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                                <polyline points="14 2 14 8 20 8"></polyline>
                                <line x1="16" y1="13" x2="8" y2="13"></line>
                                <line x1="16" y1="17" x2="8" y2="17"></line>
                                <polyline points="10 9 9 9 8 9"></polyline>
                            </svg>
                            Quản lý tin đăng
                        </a>
                    </li>
                    <li class="nav-item py-1">
                        <a class="nav-link " href="/edit-user-info">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-edit">
                                <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                                <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                            </svg>
                            Sửa thông tin cá nhân
                        </a>
                    </li>
                    <li class="nav-item py-1">
                        <a class="nav-link" href="https://phongtro123.com/lien-he">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-message-circle">
                                <path
                                        d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z">
                                </path>
                            </svg>
                            Liên hệ
                        </a>
                    </li>
                    <li class="nav-item py-1">
                        <a class="nav-link" href="https://phongtro123.com/thoat">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-log-out">
                                <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                                <polyline points="16 17 21 12 16 7"></polyline>
                                <line x1="21" y1="12" x2="9" y2="12"></line>
                            </svg>
                            Logout
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-lg-9 col-md-9 col-xs-12 col-sm-12 my-1">
                <div class="title-section">
                    <h4>Đăng tin mới</h4>
                </div>
                <div class="row">
                    <div class="col-12">
                        <form action="/post" method="POST" id="postForm" role="form"
                              enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="title">Tiêu đề: </label><br>
                                <small>Ví dụ: <i class="fas fa-check mx-1 text-primary"></i>Cho thuê nhà nguyên căn
                                    mới xây đường trường chinh </small>
                                <input type="text" name="title" class="form-control" id="title"
                                       placeholder="Tiêu đề" required="required">
                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="price">Giá thuê / Tháng: </label><br>
                                        <small>Đơn vị VNĐ (Ví dụ giá 2 triệu bạn cần nhập: <i
                                                class="fas fa-check mx-1 text-primary"></i>2000000)</small>
                                        <input type="number" class="form-control" name="price" id="price"
                                               placeholder="Giá thuê">
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="square">Diện tính: </label><br>
                                        <small>Nhập chính xác diện tích nha! Đơn vị m<sup>2</sup></small>
                                        <input type="number" class="form-control" name="square" id="square"
                                               placeholder="Diện tích">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="district">District</label>
                                        <select id="district"
                                                class="form-control form-control-outline-none">
                                            <option value="all">All</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="village">Village</label>
                                        <select id="village"
                                                class="form-control form-control-outline-none">
                                            <option value="all">All</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address">Địa chỉ cụ thể: </label><br>
                                <input type="text" class="form-control pac-target-input" name="address"
                                       autocomplete="off" id="address" placeholder="Số nhà, tên đường..."
                                       required="required">
                            </div>
                            <div class="form-group">
                                <label for="description">Content</label>
                                <textarea class="form-control" rows="50" name="description" id="description"
                                          placeholder="Content's name..."></textarea>
                            </div>
                            <input name="villageId" type="number" hidden>
                            <input name="userId" type="number" hidden>
                            <div class="form-group">
                                <label for="files">Hình ảnh: </label>
                                <small>Bạn phải nhập tối thiểu 1 hình ảnh, hình ảnh càng nhiều độ tin cậy càng
                                    cao</small>
                                <div class="img-list-img row"></div>
                                <label for="files" class="add-img">
                                    <span>+</span>
                                    <input type="file" name="file"  id="files" multiple="multiple"
                                           accept=".png, .jpg, .jpeg, .gif" required>
                                </label>
                            </div>
                            <button type="submit" class="btn btn-primary float-right">Đăng tin ngay</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
