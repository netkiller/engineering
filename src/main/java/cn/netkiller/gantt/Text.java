package cn.netkiller.gantt;

import java.io.Serializable;
import java.util.Objects;

public record Text(String text, int x, int y, int size, String fill) implements Serializable {
    public Text {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
    }
    public Text(String text, int x, int y, int size) {
        this(text, x,y, size,"#000000");
    }
}
