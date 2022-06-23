package Net.Feedback;

import Chat.Message;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PullMessageFeedback extends Feedback {

    @Serial
    private static final long serialVersionUID = -6085317754156139551L;
    private List<Message> messages = new ArrayList<>();

    int roomId;

    public PullMessageFeedback(Timestamp sendTime, int roomId){
        super(sendTime, "pullMsg");
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
