package com.sisyphe.bookstore.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;
import javax.persistence.Index;

@Data
@SolrDocument(collection = "bookstore_core")
public class BookIntro {
    @Id
    @Indexed(name = "id",type = "int")
    @Field
    private Integer id;

    @Indexed(name = "name", type = "string")
    @Field
    private String name;

    @Indexed(name = "description", type = "string")
    @Field
    private String description;

    public static JSONObject getJsonObject(BookIntro bookIntro)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",bookIntro.getId());
        jsonObject.put("name",bookIntro.getName());
        jsonObject.put("description",bookIntro.getDescription());
        return jsonObject;
    }

}
