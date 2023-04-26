package cn.netkiller.controller.project;

import cn.netkiller.domain.project.Project;
import cn.netkiller.record.Gantt;
import cn.netkiller.repository.project.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/project/gantt")
@Tag(name = "项目甘特图模块")
@CrossOrigin(origins = "*")
@Slf4j
public class ProjectGanttRestController {

    @Autowired
    private ProjectRepository projectRepository;

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

    @GetMapping("/option/lists")
    @ResponseBody
    public ResponseEntity<Iterable<Project>> option() {
        Iterable<Project> projects = projectRepository.getOptions();
        return ResponseEntity.ok(projects);
    }


    @PostMapping("/save")
    @ResponseBody
    @Transactional
    public ResponseEntity<Project> save(@RequestBody Project project) {
        try {
            log.debug(project.toString());
            if (project.getMilestone() == true) {
                project.setFinish(project.getStart());
            }
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
    public ResponseEntity<Project> change(@RequestBody Project project) {
//        try {
        log.info(project.toString());
        Project tmp = projectRepository.findOneById(project.getId());
        log.info(tmp.toString());
        if (tmp == null) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(project);
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
        if (project.getProgress() != null) {
            tmp.setProgress(project.getProgress());
        }
        if (project.getPredecessor() != null) {
            if (project.getPredecessor() == 0L) {
                tmp.setPredecessor(null);
            } else {
                tmp.setPredecessor(project.getPredecessor());
            }

        }

//     里程被，开始和完成是同一天
        if (tmp.getStart() != tmp.getFinish()) {
            tmp.setMilestone(false);

        }
//        else {
//            tmp.setMilestone(true);
//        }
        if (project.getMilestone() != null) {
            tmp.setMilestone(project.getMilestone());
            if (project.getMilestone() == true) {
                tmp.setFinish(tmp.getStart());
            }
        }
        if (project.getProject() != null) {
            if (project.getProject().getId() == 0) {
                tmp.setProject(null);
            } else {
                tmp.setProject(project.getProject());
            }

        }

        if (tmp.getParent()) {
            Long n = projectRepository.countByParentId(tmp.getId());
            if (n == 0L) {
                tmp.setParent(false);
            }
//                log.info("parent number: {}", n);
        }
        tmp.setMtime(new Date());


        projectRepository.save(tmp);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(project);
//        }
        if (tmp.getProject() != null && tmp.getProject().getId() > 0) {
            Project parent = projectRepository.findOneById(tmp.getProject().getId());
            parent.setParent(true);
            projectRepository.save(parent);
            projectRepository.updateStartAndFinishById(tmp.getProject().getId());
        }
        return ResponseEntity.ok(tmp);
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

    // 更新父任务
//    SELECT id, parent_id FROM test.project where  exists (select id from project p where project.id = p.parent_id) limit 100;
    @GetMapping("/get/invalidParentTaskLists")
    public ResponseEntity<List<Long>> invalidParentTaskLists() {
        List<Long> project = projectRepository.getInvalidParentTaskLists();
        return ResponseEntity.ok(project);
    }
}
