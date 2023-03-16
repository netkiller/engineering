package cn.netkiller.repository;

import cn.netkiller.domain.User;
import jakarta.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // 默认提供了Optional<User> findById(Long id);
    List<User> findByName(String name);
//	User findByName(Long name);

	@Query("select u from User u where u.id <= ?1")
	Page<User> findMore(Long maxId, Pageable pageable);

//	@Modifying
//	@Transactional
//	@Query("update User u set u.name = ?1 where u.id = ?2")
//	int updateById(String name, Long id);

}
