package eu.mihosoft.vmf.tutorial03b;

import eu.mihosoft.vmf.runtime.core.Change;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a book instance
        Book book = Book.newBuilder().withTitle("My Book").build();

        // and an author. we reference the book.
        Author author = Author.newBuilder().withName("The Author").withBooks(book).build();

        // the book also references the author
        if(book.getAuthors().size()==1) {
            System.out.println("#referenced-author: "+ book.getAuthors().get(0));
        } else {
            throw new RuntimeException("We referenced the book and therefore, the book has to reference the author.");
        }        
    }
}