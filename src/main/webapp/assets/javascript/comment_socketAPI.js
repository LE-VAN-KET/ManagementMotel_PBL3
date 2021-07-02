function CommentSocketAPI(postId, userId) {

    // socket api send comment
    let websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/comment/" + postId);
        websocket.onopen = message => processOpen(message);
        websocket.onmessage = message => processMessage(message);
        websocket.onclose = message => processClose(message);
        websocket.onerror = message => processError(message);
        window.onbeforeunload = function () {
            websocket.close();
        };

        function processOpen(message) {
            // console.log("Server connect...");
        }
        function processMessage(message) {
            let data = JSON.parse(message.data);
            // console.log(data)
            if (typeof (data.urlRedirect) != 'undefined') {
                location.replace(data.urlRedirect);
                return;
            }
            switch (data.method) {
                case "add":
                    addComment(data);
                    break;
                case "delete":
                    delComment(data);
                    break;
                case "update":
                    updateComment(data);
                    break;
            }


        }
        function processClose(message) {
            // textAreaMessage.value += "Server Disconnect... \n";
            console.log("Server Disconnect...");
        }
        function processError(message) {
            // textAreaMessage.value += "Error... " + message +" \n";
            console.log("error" + message);
        }

        // submit form add comment
        $("#formComment").submit( function (e) {
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({
                    content : $.trim($("#formComment textarea[name='content']").val()),
                    method : 'add'
                }));
                $('textarea[name="content"]').val('');
                $('textarea[name="content"]').height('auto');
            }
            e.preventDefault();
        });

        //submit form edit comment
        $("#formEdit").submit(function (e) {
            let content = $.trim($("#formEdit textarea[name='content']").val());
            if (content === "") {
                // confirm delete comment
                elementRemoveComment.click();
            } else {
                // send message update content
                if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                    websocket.send(JSON.stringify({
                        commentId : $("#formEdit").attr("data-commentid"),
                        content : content,
                        method : 'update'
                    }));
                }
            }
            $("#formEdit").css({"display": "none"});
            e.preventDefault();
        })

        $("#btnRemove").click(function () {
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({
                    commentId : $(this).attr("data-commentid"),
                    method : 'delete'
                }));
                $('#confirm-delete').modal('toggle');
            }
        })

        // recieved msg add comment
        const addComment = data => {
            if (data.errors) {
                data.errors.forEach(err => {
                    toastr.error(err, "Comment failed");
                })
            } else {
                Comments.add(data);
            }
        }

        // recieved msg delete comment
        const delComment = data => {
            if (data.errors) {
                toastr.error(data.errors, "Delete comment failed");
            } else {
                Comments.delete(data);
            }
        }

        const updateComment = data => {
            if (data.errors) {
                toastr.error(data.errors, "Update comment failed");
            } else {
                Comments.update(data);
            }
        }

        $("#formComment textarea[name='content']").on("keyup", function(event) {
            // Number 13 is the "Enter" key on the keyboard
            if (event.keyCode === 13) {
                event.preventDefault();
                // Trigger the button element with a submit
                $("#formComment").submit();
            }
        });

        $("#formEdit textarea[name='content']").on("keyup", function(event) {
            // Number 13 is the "Enter" key on the keyboard
            if (event.keyCode === 13) {
                event.preventDefault();
                // Trigger the button element with a submit
                $("#formEdit").submit();
            }
        });
    } else {
        toastr.info('The browser does not support websocket');
    }

    const Comments = {
        add: function (data) {
            let commentAction = '';
            let commentAuthor = '';
            if (userId == data.comment.userModel.userId) {
                commentAction += '<div class="comments-list-action action-toggle bg-transparent">' +
                    '<div class="dropdown"><a href="#" data-toggle="dropdown">' +
                    '<i class="fas fa-ellipsis-h"></i></a><div class="dropdown-menu dropdown-menu-right">' +
                    '<a class="dropdown-item text-dark" href="javascript:void(0)" onclick="editComment(this)">' +
                    '<i class="far fa-edit mr-2"></i>Edit</a>' +
                    '<a class="dropdown-item text-dark" href="javascript:void(0)" data-toggle="modal" ' +
                    'data-target="#confirm-delete">' + '<i class="fas fa-trash mr-2"></i>Remove</a>' +
                    '</div></div></div>';
                commentAuthor += 'by-author';

            }
            $("#comments-list").append('<li><div class="comment-main-level row m-0" data-userid=' +
                data.comment.userModel.userId + ' data-commentid=' + data.comment.commentId +
                '><div class="comment-avatar col-1 p-0">' +
                '<img class="img-fluid img-thumbnail lazyload p-0 "' +
                'data-src="/assets/images/143086968_2856368904622192_1959732218791162458_n.png" ' +
                'alt="image..."></div><div class="comment-box col-10"><div class="comment-head">' +
                '<h6 class="comment-name ' + commentAuthor + '"><a href="#">' + data.comment.userModel.fullName +
                '</a></h6><span class="posted-time">Posted on ' + data.createAt + '</span>' + commentAction +
                '</div><div class="comment-content"><p class="p-0 m-0">' + data.comment.content +
                '</p></div></div></div></li>');
        },
        update: function (data) {
            $(".comment-main-level").each(function () {
                if ($(this).data("commentid") == data.comment.commentId) {
                    $(this).find(".post-time").text('Updated on ' + data.modifiedAt);
                    $(this).find(".comment-content p").text(data.comment.content);
                    if (userId == data.comment.userModel.userId) {
                        toastr.success("update comment successfully");
                    }
                }
            })
        },
        delete: function (data) {
            $(".comment-main-level").each(function () {
                if ($(this).data("commentid") == data.comment.commentId) {
                    $(this).remove();
                    if (userId == data.comment.userModel.userId) {
                        toastr.success("delete comment successfully");
                    }
                }
            })
            $("#formEdit").css({"display": "none"});
        }
    }
}
