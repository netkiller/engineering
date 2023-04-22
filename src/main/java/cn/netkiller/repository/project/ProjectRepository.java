package cn.netkiller.repository.project;

import cn.netkiller.domain.User;
import cn.netkiller.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.lang.Iterable;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    //    Page<Project> findAll(Pageable pageable);
    Iterable<Project> findAll(Pageable pageable);

    //    @Query("SELECT MIN(start) AS min, MAX(finish) AS max FROM project")
//    Page<Project> findMore(Long maxId, Pageable pageable);
    @Query("SELECT MIN(start) FROM Project")
    Date findMinStart();

    @Query("SELECT MAX(p.finish) FROM Project p")
    Date findMaxFinish();
    @Query("SELECT resource FROM Project group by resource")
    List<String> findAllResourceGroupByResource();

    Project findOneById(Long id);

    List<Project> findByPredecessor(Long id);
}
