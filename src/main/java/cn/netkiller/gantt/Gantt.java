package cn.netkiller.gantt;

import cn.netkiller.gantt.svg.*;
import lombok.Data;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

@Data
public class Gantt {
    private int width;
    private int height = 1000;
    Svg svg = new Svg();

    public Gantt() {
    }

    public Gantt(int width, int height) {
        this.width = width;
        this.height = height;
    }

    int dayWidth = 30;

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public int getDayOfWeek(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
//        System.out.println(calendar.getFirstDayOfWeek());
//        System.out.println(calendar.getTime().toLocaleString());
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public String getDayNameOfWeek(int week) {
        //        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        if (week < 0)
            week = 0;
        return weekDays[week - 1];
    }

    public int getDayOfWeek(String str) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(str);
            return this.getDayOfWeek(date);
//            System.out.println(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void calendar(Date begin, Date end) {
        var ref = new Object() {
            int x = 1;
            int vline = 1;
            String color = "#eeeeee";
        };
        int y = 1;
        int width = 30;
//        int height = 1000;
        var ref1 = new Object() {

        };

        IntStream intStream = IntStream.range(1, 31);
//
//        intStream.forEach(
//                System.out::println
//        );
//        Group group = new Group("day");

        intStream.forEach(day -> {
                    ref.x = (this.dayWidth * (day - 1)) + ref.vline;

                    int nameOfWeek = this.getDayOfWeek(String.format("%s-%02d-%02d", "2023", 3, day));

                    if (nameOfWeek == 1 || nameOfWeek == 7) {
                        ref.color = "#dddddd";
                    } else {
                        ref.color = "#eeeeee";
                    }
//                    System.out.println(String.format("%s-%s-%02d:%s", "2023","03",day,nameOfWeek));
                    this.svg.rectangle(new Rectangle(ref.x, 0, this.dayWidth, this.height).fill(ref.color));
                    this.svg.text(new Text(this.getDayNameOfWeek(nameOfWeek), ref.x, 15, 20).fill("grey"));
                    this.svg.text(new Text(String.valueOf(day), ref.x, 35, 20).fill("black"));
                    ref.vline++;
                }
        );
//        this.svg.appendChild(group);


    }

    public String rander() {

        try {
            this.calendar(new Date(), new Date());
//            this.svg.save("");

        } catch (Exception e) {
            e.printStackTrace();
        }


//        Vector svg = new Vector();
//        svg.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        return this.svg.toString();
//        return this.svg.getSvg();
    }
    public Element getSvg(){
        return this.svg.getSvg();
    }

    public static void main(String[] args) {
        Gantt gantt = new Gantt();
        System.out.println(gantt.rander());
    }
}
