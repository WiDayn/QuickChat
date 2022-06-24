package Net.Feedback;

import Chat.PrivateMessage;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class QueryUnreadPrivateMessageFeedback extends Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = -1072651546553682616L;

    List<PrivateMessage> privateMessageList;

    public QueryUnreadPrivateMessageFeedback(Timestamp sendTime, List<PrivateMessage> privateMessageList) {
        super(sendTime, "QueryUnreadPrivateMessage");
        this.privateMessageList = privateMessageList;
    }

    public List<PrivateMessage> getPrivateMessageList() {
        return privateMessageList;
    }
}
