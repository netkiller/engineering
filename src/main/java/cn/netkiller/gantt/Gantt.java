package cn.netkiller.gantt;

import lombok.Data;

import java.util.Vector;
@Data
public class Gantt {
    private int width;
    private int height;
    public Gantt(){}
    public Gantt(int width, int height){
        this.width = width;
        this.height = height;
    }

    public String rander(){
        try {
            Svg svg = new Svg();
            svg.createDom();
        }catch (Exception e) {
            e.printStackTrace();
        }

        Vector svg = new Vector();
        svg.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        return svg.toString();
    }
    public static void main(String[] args) {
        Gantt gantt = new Gantt();
        System.out.println(gantt.rander());
    }
}
