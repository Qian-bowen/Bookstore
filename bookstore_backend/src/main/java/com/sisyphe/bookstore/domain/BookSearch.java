package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.constant.SearchType;

public class BookSearch {
    SearchType type;
    String name;

    public BookSearch() {
    }

    public BookSearch(SearchType searchType, String bookName) {
        type = searchType;
        name = bookName;
    }

    public String getName() {
        return name;
    }

    public SearchType getType() {
        return type;
    }
}
