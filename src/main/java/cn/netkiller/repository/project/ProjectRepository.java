package cn.netkiller.repository.project;

import cn.netkiller.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.lang.Iterable;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    //    Page<Project> findAll(Pageable pageable);
    Iterable<Project> findAll(Pageable pageable);
}
