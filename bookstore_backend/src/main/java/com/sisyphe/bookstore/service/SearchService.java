package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.entity.BookIntro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;

public interface SearchService {
    HighlightPage<BookIntro> getByDescriptionHighlight(String searchTerm, Integer maxResult);
}
