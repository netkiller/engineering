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
        this.svg.setAttribute("xmlns:xlink","http://www.w3.org/1999/xlink");
        this.svg.setAttribute("width","1980");
        this.svg.setAttribute("height","1080");
        this.svg.setAttribute("viewBox","0 0 1980 1080");
    }
    public Element group(String id){
        Element element = this.xml.createElement("group");
        element.setAttribute("id", id);
//        this.svg.appendChild(element);
        return element;
    }
    public Element text(Text t){
        Element element = this.xml.createElement("text");
//        element.setAttribute("id", t.id);
        element.setAttribute("x", String.valueOf(t.x()));
        element.setAttribute("y", String.valueOf(t.y()));
        element.setAttribute("font-size", String.valueOf(t.size()));
        element.appendChild(this.xml.createTextNode(t.text()));
        return element;
    }
    public Element appendChild(Node element){
        this.svg.appendChild(element);
        return this.svg;
    }
    public void createDom(){

        Element school,student;
        Element name = null;
        Element num = null;
        Element classes = null;
        Element address = null;
        Element tel = null;
        try{

            if(this.xml != null){
                //创建school元素

//                //创建student元素
//                student = doc.createElement("Student");
//                //设置元素Student的属性值为231
//                student.setAttribute("examId", "23");
//                //创建名称为Name的元素
//                name = doc.createElement("Name");
//                //创建名称为 香香 的文本节点并作为子节点添加到name元素中
//                name.appendChild(doc.createTextNode("香香"));
//                //将name子元素添加到student中
//                student.appendChild(name);
//                /**
//                 * 下面的元素依次加入即可
//                 * */
//                num = doc.createElement("Num");
//                num.appendChild();
//                student.appendChild(num);
//
//                classes = doc.createElement("Classes");
//                classes.appendChild(doc.createTextNode("眼视光5"));
//                student.appendChild(classes);
//
//                address = doc.createElement("Address");
//                address.appendChild(doc.createTextNode("浙江温州"));
//                student.appendChild(address);
//
//                tel = doc.createElement("Tel");
//                tel.appendChild(doc.createTextNode("123890"));
//                student.appendChild(tel);
//
//                //将student作为子元素添加到树的根节点school
//                school.appendChild(student);
//                //添加到文档树中
//                doc.appendChild(school);
                this.xml.appendChild(this.svg);
                //将内存中的文档通过文件流生成insertSchool.xml,XmlDocument位于crison.jar下
//                ((XmlDocument)doc).write(new FileOutputStream("/tmp/school.xml"));
                StringWriter sw = new StringWriter();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");
                transformer.setOutputProperty(OutputKeys.METHOD,"xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
                transformer.transform(new DOMSource(this.xml), new StreamResult(sw));
                System.out.println(sw.toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            Svg svg = new Svg();
            Node element = svg.group("test");
            element.appendChild(svg.text(new Text("Text", 5,5,10)));
//            Node element = svg.text("Text");
            svg.appendChild(element);
            svg.createDom();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
