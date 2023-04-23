package cn.netkiller.repository.project;


import cn.netkiller.domain.project.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE project p, (SELECT MIN(start) AS start, MAX(finish) AS finish FROM project WHERE parent_id = :id) t SET p.start = t.start, p.finish = t.finish WHERE p.id = :id")
    public void updateStartAndFinishById(@Param("id") Long id);

    @Query("SELECT id,name FROM Project")
    Iterable<Project> getOptions();

    @Query(nativeQuery = true, value = "select t1.id from project t1 where parent = b'1' and not exists (select 1 from project t2 where t1.id = t2.parent_id)")
    public List<Long> getInvalidParentTaskLists();

    @Query(value = "Select count(1) from project where parent_id = :id", nativeQuery = true)
    public Long countByParentId(Long id);
}
