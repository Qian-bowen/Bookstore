package com.sisyphe.bookstore.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@Node
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    private String tag;

    private Tag(){}

    public Tag(String tag){
        this.tag=tag;
    }

    @Relationship(type = "RELATE")
    public Set<Tag> relate_tag;

    public void relateTo(Tag t){
        if(relate_tag==null){
            relate_tag=new HashSet<>();
        }
        relate_tag.add(t);
    }
}
