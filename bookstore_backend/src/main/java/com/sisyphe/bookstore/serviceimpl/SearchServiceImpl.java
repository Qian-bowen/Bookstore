package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.entity.BookIntro;
import com.sisyphe.bookstore.repository.BookIntroRepository;
import com.sisyphe.bookstore.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private BookIntroRepository bookIntroRepository;

    @Override
    public HighlightPage<BookIntro> getByDescriptionHighlight(String searchTerm, Integer maxResult) {
        PageRequest pageRequest = PageRequest.of(0, maxResult);
        return bookIntroRepository.getByDescriptionHighlight(searchTerm, pageRequest);
    }
}
