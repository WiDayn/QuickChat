package Net.Request;

import Chat.Message;

import java.io.Serial;
import java.sql.Timestamp;

public class SendMessageRequest extends Request{
    @Serial
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
