package cn.netkiller.gantt.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.Objects;

public class Text {
    String text;
    int x;
    int y;
    int size;
    private String fill;

    public Text(String text, int x, int y, int size) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;

    }

    public Text fill(String fill) {
        this.fill = fill;
        return this;
    }

    public Element createElement(Document xml) {
        Element element = xml.createElement("text");
//        element.setAttribute("id", t.id);
        element.setAttribute("x", String.valueOf(this.x));
        element.setAttribute("y", String.valueOf(this.y));
        element.setAttribute("font-size", String.valueOf(this.size));
//        element.setAttribute("fill", String.valueOf(this.fill));
        if (this.fill != null) {
            element.setAttribute("fill", String.valueOf(fill));
        }
        element.appendChild(xml.createTextNode(this.text));
        return element;
    }
}
