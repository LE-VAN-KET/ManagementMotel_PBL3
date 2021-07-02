<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16/06/2021
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../common/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Chat and Discussion Platform</title>
    <link rel="icon" href="${contextPath}/assets/images/favicon.png" type="image/png"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/template/soho.min.css"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/template/toastr.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<%--    setting modal--%>
    <%@include file="../components/modals/setting-modal.jsp"%>
<%--    layout--%>
    <div class="layout">
<%--        navigation--%>
        <%@include file="../components/navigationChat.jsp"%>
<%--        content--%>
        <div class="content">
            <%@include file="../components/conversation/index.jsp"%>
        </div>
    </div>
<%--    delete modal--%>
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                        <span>
                            Please Confirm delete this message.
                            <i class="fas fa-check-circle ml-2"></i>
                        </span>
                </div>
                <div class="modal-body mx-auto">
                    <span>Are you sure?</span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger text-light" id="btnRemove">
                        <i class="far fa-trash-alt mr-1 text-light"></i>
                        Delete
                    </button>
                </div>

            </div>
        </div>
    </div>
    <script src="${contextPath}/assets/javascript/template/jquery.min.js" ></script>
    <script src="${contextPath}/assets/javascript/template/bootstrap/popper.min.js" ></script>
    <script src="${contextPath}/assets/javascript/template/bootstrap/bootstrap.min.js" ></script>
    <script src="${contextPath}/assets/javascript/template/nicescroll.min.js"></script>
    <script src="${contextPath}/assets/javascript/template/toastr.min.js"></script>
    <script src="${contextPath}/assets/javascript/template/chat.min.js"></script>
<%--    <script src="${contextPath}/assets/javascript/chat_exemple.js"></script>--%>

    <div class="nicescroll-rails nicescroll-rails-vr" id="ascrail2000" style="width: 4px; z-index: auto; cursor: default; position: absolute; top: 530.797px; left: 1354px; height: 72px; display: none; opacity: 0;">
        <div class="nicescroll-cursors" style="position: relative; top: 0px; float: right; width: 4px; height: 72px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-hr" id="ascrail2000-hr" style="height: 4px; z-index: auto; top: 598.797px; left: 1076px; position: absolute; cursor: default; display: none; width: 282px; opacity: 0;">
        <div class="nicescroll-cursors" style="position: absolute; top: 0px; height: 4px; width: 188px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px; left: 0px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-vr" id="ascrail2001" style="width: 4px; z-index: auto; cursor: default; position: absolute; top: 136px; left: 1378px; height: 481px; opacity: 0; display: block;">
        <div class="nicescroll-cursors" style="position: relative; top: 195px; float: right; width: 4px; height: 286px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-hr" id="ascrail2001-hr" style="height: 4px; z-index: auto; top: 613px; left: 460px; position: absolute; cursor: default; opacity: 0; display: none; width: 918px;">
        <div class="nicescroll-cursors" style="position: absolute; top: 0px; height: 4px; width: 922px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px; left: 0px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-vr" id="ascrail2002" style="width: 4px; z-index: auto; cursor: default; position: absolute; top: 185px; left: 426px; height: 543px; opacity: 0; display: none;">
        <div class="nicescroll-cursors" style="position: relative; top: 0px; float: right; width: 4px; height: 20px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-hr" id="ascrail2002-hr" style="height: 4px; z-index: auto; top: 724px; left: 100px; position: absolute; cursor: default; opacity: 0; display: none;">
        <div class="nicescroll-cursors" style="position: absolute; top: 0px; height: 4px; width: 0px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px; left: 0px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-vr" id="ascrail2003" style="width: 4px; z-index: auto; cursor: default; position: absolute; top: 0px; left: -4px; height: 0px; display: none;">
        <div class="nicescroll-cursors" style="position: relative; top: 0px; float: right; width: 4px; height: 0px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-hr" id="ascrail2003-hr" style="height: 4px; z-index: auto; top: -4px; left: 0px; position: absolute; cursor: default; display: none;">
        <div class="nicescroll-cursors" style="position: absolute; top: 0px; height: 4px; width: 0px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-vr" id="ascrail2004" style="width: 4px; z-index: auto; cursor: default; position: absolute; top: 0px; left: -4px; height: 0px; display: none;">
        <div class="nicescroll-cursors" style="position: relative; top: 0px; float: right; width: 4px; height: 0px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-hr" id="ascrail2004-hr" style="height: 4px; z-index: auto; top: -4px; left: 0px; position: absolute; cursor: default; display: none;">
        <div class="nicescroll-cursors" style="position: absolute; top: 0px; height: 4px; width: 0px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-vr" id="ascrail2005" style="width: 4px; z-index: auto; cursor: default; position: absolute; top: 110px; left: 1378px; height: 618px; display: none; opacity: 0;">
        <div class="nicescroll-cursors" style="position: relative; top: 0px; float: right; width: 4px; height: 402px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px;"></div>
    </div>
    <div class="nicescroll-rails nicescroll-rails-hr" id="ascrail2005-hr" style="height: 4px; z-index: auto; top: 724px; left: 1052px; position: absolute; cursor: default; display: none; width: 326px; opacity: 0;">
        <div class="nicescroll-cursors" style="position: absolute; top: 0px; height: 4px; width: 330px; background-color: rgba(66, 66, 66, 0.2); border: 0px; background-clip: padding-box; border-radius: 5px; left: 0px;"></div>
    </div>
    <script src="${contextPath}/assets/javascript/message.js"></script>
    <script>
        const senderId = ${SENDER.userId};
        let recipientId, currentChattingName;
        if (window.localStorage) {
            recipientId = localStorage.getItem("_recipient_id");
            currentChattingName = localStorage.getItem("_fullname");
            localStorage.removeItem("_recipient_id");
            localStorage.removeItem("_fullname");
        }
        let count_no_seen = 0;
        MessageSocket();
    </script>
</body>
</html>
