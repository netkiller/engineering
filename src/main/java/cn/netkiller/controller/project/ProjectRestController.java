package cn.netkiller.controller.project;

import cn.netkiller.domain.project.Project;
import cn.netkiller.record.Gantt;
import cn.netkiller.repository.project.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @PostMapping("/save")
    @ResponseBody
    @Transactional
    public ResponseEntity<Project> save(@RequestBody Project project) {
        try {
            logger.debug(project.toString());

            if (project.getProject() != null && project.getProject().getId() > 0) {
                Project parent = projectRepository.findOneById(project.getProject().getId());
                parent.setParent(true);
                projectRepository.save(parent);
            } else {
                project.setProject(null);
            }
            projectRepository.save(project);
        } catch (Exception e) {
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.ok(project);
    }

    @PostMapping("/change")
    @ResponseBody
    @Transactional
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

    @GetMapping("/get/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Project project = projectRepository.findOneById(id);
        ResponseEntity<Project> body = ResponseEntity.status(HttpStatus.OK).body(project);
        return body;
    }
    @GetMapping("/get/next/{id}")
    public ResponseEntity<List<Project>> next(@PathVariable Long id) {
        List<Project> project = projectRepository.findByPredecessor(id);
        return ResponseEntity.ok(project);
    }
}
