package Net.Request;

import java.io.Serializable;
import java.sql.Timestamp;

public class QueryRoomRequest extends Request implements Serializable {
    private static final long serialVersionUID = -5970984285966095676L;
    String userId;

    public QueryRoomRequest(Timestamp sendTime, String type, String userId) {
        super(sendTime, type);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
