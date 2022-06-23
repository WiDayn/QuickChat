package Net.Request;

import Chat.Message;
import Chat.Room;

import java.io.Serial;
import java.sql.Timestamp;

public class SendMessageRequest extends Request{
    @Serial
    private static final long serialVersionUID = -2483948143061662330L;
    private Message message;

    private Room room;

    public SendMessageRequest(Timestamp sendTime, Message message, Room room) {
        super(sendTime, "SendMsg");
        this.message = message;
        this.room = room;
    }

    public Message getMessage() {
        return message;
    }

    public Room getRoom() {
        return room;
    }
}
