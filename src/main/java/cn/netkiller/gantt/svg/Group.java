package cn.netkiller.gantt.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Group {
    public String id;

    private String fill;

    public Group(String id) {
        this.id = id;
    }

    public Element createElement(Document xml) {
        Element element = xml.createElement("g");
        element.setAttribute("id", this.id);
//        this.element.setAttribute("x", String.valueOf(this.x));
//        this.element.setAttribute("y", String.valueOf(this.y));
//        this.element.setAttribute("width", String.valueOf(this.width));
//        this.element.setAttribute("height", String.valueOf(this.height));
        if(this.fill != null){
            element.setAttribute("fill", String.valueOf(fill));
        }
        return element;
    }
}
