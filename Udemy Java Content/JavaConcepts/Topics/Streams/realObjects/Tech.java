package Streams.realObjects;

import java.util.List;

public class Tech {
    private List<String> name;

    public Tech(List<String> name) {
        this.name = name;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tech:"+ name + "";
    }
}
