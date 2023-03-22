package cn.netkiller.gantt.html;

import cn.netkiller.gantt.Gantt;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class Html {
    private Document document;
    private Element element;

    public Html() {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //创建文档树模型对象
            this.document = dbBuilder.newDocument();
            this.element = this.document.createElement("html");
            this.element.setAttribute("xmlns", "http://www.w3.org/2000/element");
            this.element.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
//            this.element.setAttribute("width", "1980");
//            this.element.setAttribute("height", "1080");
//            this.element.setAttribute("viewBox", "0 0 1980 1080");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public Html head() {
        Element element = this.document.createElement("head");
        this.element.appendChild(element);
        return this;
    }

    public Html body() {
        Element element = this.document.createElement("body");

        element.appendChild(this.project(this.table()));

        this.element.appendChild(element);
        return this;
    }
    public Element table(){
        Element table = this.document.createElement("table");
        Element thead = this.document.createElement("thead");
        Element tbody = this.document.createElement("tbody");
        Element tfoot = this.document.createElement("tfoot");
        Element th = this.document.createElement("th");
        Element tr = this.document.createElement("tr");
        Element td = this.document.createElement("td");


//                Element element = this.xml.createElement("任务");
////
//        element.setAttribute("y", String.valueOf(t.y()));
//        element.setAttribute("font-size", String.valueOf(t.size()));


        th.appendChild(this.document.createTextNode("任务"));
        tr.appendChild(th);

        th = this.document.createElement("td");
        th.appendChild(this.document.createTextNode("开始时间"));
        tr.appendChild(th);

        th = this.document.createElement("td");
        th.appendChild(this.document.createTextNode("结束时间"));
        tr.appendChild(th);

        th = this.document.createElement("td");
        th.appendChild(this.document.createTextNode("资源"));
        tr.appendChild(th);

        thead.appendChild(tr);
        table.appendChild(thead);

        tr = this.document.createElement("tr");

        td = this.document.createElement("td");
        td.appendChild(this.document.createTextNode("甘特图开发"));
        tr.appendChild(td);

        td = this.document.createElement("td");
        Element input = this.document.createElement("input");
        input.setAttribute("id", "1");
        input.setAttribute("value", "2023-03-01");
        td.appendChild(input);
        tr.appendChild(td);

        td = this.document.createElement("td");
        input = this.document.createElement("input");
        input.setAttribute("id", "1");
        input.setAttribute("value", "2023-03-01");
        td.appendChild(input);
        tr.appendChild(td);

        td = this.document.createElement("td");
        td.appendChild(this.document.createTextNode("netkiller"));
        tr.appendChild(td);

        tbody.appendChild(tr);
        table.appendChild(tbody);
        return table;
    }
    public Node project(Element task){
        Element table = this.document.createElement("table");
        Element tr = this.document.createElement("tr");
        Element td = this.document.createElement("td");
        td.appendChild(task);
        Gantt gantt = new Gantt();
        System.out.println(gantt.rander());
//        td.appendChild(this.document.createTextNode(gantt.rander()));
        td.appendChild(gantt.getSvg());
        tr.appendChild(td);
        table.appendChild(tr);
        return table;

    }
    public String rander() {
        this.head();
        this.body();
        StringWriter sw = new StringWriter();
        try {

            if (this.document != null) {
                this.document.appendChild(this.element);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
//                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(new DOMSource(this.document), new StreamResult(sw));
                System.out.println(sw.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
