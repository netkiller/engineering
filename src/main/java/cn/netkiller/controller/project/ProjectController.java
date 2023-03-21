package cn.netkiller.controller.project;

import cn.netkiller.gantt.A;
import cn.netkiller.gantt.Rect;
import cn.netkiller.gantt.Svg;
import cn.netkiller.gantt.Text;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Node;

@Controller
@RequestMapping("/project")
@Tag(name = "项目模块")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

//    @Autowired
//    private ProjectRepository projectRepository;

    public ProjectController() {
        // TODO Auto-generated constructor stub
    }

    @Operation(summary = "普通body请求")
    @GetMapping("/gantt")
    public String gantt() {
        return "Hello world!!!\r\n";
    }

    @GetMapping("/svg")
    @ResponseBody
    public String svg() {
        String html = "";
        try {
            Svg svg = new Svg();
            Node element = svg.group("test");
            element.appendChild(svg.text(new Text("Text", 5, 5, 10)));
//            Node element = svg.text("Text");
            svg.appendChild(element);
            svg.appendChild(svg.rectangle(new Rect(1, 1, 10, 10)));
            Node a = svg.a(new A("https://www.netkiller.cn", "_blank"));
            a.appendChild(svg.text(new Text("Neo", 15, 15, 10)));
            svg.appendChild(a);
            html = svg.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }
}
