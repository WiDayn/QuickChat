package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class JoinRoomRequest extends Request implements Serializable {

    @Serial
    private static final long serialVersionUID = -5537376888806612327L;

    private String userid;
    private int roomId;

    public JoinRoomRequest(Timestamp sendTime, String type, String userid, int roomId) {
        super(sendTime, type);
        this.userid = userid;
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getUserid() {
        return userid;
    }
}
