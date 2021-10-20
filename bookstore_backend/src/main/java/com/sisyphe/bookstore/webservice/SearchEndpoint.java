package com.sisyphe.bookstore.webservice;

import bookstore.search.book.GetBookSearchRequest;
import bookstore.search.book.GetBookSearchResponse;
import com.sisyphe.bookstore.entity.BookIntro;
import com.sisyphe.bookstore.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class SearchEndpoint {
    private static final String NAMESPACE_URI = "http://bookstore/search/book";
    @Autowired
    private SearchService searchService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookSearchRequest")
    @ResponsePayload
    public GetBookSearchResponse getSearchResult(@RequestPayload GetBookSearchRequest request) {
        GetBookSearchResponse response = new GetBookSearchResponse();
        HighlightPage<BookIntro> bookIntros = searchService.getByDescriptionHighlight(request.getSearch(), 10);
        if (bookIntros.isEmpty()) {
            return response;
        }
        for (BookIntro bookIntro : bookIntros) {
            GetBookSearchResponse.Result tmp = new GetBookSearchResponse.Result();
            tmp.setId(bookIntro.getId());
            tmp.setName(bookIntro.getName());
            tmp.setDescription(bookIntro.getDescription());
            response.getResult().add(tmp);
        }

        return response;
    }


}
