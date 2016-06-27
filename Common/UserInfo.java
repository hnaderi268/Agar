package Common;

import java.io.Serializable;

public class UserInfo implements Serializable{
    public           String name;

    public UserInfo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "user{" +
               ", name='" + name + '\'' +
               '}';
    }

    public String getName() {
        return name;
    }
}
