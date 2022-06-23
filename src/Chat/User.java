package Chat;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.TimerTask;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 2461736447946049838L;

    int id;
    String userid;

    String nickName;

    String avatar;

    Timestamp lastActive;

    List<Room> roomList;

    public User(int id, String userid, String nickName, String avatar, Timestamp lastActive) {
        this.id = id;
        this.userid = userid;
        this.nickName = nickName;
        this.avatar = avatar;
        this.lastActive = lastActive;
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

    public Timestamp getLastActive() {
        return lastActive;
    }

    public void setRoomList(List<Room> roomList){
        this.roomList = roomList;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof  User){
            return userid.equals(((User) obj).userid);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return userid.hashCode();
    }
}
