package Net.Request;

import Chat.Message;
import Chat.User;

import java.sql.Timestamp;
import java.util.Date;

public class SendMessageRequest extends Request{
    private static final long serialVersionUID = -2483948143061662330L;
    Message message;

    public SendMessageRequest(Timestamp sendTime, Message message) {
        super(sendTime, "SendMsg");
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
