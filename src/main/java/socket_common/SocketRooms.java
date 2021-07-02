package socket_common;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SocketRooms {
    public static void connection(String nameRoom,final Map<String, Set<Session>> rooms, Session session) {
        if (!rooms.containsKey(nameRoom)) {
            // Create a room if the room does not exist
            Set<Session> room = new HashSet<>();
            room.add(session); //Add user
            rooms.put(nameRoom, room);
        } else {
            // The room already exists, add users directly to the corresponding room
            rooms.get(nameRoom).add(session);
        }
    }
}
