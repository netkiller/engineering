package cn.netkiller.gantt.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Rectangle {
    int x;
    int y;
    int width;
    int height;

    private Element element;
    private String fill;

    public Rectangle(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public Rectangle fill(String fill) {
        this.fill = fill;
        return this;
    }

    public Element createElement(Document xml) {
        this.element = xml.createElement("rect");
//        element.setAttribute("id", t.id);
        this.element.setAttribute("x", String.valueOf(this.x));
        this.element.setAttribute("y", String.valueOf(this.y));
        this.element.setAttribute("width", String.valueOf(this.width));
        this.element.setAttribute("height", String.valueOf(this.height));
        if(this.fill != null){
            this.element.setAttribute("fill", String.valueOf(fill));
        }
        return element;
    }
}
