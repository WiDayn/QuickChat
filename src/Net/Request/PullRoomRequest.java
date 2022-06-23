package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class PullRoomRequest extends Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 6410279842323917594L;
    private String userid;
    private int roomId;

    public PullRoomRequest(Timestamp sendTime, String userid, int roomId) {
        super(sendTime, "PullRoom");
        this.userid = userid;
        this.roomId = roomId;
    }

    public String getUserid() {
        return userid;
    }

    public int getRoomId() {
        return roomId;
    }
}
