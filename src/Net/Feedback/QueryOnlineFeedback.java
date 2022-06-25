package Net.Feedback;

import Chat.User;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class QueryOnlineFeedback extends Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 4542429544352514775L;

    private int roomId;
    private List<User> userList;

    public QueryOnlineFeedback(Timestamp sendTime, int roomId, List<User> userList) {
            super(sendTime, "QueryOnline");
        this.roomId = roomId;
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
