package com.sisyphe.bookstore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sisyphe.bookstore.entity.BookIntro;
import com.sisyphe.bookstore.service.SearchService;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * RESTful Webservice
     * @param searchStr
     * @return
     */
    @GetMapping("/search/book/intro")
    public Msg searchBookIntro(@RequestParam(name = "searchStr") String searchStr)
    {
        HighlightPage<BookIntro> bookIntros = searchService.getByDescriptionHighlight(searchStr,10);
        if(bookIntros.isEmpty())
        {
            return new Msg(MsgCode.ERROR);
        }
        JSONArray jsonArray=new JSONArray();
        for(BookIntro bookIntro:bookIntros)
        {
            jsonArray.add(BookIntro.getJsonObject(bookIntro));
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result",jsonArray);
        return new Msg(MsgCode.SUCCESS,jsonObject);
    }


}
