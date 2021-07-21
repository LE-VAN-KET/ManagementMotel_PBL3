// socket api send notification message
function notificationMsg(senderId) {
    let websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/message/" + senderId);
        window.onbeforeunload = function () {
            websocket.onclose();
        };

        websocket.onopen = message => {
            // console.log("Server connect...");
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(JSON.stringify({messageType : 'TOTAL_NOTIFICATION_MSG'}));
            }
        }

        websocket.onmessage = message => {
            let data = JSON.parse(message.data);
            switch (data.messageType) {
                case "TOTAL_NOTIFICATION_MSG":
                    totalNotificationMessage(data);
                    break;
            }
        }

        websocket.onclose = message => {
            console.log("Server Disconnect...");
        }
        websocket.onerror = message => {
            console.log("error" + message);
        }

        const totalNotificationMessage = data => {
            $("a.message .notify-message-count").remove();
            if (data.countMesssage !== 0) {
                $("a.message").first().append("<div class=\"notify-message-count float-right\">" + data.countMesssage
                    + "</div>");
            }
        }
    }
}