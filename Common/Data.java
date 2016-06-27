package Common;


import java.io.Serializable;

/**
 * Created by Majid Vaghari on 6/26/2016.
 */
public class Data implements Serializable {
    private           int    id;
    private           String name;
    private transient long   sampleTransientProperty;

    public Data(int id, String name, long sampleTransientProperty) {
        this.id = id;
        this.name = name;
        this.sampleTransientProperty = sampleTransientProperty;
    }

    @Override
    public String toString() {
        return "Data{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", sampleTransientProperty=" + sampleTransientProperty +
               '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getSampleTransientProperty() {
        return sampleTransientProperty;
    }
}
