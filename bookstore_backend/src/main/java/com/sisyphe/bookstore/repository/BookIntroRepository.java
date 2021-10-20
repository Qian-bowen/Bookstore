package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.BookIntro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface BookIntroRepository extends SolrCrudRepository<BookIntro, Integer> {
    @Query("name:*?0* OR description:*?0*")
    HighlightPage<BookIntro> getByDescriptionHighlight(String searchTerm, Pageable pageable);
}
