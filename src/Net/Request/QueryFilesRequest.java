package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class QueryFilesRequest extends Request implements Serializable {
    @Serial
    private static final long serialVersionUID = -2249026207990340822L;

    private int roomId;

    public QueryFilesRequest(Timestamp sendTime, int roomId) {
        super(sendTime, "QueryFiles");
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }
}
