package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.BookIntro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface BookIntroRepository extends SolrCrudRepository<BookIntro,Integer> {
    @Query("name:*?0* OR description:*?0*")
//    @Highlight(prefix = "<strong>", postfix = "</strong>")
    HighlightPage<BookIntro> getByDescriptionHighlight(String searchTerm, Pageable pageable);
}
