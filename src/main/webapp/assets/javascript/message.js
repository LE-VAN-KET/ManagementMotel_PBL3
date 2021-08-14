'use strict';

function MessageSocket() {
    let a = null;
    if ("WebSocket" in window) {
        var d = function (b) {
            let c = b.message.sender.userId;
            b = b.message.recipient.userId;
            return c == senderId && b == recipientId || c == recipientId && b == senderId
        };
        a = new WebSocket("wss://" + window.location.host + "/message/" + senderId);
        window.onbeforeunload = function () {
            a.onclose()
        };
        a.onopen = b => {
            void 0 !== a && a.readyState == WebSocket.OPEN && (a.send(JSON.stringify({
                    messageType: "LIST_LAST_MESSAGE"
                })), a.send(JSON.stringify({
                    messageType: "NOTIFICATION_MSG"
                })), count_no_seen =
                0)
        };
        a.onmessage = b => {
            b = JSON.parse(b.data);
            switch (b.messageType) {
                case "ADD":
                    e(b);
                    break;
                case "DELETE":
                    d(b) && (b.errors ? toastr.error(b.errors, "Delete message failed") : Message.delete(b, senderId));
                    break;
                case "MSG_HISTORY":
                    f(b);
                    break;
                case "LIST_LAST_MESSAGE":
                    k(b);
                    break;
                case "LIST_USERS_ONLINE":
                    l(b);
                    break;
                case "REFRESH_USERS_ONLINE":
                    void 0 !== a && a.readyState == WebSocket.OPEN && a.send(JSON.stringify({
                        messageType: "LIST_USERS_ONLINE"
                    }));
                    break;
                case "NOTIFICATION_MSG":
                    m(b)
            }
        };
        a.onclose = b => {
            console.log("Server Disconnect...")
        };
        a.onerror = b => {
            console.log("error" + b)
        };
        $("#chat-message").submit(function (b) {
            var c = $.trim($("#chat-message textarea[name='body']").val());
            void 0 !== a && a.readyState == WebSocket.OPEN && "" != c && (a.send(JSON.stringify({
                body: c,
                senderId,
                recipientId: $(this).attr("data-recipientid"),
                messageType: "ADD"
            })), c = $('#chat-message textarea[name="body"]'), c.val(""), c.focus(), c.height("auto"));
            b.preventDefault()
        });
        $("#btnRemove").click(function () {
            void 0 !== a && a.readyState == WebSocket.OPEN && (a.send(JSON.stringify({
                messageId: $(this).attr("data-messageid"),
                senderId: $(this).attr("data-userid"),
                messageType: "DELETE"
            })), $("#deleteModal").modal("toggle"))
        });
        $(document).on("click", ".list-group .list-group-item", function () {
            $(".list-group .open-chat").removeClass("open-chat");
            $(this).addClass("open-chat");
            $("#currentChattingName").text($(this).data("fullname"));
            $("#chat-message").attr("data-recipientid", $(this).data("recipientid"));
            void 0 !== a && a.readyState == WebSocket.OPEN && (a.send(JSON.stringify({
                    recipientId: $(this).data("recipientid"),
                    senderId,
                    messageType: "MSG_HISTORY"
                })),
                recipientId = $(this).data("recipientid"), $("#chat-message textarea[name='body']").focus())
        });
        $('#chat-message textarea[name="body"]').focus(function () {
            let b = $(".list-group .open-chat .new-message-count");
            0 !== count_no_seen && 0 !== b.length && void 0 !== a && a.readyState == WebSocket.OPEN && (a.send(JSON.stringify({
                recipientId: $("#chat-message").attr("data-recipientid"),
                messageType: "UPDATE_MSG_SEEN"
            })), a.send(JSON.stringify({
                messageType: "NOTIFICATION_MSG"
            })))
        });
        const e = b => {
                d(b) && (b.errors ? b.errors.forEach(c => {
                        toastr.error(c)
                    }) :
                    Message.add(b, b.message.sender.userId === senderId ? "outgoing-message" : null))
            },
            f = b => {
                count_no_seen = 0;
                $(".layout .content .chat .chat-body .messages .message-item").remove();
                if(b.message.length == 0) {
                    $(".chat-body .message").remove();
                    $(".chat-body").append('<div class="no-message-container">'
                    + '<i class="fa fa-comments-o"></i>
                    + '<p>Select a chat to read messages</p></div>');
                    return;
                }
                b.messages.forEach(c => {
                    e({
                        message: c
                    })
                })
            },
            k = b => {
                $(".list-group .list-group-item").remove();
                b.listLastMessage.forEach(c => {
                    if (senderId === c.sender.userId || senderId === c.recipient.userId) {
                        let g, h;
                        c.sender.userId === senderId ? (g = c.recipient.userId, h = c.recipient.fullName) : (g = c.sender.userId, h = c.sender.fullName);
                        let n = g == recipientId ? " open-chat" :
                            "";
                        $(".list-group").append('<li class="list-group-item' + n + '" data-senderId=' + senderId + " data-recipientId=" + g + ' data-fullName="' + h + '"><figure class="avatar"><img class="rounded-circle" src="/assets/images/143086968_2856368904622192_1959732218791162458_n.png"/></figure><div class="users-list-body"><h5>' + h + "</h5><p>" + c.body + '</p><div class="users-list-action action-toggle"><div class="dropdown"><a data-toggle="dropdown" href="#"><i class="ti-more"></i></a> <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" href="#">Open</a></div></div></div></div>')
                    }
                });
                $("#chat-message").attr("data-recipientid") ? void 0 !== a && a.readyState == WebSocket.OPEN && a.send(JSON.stringify({
                    messageType: "LIST_USERS_ONLINE"
                })) : (recipientId || (recipientId = $(".list-group .list-group-item").first().data("recipientid"), currentChattingName = $(".list-group .list-group-item").first().data("fullname"), $(".list-group .list-group-item").first().addClass("open-chat")), void 0 !== a && a.readyState == WebSocket.OPEN && (a.send(JSON.stringify({
                        recipientId,
                        senderId,
                        messageType: "MSG_HISTORY"
                    })), a.send(JSON.stringify({
                        messageType: "LIST_USERS_ONLINE"
                    })),
                    a.send(JSON.stringify({
                        messageType: "REFRESH_USERS_ONLINE"
                    }))), $("#currentChattingName").text(currentChattingName), $("#chat-message").attr("data-recipientid", recipientId), $("#chat-message textarea[name='body']").focus())
            },
            l = b => {
                $(".list-group .list-group-item").each(function () {
                    let c = $(this).find("figure.avatar").first();
                    c.removeClass("avatar-state-success");
                    b.listUsersOnline.forEach(g => {
                        $(this).data("recipientid") == g && c.addClass("avatar-state-success")
                    })
                })
            },
            m = b => {
                count_no_seen = 0;
                $(".list-group .list-group-item .new-message-count").remove();
                b.notificationMessages.forEach(c => {
                    count_no_seen += c.amount;
                    $(".list-group .list-group-item").each(function () {
                        $(this).data("recipientid") == c.recipinetId && 0 != c.amount && $(this).find(".users-list-body p").first().after('<div class="new-message-count float-right">' + c.amount + "</div>")
                    })
                });
                $("#chat-message textarea[name='body']").focus()
            };
        $("#chat-message textarea[name='body']").on("keyup", function (b) {
            13 === b.keyCode && (b.preventDefault(), $("#chat-message").submit())
        })
    } else toastr.info("The browser does not support websocket")
}

function DateFormatter(a) {
    switch (typeof a) {
        case "number":
            break;
        case "string":
            a = +new Date(a);
            break;
        case "object":
            break;
        default:
            a = +new Date
    }
    var d = new Date;
    let e = d.getDay() - a.getDay(),
        f = d.getMonth() - a.getMonth();
    d = d.getFullYear() - a.getFullYear();
    return 0 === e && 0 === f && 0 === d ? "Today, " + a.toLocaleString("en-US", {
        hour: "2-digit",
        minute: "2-digit"
    }) : 1 === e && 0 === f && 0 === d ? "Yesterday, " + a.toLocaleString("en-US", {
        hour: "2-digit",
        minute: "2-digit"
    }) : 4 > e && 0 === f && 0 === d ? a.toLocaleString("en-US", {
        weekday: "long",
        hour: "2-digit",
        minute: "2-digit"
    }) : a.toLocaleString("en-US", {
        year: "numeric",
        month: "short",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit"
    })
}
const Message = {
    add: function (a, d) {
        let e = $(".layout .content .chat .chat-body"),
            f = DateFormatter(new Date(a.message.createAt));
        0 < e.length && (d = d || "", $(".layout .content .chat .chat-body .messages").append('<div class="message-item ' + d + '" data-messageid=' + a.message.messageId + " data-userid=" + a.message.sender.userId + '><div class="d-flex flex-row flex-nowrap justify-content-' + (d ? "end" : "start") + ' align-items-center">' + (d ? '<div class="message-list-action action-toggle popup"><a class="btn-action" href="#"><i class="ti-more"></i></a><div class="active-menu"><a class="action-item text-muted" data-toggle="modal" data-target="#deleteModal" href="javascript:void(0)">Delete</a></div></div>' :
            "") + '<div class="message-content ' + (d ? "ml-2" : "") + '">' + a.message.body + '</div></div><div class="message-action ' + (a.message.isSeen ? "had-seen" : "not-seen") + '">' + (d ? f + '<i class="ti-check ml-1"></i>' : f) + "</div></div>"), e.scrollTop(e.get(0).scrollHeight, -1).niceScroll({
            cursorcolor: "rgba(66, 66, 66, 0.20)",
            cursorwidth: "4px",
            cursorborder: "0px"
        }).resize())
    },
    delete: function (a, d) {
        $(".messages .message-item").each(function () {
            $(this).data("messageid") == a.message.messageId && ($(this).remove(), d == a.message.sender.userId &&
                toastr.success("delete message successfully"))
        })
    }
};
$(document).on("click", ".layout .content .sidebar-group .sidebar .list-group-item", function () {
    jQuery.browser.mobile && $(this).closest(".sidebar-group").removeClass("mobile-open")
});
$(document).on("click", ".messages .message-item .message-list-action", function () {
    $(".messages .message-item .message-list-action").children().removeClass("show");
    $(this).children().last().toggleClass("show")
});
$(document).on("mouseup", "body", function (a) {
    a.stopPropagation();
    let d = $(".messages .message-item .message-list-action");
    d.is(a.target) || 0 !== d.has(a.target).length || d.children().removeClass("show")
});
$(document).ready(function () {
    window.localStorage && localStorage.getItem("_customMode") && $("body").addClass(localStorage.getItem("_customMode"));
    $("#customSwitchMode").click(function () {
        1 == $(this).prop("checked") ? ($("body").addClass("dark"), window.localStorage && localStorage.setItem("_customMode", "dark")) : $("body").removeClass("dark")
    });
    $("#deleteModal").on("show.bs.modal", function (a) {
        let d = $(this).find("#btnRemove");
        d.attr("data-messageid", $(a.relatedTarget).parents(".messages .message-item").data("messageid"));
        d.attr("data-userid", $(a.relatedTarget).parents(".messages .message-item").data("userid"))
    });
    $('#chat-message textarea[name="body"]').each(function () {
        this.setAttribute("style", "overflow-y:hidden;")
    }).on("input", function () {
        this.style.height = "auto";
        this.style.height = this.scrollHeight + "px"
    });
    $(".nav-group ul li a").click(function () {
        $(".nav-group ul li a").removeClass("active");
        $(this).addClass("active")
    });
    992 > screen.width && $(".navigation .nav-group .dropdown-hotel").removeClass("dropright").addClass("dropup")
});