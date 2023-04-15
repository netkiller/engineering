package cn.netkiller.controller.project;

import cn.netkiller.domain.project.Project;
import cn.netkiller.record.Gantt;
import cn.netkiller.repository.project.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Iterable;
import java.util.List;

@RestController
@RequestMapping("/project")
@Tag(name = "项目模块")
@CrossOrigin(origins = "*")
public class ProjectRestController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectRestController.class);

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectRestController() {

    }

    @Operation(summary = "普通body请求")
    @GetMapping("/index")
    public String index() {
        return "Hello world!!!\r\n";
    }

    @Operation(summary = "甘特图起始和结束")
    @GetMapping("/init")
    public Gantt init() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Gantt gantt = new Gantt(dateFormat.format(projectRepository.findMinStart()), dateFormat.format(projectRepository.findMaxFinish()));
        System.out.println(gantt);
        return gantt;

    }

    @Operation(summary = "甘特图资源列表")
    @GetMapping("/resource/lists")
    public List<String> resourceList() {
        List<String> resourceLists = projectRepository.findAllResourceGroupByResource();
        System.out.println(resourceLists);
        return resourceLists;
    }

    @GetMapping("/lists")
    @ResponseBody
//    public ResponseEntity<List<Project>> list() {
    public Iterable<Project> lists() {
        Iterable<Project> projects = projectRepository.findAll();
        return (projects);
    }

    //
//    ResponseEntity.status(HttpStatus.OK)
//            .body(Object)
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody Project project) {
        projectRepository.save(project);
//        projectRepository.save(new Project("test", new Date(), new Date(), "neo"));
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/change")
    @ResponseBody
    public ResponseEntity change(@RequestBody Project project) {

        try {
            Project tmp = projectRepository.findOneById(project.getId());

            if (tmp == null) {
                return ResponseEntity.ok(false);
            }

            if (project.getName() != null) {
                tmp.setName(project.getName());
            }
            if (project.getStart() != null) {
                tmp.setStart(project.getStart());
            }
            if (project.getFinish() != null) {
                tmp.setFinish(project.getFinish());
            }
            if (project.getResource() != null) {
                tmp.setResource(project.getResource());
            }
            tmp.setMtime(new Date());

            logger.info(tmp.toString());
            projectRepository.save(tmp);


        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }


        return ResponseEntity.ok(true);
    }
}
