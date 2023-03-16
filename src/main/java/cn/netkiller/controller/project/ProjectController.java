package cn.netkiller.controller.project;

import cn.netkiller.domain.project.Project;
import cn.netkiller.repository.ArticleRepository;
import cn.netkiller.repository.project.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/project")
@Tag(name = "项目模块")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectController() {
        // TODO Auto-generated constructor stub
    }

    @Operation(summary = "普通body请求")
    @GetMapping("/index")
    public String index() {
        return "Hello world!!!\r\n";
    }

    @GetMapping("/save")
    @ResponseBody
    public String save() {
        projectRepository.save(new Project("test", new Date(), new Date(), "neo"));
        return "OK";
    }
}
