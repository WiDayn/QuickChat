package Chat;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 2461736447946049838L;
    String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
