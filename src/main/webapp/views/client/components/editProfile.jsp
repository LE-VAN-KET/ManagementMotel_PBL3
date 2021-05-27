<%--
  Created by IntelliJ IDEA.
  User: Van Ket
  Date: 18/05/2021
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12 edit_information">
        <form action="/edit-profile"  method="POST" accept-charset="utf-8">
            <h3 class="text-center mt-2">Edit Personal Information</h3>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <div class="form-group">
                        <label for="fullname">Full Name:</label>
                        <input type="text" name="fullName" id="fullname" class="form-control"
                               value="${ACCOUNTMODEL.user.fullName}" required >
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <div class="form-group">
                        <label for="phone">Mobile Number:</label>
                        <input type="tel" name="SDT" id="phone"  class="form-control" value="${ACCOUNTMODEL.user.SDT}"
                               required pattern=[0-9]{10}>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="form-group">
                        <label for="email">Email Address:</label>
                        <input type="email" name="email" id="email" class="form-control"
                               value="${ACCOUNTMODEL.user.email}" required disabled>
                    </div>
                    <a href="#" class="btn btn-info my-1" id="changeEmail">Change Email</a>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="form-group">
                        <label for="username">Username: </label>
                        <input type="text" name="username" id="username" disabled class="form-control"
                               value="${ACCOUNTMODEL.username}" required>
                    </div>
                    <a href="#" class="btn btn-info" id="changeUsername">Change Username</a>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="form-group">
                        <label for="password">New Password:</label>
                        <input type="password" name="password" id="password" class="form-control"
                               value="" placeholder="new password" disabled >
                    </div>
                    <a href="#" class="btn btn-info my-1" id="changePassword">Change Password</a>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 submit">
                    <div class="form-group">
                        <input type="submit" class="btn btn-success float-right" value="Submit">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
