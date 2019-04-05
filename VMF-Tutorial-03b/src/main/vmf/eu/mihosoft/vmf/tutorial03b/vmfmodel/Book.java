package eu.mihosoft.vmf.tutorial03b.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Book {

    String getTitle();

    @Refers(opposite="books")
    Author[] getAuthors();
}

interface Author {
    String getName();

    @Refers(opposite="authors")
    Book[] getBooks();
}