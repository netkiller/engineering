package cn.netkiller.controller.test;

import cn.netkiller.domain.Article;
import cn.netkiller.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class TestRestController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/save")
    @ResponseBody
    public String save() {
        articleRepository.save(new Article("Neo", "Chen"));
        return "OK";
    }

    @GetMapping("/all")
    @ResponseBody
    public String all() {

        for (Article article : articleRepository.findAll()) {
            System.out.println(article);
        }
        return "OK";
    }

}
