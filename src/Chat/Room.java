package Chat;

import Net.Feedback.RegisterFeedback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {

    private static final long serialVersionUID = -1695936838040146977L;

    private int id;
    private String name;
    private List<User> UserList = new ArrayList<>();
    private List<Message> message = new ArrayList<>();

    public Room(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessage() {
        return message;
    }

    public List<User> getUserList() {
        return UserList;
    }

    @Override
    public boolean equals(Object obj){
        // ÷ÿ–¥œ‡µ»≈–∂œ
        if(obj instanceof Room){
            return id == ((Room) obj).id;
        }
        return false;
    }
}
