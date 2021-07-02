<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16/06/2021
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--// sidebar group--%>
<div class="sidebar-group">
    <%--// Chats sidebar--%>
    <%@include file="./partials/chats-sidebar.jsp"%>
</div>
<%--// chat--%>
<div class="chat" id="receiverId">
    <div class="chat-header d-flex flex-row flex-nowrap align-items-center">
        <div class="chat-header-user col-8 px-0">
            <figure class="avatar avatar-lg">
                <img class="rounded-circle" id="currentChattingAvatar" src="${contextPath}/assets/images/143086968_2856368904622192_1959732218791162458_n.png"/>
            </figure>
            <div>
                <h5 id="currentChattingName"></h5>
                <small class="text-success"><i>Online</i></small>
            </div>
        </div>
        <div class="chat-header-action col-4">
            <ul class="list-inline d-inline-block float-right">
                <li class="list-inline-item"><a class="text-success" href="#">
                    <i class="fa fa-phone" aria-hidden="true"></i></a></li>
                <li class="list-inline-item"><a class="text-success" href="#">
                    <i class="fa fa-video-camera" aria-hidden="true"></i></a></li>
                <li class="list-inline-item"><a class="text-secondary" href="#" data-toggle="dropdown" aria-expanded="false">
                    <i class="ti-more"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" x-placement="bottom-end" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(1222px, 68px, 0px);">
                        <a class="dropdown-item active" href="#" data-navigation-target="contact-information">Profile</a>
                        <a class="dropdown-item" href="#">Add to archive</a><a class="dropdown-item" href="#">Delete</a>
                        <div class="dropdown-divider"></div><a class="dropdown-item" href="#">Block</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="chat-body" tabindex="2" style="outline: none;">
        <!-- .no-message-->
        <!--
        <div class="no-message-container">
        <i class="fa fa-comments-o"></i>
        <p>Select a chat to read messages</p>
        </div>
        -->
        <div class="messages">
            <div class="message-item outgoing-message">
                <div class="message-content">Hey, Maher! I&apos;m waiting for you to send me the files.</div>
                <div class="message-action">Am 09:34 <i class="ti-double-check"></i></div>
            </div>
            <div class="message-item">
                <div class="d-flex flex-row flex-nowrap justify-content-center align-items-center">
                    <div class="message-content mr-2">I&apos;m sorry :( I&apos;ll send you as soon as possibleddddddddddddddddddddddddddddddddddddddd
                        ddddddddddddddddddddddddddddddddddddddddddd.</div>
                    <div class="message-list-action action-toggle">
                        <div class="dropdown"><a data-toggle="dropdown" href="#"><i class="ti-more"></i></a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#">Open</a>
                                <a class="dropdown-item active" href="#" data-navigation-target="contact-information">Profile</a>
                                <a class="dropdown-item" href="#">Add to archive</a>
                                <a class="dropdown-item" href="#">Delete</a></div>
                        </div>
                    </div>
                </div>
                <div class="message-action not-seen"><i class="ti-check mr-1"></i>Pm 14:20</div>
            </div>
            <div class="message-item outgoing-message">
                <div class="message-content">I&apos;m waiting. Thank you :)</div>
                <div class="message-action">Pm 14:25 <i class="ti-double-check"></i></div>
            </div>
            <div class="message-item outgoing-message" >
                <div class="d-flex flex-row flex-nowrap justify-content-end align-items-center">
                    <div class="message-list-action action-toggle popup">
                        <a class="btn-action" href="#"><i class="ti-more"></i></a>
                        <div class="active-menu">
                            <a class="action-item text-muted" data-toggle="modal" data-target="#deleteModal"
                               href="javascript:void(0)">Delete</a>
                        </div>
                    </div>
                    <div class="message-content ml-2">I'm sorry :( I'll send you as soon as .</div>
                </div>
                <div class="message-action had-seen"><i class="ti-check mr-1"></i>Pm 14:20</div>
            </div>
            <div class="message-item outgoing-message" >
                <div class="d-flex flex-row flex-nowrap justify-content-end align-items-center">
                    <div class="message-list-action action-toggle popup">
                        <a class="btn-action" href="#"><i class="ti-more"></i></a>
                        <div class="active-menu">
                            <a class="action-item text-muted" data-toggle="modal" data-target="#deleteModal"
                               href="javascript:void(0)">Delete</a>
                        </div>
                    </div>
                    <div class="message-content ml-2">I'm sorry :( I'll send you as soon as .</div>
                </div>
                <div class="message-action had-seen"><i class="ti-check mr-1"></i>Pm 14:20</div>
            </div>
            <div class="message-item outgoing-message" >
                <div class="d-flex flex-row flex-nowrap justify-content-end align-items-center">
                    <div class="message-list-action action-toggle popup">
                        <a class="btn-action" href="#"><i class="ti-more"></i></a>
                        <div class="active-menu">
                            <a class="action-item text-muted" data-toggle="modal" data-target="#deleteModal"
                               href="javascript:void(0)">Delete</a>
                        </div>
                    </div>
                    <div class="message-content ml-2">I'm sorry :( I'll send you as soon as .</div>
                </div>
                <div class="message-action had-seen"><i class="ti-check mr-1"></i>Pm 14:20</div>
            </div>
        </div>
    </div>
    <div class="chat-footer">
        <form action="" id="chat-message">
            <textarea class="form-control" type="text" placeholder="Recipient's username" aria-label="Recipient's username"
                      aria-describedby="button-addon2" rows="1" name="body" autocomplete="off" required></textarea>
            <div class="form-buttons">
<%--                <button class="btn btn-light btn-floating" type="button"><i class="fa fa-paperclip"></i></button>--%>
<%--                <button class="btn btn-light btn-floating" type="button"><i class="fa fa-microphone"></i></button>--%>
                <button class="btn btn-primary btn-floating" type="submit"><i class="fa fa-send"></i></button>
            </div>
        </form>
    </div>
</div>