package Net.Feedback;

import Chat.Room;
import Chat.User;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class QueryRoomFeedback extends Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 3731817462768860430L;
    private List<Room> roomList;

    public QueryRoomFeedback(Timestamp sendTime, List<Room> roomList) {
        super(sendTime, "QueryRoom");
        this.roomList = roomList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
