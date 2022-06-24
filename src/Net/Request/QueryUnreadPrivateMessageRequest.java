package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class QueryUnreadPrivateMessageRequest extends Request implements Serializable {

    @Serial
    private static final long serialVersionUID = -4492048229430225396L;

    private final String userid;

    public QueryUnreadPrivateMessageRequest(Timestamp sendTime, String userid) {
        super(sendTime, "QueryUnreadPrivateMessage");
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }
}
