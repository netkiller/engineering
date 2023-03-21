package cn.netkiller.gantt;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class Svg {
    //    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
//    width="1980" height="1080" viewBox="0 0 1980 1080">
    private Document xml;
    private Element svg;

    public Svg() throws ParserConfigurationException {
        //得到DOM解析器的工厂实例
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        //从DOM工厂中获得DOM解析器
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        //创建文档树模型对象
        this.xml = dbBuilder.newDocument();
        this.svg = this.xml.createElement("svg");
        this.svg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
        this.svg.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
        this.svg.setAttribute("width", "1980");
        this.svg.setAttribute("height", "1080");
        this.svg.setAttribute("viewBox", "0 0 1980 1080");
    }

    public Element group(String id) {
        Element element = this.xml.createElement("g");
        element.setAttribute("id", id);
//        this.svg.appendChild(element);
        return element;
    }
    public Element a(A a) {
        Element element = this.xml.createElement("a");
//        element.setAttribute("id", t.id);
        element.setAttribute("xlink:href", String.valueOf(a.href()));
        element.setAttribute("target", String.valueOf(a.target()));
//        element.setAttribute("font-size", String.valueOf(t.size()));
//        element.appendChild(this.xml.createTextNode(t.text()));
        return element;
    }
    public Element text(Text t) {
        Element element = this.xml.createElement("text");
//        element.setAttribute("id", t.id);
        element.setAttribute("x", String.valueOf(t.x()));
        element.setAttribute("y", String.valueOf(t.y()));
        element.setAttribute("font-size", String.valueOf(t.size()));
        element.appendChild(this.xml.createTextNode(t.text()));
        return element;
    }
    public Element title(String text){
        Element element = this.xml.createElement("title");
        element.appendChild(this.xml.createTextNode(text));
        return element;
    }
    public Element script(String text){
        Element element = this.xml.createElement("script");
        element.appendChild(this.xml.createTextNode("// <![CDATA["));
        element.appendChild(this.xml.createTextNode(text));
        element.appendChild(this.xml.createTextNode("// ]]>"));
        return element;
    }
    public Element style(String text){
        Element element = this.xml.createElement("style");
        element.appendChild(this.xml.createTextNode("/* <![CDATA[ */"));
        element.appendChild(this.xml.createTextNode(text));
        element.appendChild(this.xml.createTextNode("/* ]]> */"));
        return element;
    }
    public Element appendChild(Node element) {
        this.svg.appendChild(element);
        return this.svg;
    }

    public Element rectangle(Rect rect) {
        Element element = this.xml.createElement("rect");
//        element.setAttribute("id", t.id);
        element.setAttribute("x", String.valueOf(rect.x()));
        element.setAttribute("y", String.valueOf(rect.y()));
        element.setAttribute("width", String.valueOf(rect.width()));
        element.setAttribute("height", String.valueOf(rect.height()));
        element.setAttribute("fill", String.valueOf(rect.fill()));
//        element.appendChild(this.xml.createTextNode(t.text()));
        return element;
    }
    public Element line(Line line) {
        Element element = this.xml.createElement("rect");
//        element.setAttribute("id", t.id);
        element.setAttribute("x", String.valueOf(line.x1()));
        element.setAttribute("y", String.valueOf(line.y1()));
        element.setAttribute("width", String.valueOf(line.x2()));
        element.setAttribute("height", String.valueOf(line.y2()));
//        element.setAttribute("fill", String.valueOf(line.fill()));
//        element.appendChild(this.xml.createTextNode(t.text()));
        return element;
    }
    public void createDom() {

        Element school, student;
        Element name = null;
        Element num = null;
        Element classes = null;
        Element address = null;
        Element tel = null;
        try {

            if (this.xml != null) {

                this.xml.appendChild(this.svg);

                StringWriter sw = new StringWriter();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(new DOMSource(this.xml), new StreamResult(sw));
                System.out.println(sw.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {


        StringWriter sw = new StringWriter();
        try {

            if (this.xml != null) {
                this.xml.appendChild(this.svg);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(new DOMSource(this.xml), new StreamResult(sw));
                System.out.println(sw.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static void main(String[] args) {
        try {
            Svg svg = new Svg();
            Node element = svg.group("test");
            element.appendChild(svg.text(new Text("Text", 5, 5, 10)));
//            Node element = svg.text("Text");
            svg.appendChild(element);
            svg.appendChild(svg.rectangle(new Rect(1, 1, 10, 10)));
            svg.appendChild(svg.script("he"));
            svg.createDom();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
