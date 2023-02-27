package cn.netkiller.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.netkiller.domain.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);

}
