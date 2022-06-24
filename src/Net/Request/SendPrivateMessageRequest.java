package Net.Request;

import Chat.PrivateMessage;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class SendPrivateMessageRequest extends Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 5969749481041597647L;

    private final PrivateMessage privateMessage;

    public SendPrivateMessageRequest(Timestamp sendTime, PrivateMessage privateMessage) {
        super(sendTime, "SendPrivateMessage");
        this.privateMessage = privateMessage;
    }

    public PrivateMessage getPrivateMessage() {
        return privateMessage;
    }
}
