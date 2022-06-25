package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class QueryRoomRequest extends Request implements Serializable {
    @Serial
    private static final long serialVersionUID = -5970984285966095676L;
    private String userId;

    public QueryRoomRequest(Timestamp sendTime, String userId) {
        super(sendTime, "QueryRoom");
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
