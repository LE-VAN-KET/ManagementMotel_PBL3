function MessageSocket() {
    // socket api send comment
    let websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/message/" + senderId);
        window.onbeforeunload = function () {
            websocket.onclose();
        };

        websocket.onopen = message => {
            // console.log("Server connect...");
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({messageType : 'LIST_LAST_MESSAGE'}));
                websocket.send(JSON.stringify({messageType : 'NOTIFICATION_MSG'}));
                count_no_seen = 0;
            }
        }

        websocket.onmessage = message => {
            let data = JSON.parse(message.data);
            switch (data.messageType) {
                case "ADD":
                    addMessage(data);
                    break;
                case "DELETE":
                    delMessage(data);
                    break;
                case "MSG_HISTORY":
                    historyMessage(data);
                    break;
                case "LIST_LAST_MESSAGE":
                    listLastMessage(data);
                    break;
                case "LIST_USERS_ONLINE":
                    listUsersOnline(data);
                    break;
                case "REFRESH_USERS_ONLINE":
                    refreshUsersOnline();
                    break;
                case "NOTIFICATION_MSG":
                    notificationMessage(data);
                    break;
            }
        }

        websocket.onclose = message => {
            console.log("Server Disconnect...");
        }
        websocket.onerror = message => {
            console.log("error" + message);
        }

        // submit form add comment
        $("#chat-message").submit( function (e) {
            let body = $.trim($("#chat-message textarea[name='body']").val());
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN && body != "") {
                websocket.send(JSON.stringify({
                    body : body,
                    senderId : senderId,
                    recipientId: $(this).attr("data-recipientid"),
                    messageType : 'ADD'
                }));
                let eleBody = $('#chat-message textarea[name="body"]');
                eleBody.val('');
                eleBody.focus();
                eleBody.height('auto');
            }
            e.preventDefault();
        });


        $("#btnRemove").click(function () {
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({
                    messageId : $(this).attr("data-messageid"),
                    senderId : $(this).attr("data-userid"),
                    messageType : 'DELETE'
                }));
                $('#deleteModal').modal('toggle');
            }
        })

        $(document).on('click', '.list-group .list-group-item', function() {
            $(".list-group .open-chat").removeClass("open-chat");
            $(this).addClass("open-chat");
            $("#currentChattingName").text($(this).data("fullname"));
            $("#chat-message").attr("data-recipientid", $(this).data("recipientid"));
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({
                    recipientId : $(this).data("recipientid"),
                    senderId : senderId,
                    messageType : 'MSG_HISTORY'
                }));
                recipientId = $(this).data("recipientid");
                $("#chat-message textarea[name='body']").focus();
            }
        })

        $('#chat-message textarea[name="body"]').focus(function () {
            let notifyMsg = $(".list-group .open-chat .new-message-count");
            if (count_no_seen !== 0 && notifyMsg.length !== 0 && (typeof websocket != 'undefined')
                && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({
                    recipientId: $("#chat-message").attr("data-recipientid"),
                    messageType: 'UPDATE_MSG_SEEN'
                }));
                websocket.send(JSON.stringify({messageType : 'NOTIFICATION_MSG'}));
            }
        })

        function checkConversation(data) {
            let sender_id = data.message.sender.userId,
                recipient_id = data.message.recipient.userId;
            return ((sender_id == senderId && recipient_id == recipientId) ||
                (sender_id == recipientId && recipient_id == senderId)) ? true: false;
        }


        // recieved msg add message
        const addMessage = data => {
            if (!checkConversation(data)) return;
            if (data.errors) {
                data.errors.forEach(err => {
                    toastr.error(err);
                })
            } else {
                let type = (data.message.sender.userId === senderId) ?  'outgoing-message': null;
                Message.add(data, type);
                // if (!data.message.isSeen && data.message.sender.userId != senderId) count_no_seen++;
            }
        }

        // recieved msg delete comment
        const delMessage = data => {
            if (!checkConversation(data)) return;
            if (data.errors) {
                toastr.error(data.errors, "Delete message failed");
            } else {
                Message.delete(data, senderId);
            }
        }

        const historyMessage = data => {
            // console.log(data);
            count_no_seen = 0;
            $(".layout .content .chat .chat-body .messages .message-item").remove();
            data.messages.forEach(message => {
                let msg = {
                    message: message
                };
                addMessage(msg);
            })

        }

        const listLastMessage = data => {
            $(".list-group .list-group-item").remove();
            // console.log(data);
            data.listLastMessage.forEach(message => {
                if (senderId === message.sender.userId || senderId === message.recipient.userId) {
                    let recipient_id, fullName;
                    if (message.sender.userId === senderId) {
                        recipient_id = message.recipient.userId;
                        fullName = message.recipient.fullName;
                    } else {
                        recipient_id = message.sender.userId;
                        fullName = message.sender.fullName;
                    }

                    let action_sidebar = (recipient_id == recipientId) ? ' open-chat': '';

                    $(".list-group").append('<li class="list-group-item' + action_sidebar + '" data-senderId=' + senderId +
                        ' data-recipientId=' + recipient_id + ' data-fullName="' + fullName + '"><figure class="avatar">' +
                        '<img class="rounded-circle" src="/assets/images/143086968_2856368904622192_1959732218791162458_n.png"/></figure>' +
                        '<div class="users-list-body"><h5>' + fullName + '</h5><p>' + message.body + '</p><div' +
                        ' class="users-list-action action-toggle"><div class="dropdown"><a data-toggle="dropdown" ' +
                        'href="#"><i class="ti-more"></i></a> <div class="dropdown-menu dropdown-menu-right">' +
                        '<a class="dropdown-item" href="#">Open</a></div></div></div></div>');
                }
            })

            if (!$("#chat-message").attr("data-recipientid")) {
                // console.log("open-chat");
                if (!recipientId) {
                    recipientId = $(".list-group .list-group-item").first().data("recipientid");
                    currentChattingName = $(".list-group .list-group-item").first().data("fullname");
                    $(".list-group .list-group-item").first().addClass("open-chat");
                }

                if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                    websocket.send(JSON.stringify({
                        recipientId : recipientId,
                        senderId : senderId,
                        messageType : 'MSG_HISTORY'
                    }));

                    websocket.send(JSON.stringify({messageType : 'LIST_USERS_ONLINE'}));
                    websocket.send(JSON.stringify({messageType : 'REFRESH_USERS_ONLINE'}));
                }
                $("#currentChattingName").text(currentChattingName);
                $("#chat-message").attr("data-recipientid", recipientId);
                $("#chat-message textarea[name='body']").focus();
            } else {
                // event reload list users online when add or delete message
                if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                    websocket.send(JSON.stringify({messageType : 'LIST_USERS_ONLINE'}));
                }
            }
        }

        const listUsersOnline = data => {
            // console.log(data)
            $(".list-group .list-group-item").each(function () {
                let element = $(this).find("figure.avatar").first();
                element.removeClass("avatar-state-success");
                data.listUsersOnline.forEach(user_id => {
                    if ($(this).data("recipientid") == user_id) {
                        element.addClass("avatar-state-success");
                    }
                })
            })
        }

        const refreshUsersOnline = () => {
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({messageType : 'LIST_USERS_ONLINE'}));
            }
        }

        const notificationMessage = data => {
            count_no_seen = 0;
            $(".list-group .list-group-item .new-message-count").remove();
            data.notificationMessages.forEach(notification => {
                count_no_seen += notification.amount;
                $(".list-group .list-group-item").each(function () {
                    if ($(this).data("recipientid") == notification.recipinetId && notification.amount != 0) {
                        $(this).find(".users-list-body p").first().after('<div class="new-message-count float-right">' +
                            notification.amount + '</div>');
                    }
                })
            })
            $("#chat-message textarea[name='body']").focus();
        }

        $("#chat-message textarea[name='body']").on("keyup", function(event) {
            // Number 13 is the "Enter" key on the keyboard
            if (event.keyCode === 13) {
                event.preventDefault();
                // Trigger the button element with a submit
                $("#chat-message").submit();
            }
        });
    } else {
        toastr.info('The browser does not support websocket');
    }

    // setTimeout(function () {
    //     Message.add();
    //     Message.delete();
    // }, 1000);
}

function DateFormatter(time) {
    switch (typeof time) {
        case 'number':
            break;
        case 'string':
            time = +new Date(time);
            break;
        case 'object':
            // if (time.constructor === Date)
            break;
        default:
            time = +new Date();
    }
    let now = new Date();
    let diffDays = now.getDay() - time.getDay(),
        diffMonths = now.getMonth() - time.getMonth(),
        diffYears = now.getFullYear() - time.getFullYear();
    if (diffDays === 0 && diffMonths === 0   && diffYears === 0) {
        return 'Today, ' + time.toLocaleString('en-US', {hour: '2-digit', minute: '2-digit'});
    }

    if ((diffDays === 1) && diffMonths === 0 && diffYears === 0) {
        return 'Yesterday, ' + time.toLocaleString('en-US', {hour: '2-digit', minute: '2-digit'});
    }

    if (diffDays < 4 && diffMonths === 0 && diffYears === 0) {
        return time.toLocaleString('en-US', {weekday: "long", hour: "2-digit", minute: "2-digit"});
    }

    let options = {
        year: "numeric", month: "short", day: "numeric", hour: "2-digit", minute: "2-digit"
    };

    return time.toLocaleString('en-US', options);
}

const Message = {
    add: function (data, type) {
        // console.log(data);
        let chat_body = $('.layout .content .chat .chat-body');
        let datetime = DateFormatter(new Date(data.message.createAt));
        if (chat_body.length > 0) {
            type = type ? type : '';
            $('.layout .content .chat .chat-body .messages').append('<div class="message-item ' + type +
                '" data-messageid=' + data.message.messageId + ' data-userid=' + data.message.sender.userId + '>' +
                '<div class="d-flex flex-row flex-nowrap justify-content-' + (type?'end': 'start') + ' align-items-center">' +
                (type ? '<div class="message-list-action action-toggle popup"><a class="btn-action" href="#">' +
                    '<i class="ti-more"></i></a><div class="active-menu"><a class="action-item text-muted" ' +
                    'data-toggle="modal" data-target="#deleteModal" href="javascript:void(0)">Delete</a></div>' +
                    '</div>' : '') +
                '<div class="message-content '+ (type?'ml-2':'') +'">' + (data.message.body) + '</div></div>' +
                '<div class="message-action ' + (data.message.isSeen ? 'had-seen': 'not-seen') + '">' +
                (type ? (datetime + '<i class="ti-check ml-1"></i>') : (datetime)) +
                '</div></div>');

            chat_body.scrollTop(chat_body.get(0).scrollHeight, -1).niceScroll({
                cursorcolor: 'rgba(66, 66, 66, 0.20)',
                cursorwidth: "4px",
                cursorborder: '0px'
            }).resize();
        }
    },

    delete: function (data, senderId) {
        // console.log(data);
        $(".messages .message-item").each(function () {
            if ($(this).data("messageid") == data.message.messageId) {
                $(this).remove();
                if (senderId == data.message.sender.userId) {
                    toastr.success("delete message successfully");
                }
            }
        })
    }
}

$(document).on('click', '.layout .content .sidebar-group .sidebar .list-group-item', function () {
    if (jQuery.browser.mobile) {
        $(this).closest('.sidebar-group').removeClass('mobile-open');
    }
});

$(document).on('click', '.messages .message-item .message-list-action', function() {
    $(".messages .message-item .message-list-action").children().removeClass("show");
    $(this).children().last().toggleClass("show");
})

$(document).on( "mouseup", "body", function(e) {
    e.stopPropagation();
    let $action = $(".messages .message-item .message-list-action");
    if (!$action.is(e.target) && $action.has(e.target).length === 0)
    {
        $action.children().removeClass("show");
    }
});

$(document).ready(function () {
    if (window.localStorage && localStorage.getItem("_customMode")) {
        $("body").addClass(localStorage.getItem("_customMode"));
    }

    $("#customSwitchMode").click(function () {
        if ($(this).prop("checked") == true) {
            $("body").addClass("dark");
            if (window.localStorage) {
                localStorage.setItem("_customMode", "dark");
            }
        } else {
            $("body").removeClass("dark");
        }
    })

    $('#deleteModal').on('show.bs.modal', function(e) {
        let btn = $(this).find('#btnRemove');
        btn.attr('data-messageid', $(e.relatedTarget).parents(".messages .message-item").data("messageid"));
        btn.attr('data-userid', $(e.relatedTarget).parents(".messages .message-item").data("userid"));
    });

    $('#chat-message textarea[name="body"]').each(function () {
        this.setAttribute('style', 'overflow-y:hidden;');
    }).on('input', function () {
        this.style.height = 'auto';
        this.style.height = (this.scrollHeight) + 'px';
    });

    $(".nav-group ul li a").click(function () {
        $(".nav-group ul li a").removeClass("active");
        $(this).addClass("active");
    })

    if (screen.width < 992) {
        $('.navigation .nav-group .dropdown-hotel').removeClass("dropright").addClass("dropup");
    }
})

