package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Tag;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface TagRepository extends Neo4jRepository<Tag,Long> {
    Tag findByTag(String tag);

    @Query("match (t:Tag{tag:$Name})-[*1..2]->(n:Tag) return n")
    List<Tag> getRelateTag(String Name);
}
