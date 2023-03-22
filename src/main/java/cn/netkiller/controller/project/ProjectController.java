package cn.netkiller.controller.project;

import cn.netkiller.gantt.html.Html;
import cn.netkiller.gantt.svg.A;
import cn.netkiller.gantt.svg.Rect;
import cn.netkiller.gantt.svg.Svg;
import cn.netkiller.gantt.svg.Text;
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
    public String html() {
        Html html = new Html();
        return html.rander();
    }
}
