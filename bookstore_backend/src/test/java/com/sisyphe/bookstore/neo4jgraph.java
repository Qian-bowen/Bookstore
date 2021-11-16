package com.sisyphe.bookstore;

import com.sisyphe.bookstore.entity.Tag;
import com.sisyphe.bookstore.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class neo4jgraph {
    private final static Logger log = LoggerFactory.getLogger(neo4jgraph.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(neo4jgraph.class, args);
        System.exit(0);
    }

    @Autowired
    private TagRepository tagRepository;

    @Bean
    void graph()
    {
        tagRepository.deleteAll();
//        Tag math=new Tag("math");
//        Tag science=new Tag("science");
//        Tag physics=new Tag("physics");
        Tag language=new Tag("language");
        Tag chinese=new Tag("chinese");
        Tag english=new Tag("english");
        Tag literature=new Tag("literature");
        Tag novel=new Tag("novel");
        Tag fiction=new Tag("fiction");
        Tag children=new Tag("children");
        tagRepository.save(language);
        tagRepository.save(chinese);
        tagRepository.save(english);
        tagRepository.save(literature);
        tagRepository.save(novel);
        tagRepository.save(fiction);
        tagRepository.save(children);

        language=tagRepository.findByTag(language.getTag());
        language.relateTo(chinese);
        language.relateTo(english);
        tagRepository.save(language);

        chinese=tagRepository.findByTag(chinese.getTag());
        chinese.relateTo(literature);
        tagRepository.save(chinese);

        english=tagRepository.findByTag(english.getTag());
        english.relateTo(literature);
        tagRepository.save(english);

        literature=tagRepository.findByTag(literature.getTag());
        literature.relateTo(novel);
        literature.relateTo(fiction);
        tagRepository.save(literature);

        fiction=tagRepository.findByTag(fiction.getTag());
        fiction.relateTo(children);
        tagRepository.save(fiction);

        novel=tagRepository.findByTag(novel.getTag());
        novel.relateTo(children);
        tagRepository.save(novel);
        //match (t:Tag{tag:"literature"})-[*1..2]->(n:Tag) return n


    }


}
