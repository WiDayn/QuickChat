package Net.Feedback;

import Chat.Message;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PullMessageFeedback extends Feedback {

    @Serial
    private static final long serialVersionUID = -6085317754156139551L;
    List<Message> messages = new ArrayList<>();

    public PullMessageFeedback(Timestamp sendTime){
        super(sendTime, "pullMsg");
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
