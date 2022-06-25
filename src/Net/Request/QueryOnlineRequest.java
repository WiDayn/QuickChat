package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class QueryOnlineRequest extends Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 2484595907742515202L;

    private int roomId;

    public QueryOnlineRequest(Timestamp sendTime, int roomId) {
        super(sendTime, "QueryOnline");
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }
}
