package Chat;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 2461736447946049838L;

    int id;
    String userid;

    String nickName;

    String avatar;

    List<Room> roomList;

    public User(int id, String userid, String nickName, String avatar) {
        this.id = id;
        this.userid = userid;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
