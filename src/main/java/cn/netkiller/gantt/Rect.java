package cn.netkiller.gantt;

public record Rect(int x, int y,int width, int height,String fill) {
    public Rect(int x, int y,int width, int height){
        this(x,y,width,height,"");
    }
}
