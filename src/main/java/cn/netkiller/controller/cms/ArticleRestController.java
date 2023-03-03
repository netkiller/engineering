package cn.netkiller.controller.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cn.netkiller.domain.Article;
import cn.netkiller.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//
//import api.domain.Article;
//import api.domain.Article.Author;
//import api.domain.Hypermedia;
//import api.repository.ArticleRepository;
//import api.repository.HypermediaRepository;
//
@RestController
@RequestMapping("/article")
public class ArticleRestController {
//
//	@Autowired
//	private ArticleRepository articleRepository;
//
//	@Autowired
//	private HypermediaRepository hypermediaRepository;
//
//	@Autowired
//	private MongoTemplate mongoTemplate;
//
//	public ArticleRestController() {
//		// TODO Auto-generated constructor stub
//	}

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

//	@GetMapping("/save")
//	public Article ref() {
//
//		Article article = new Article();
//		article.setTitle("标题");
//		article.setDescription("摘要");
//		article.setTag("标签");
//
//		List<Author> author = new ArrayList<Author>();
//		author.add(new Author("neo", "chen"));
//		author.add(new Author("jerry", "lee"));
//		article.setAuthor(author);
//
//		Hypermedia hypermedia = new Hypermedia("AAA", "BBB", "CCC");
//		hypermediaRepository.save(hypermedia);
//
//		List<Hypermedia> hypermedias = new ArrayList<Hypermedia>();
//		hypermedias.add(hypermedia);
//
//		article.setHypermedia(hypermedias);
//
//		articleRepository.save(article);
//
//		System.out.println(article);
//
//		return article;
//	}
//
//	@GetMapping("/get/{id}")
//	public Optional<Article> get(@PathVariable String id) {
//		// Article article = new Article();
//		// article.setId(id);
//		Optional<Article> tmp = articleRepository.findById(id);
//		return tmp;
//	}
//
//	@GetMapping("/update")
//	public void remove() {
//		// Article article = new Article();
//		// article.setId(id);
//
//		Query query = Query.query(Criteria.where("author.firstname").is("neo"));
//		Update update = new Update().set("author.$.firstname", "netkiller");
//		mongoTemplate.updateFirst(query, update, Article.class);
//
//		// return tmp;
//	}
//
//	@GetMapping("/push")
//	public void push() {
//		// Article article = new Article();
//		// article.setId(id);
//
//		Query query = Query.query(Criteria.where("id").is("5bbf091efd9557069c4a25c5"));
//		Update update = new Update().push("author", new Author("neo", "chen"));
//		mongoTemplate.updateFirst(query, update, Article.class);
//		// return tmp;
//	}
//
//	@GetMapping("/pull")
//	public void pull() {
//
//		Query query = Query.query(Criteria.where("id").is("5bbf091efd9557069c4a25c5"));
//		Update update = new Update().pull("author", new Author("jerry", "lee"));
//		mongoTemplate.updateFirst(query, update, Article.class);
//
//	}
//
}
