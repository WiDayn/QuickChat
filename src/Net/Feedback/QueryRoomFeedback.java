package Net.Feedback;

import Chat.Room;
import Chat.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class QueryRoomFeedback extends Feedback implements Serializable {


    List<Room> roomList;

    public QueryRoomFeedback(Timestamp sendTime, String type, List<Room> roomList) {
        super(sendTime, type);
        this.roomList = roomList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
