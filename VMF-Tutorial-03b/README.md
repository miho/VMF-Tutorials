# Tutorial 03b · Cross references

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-03/README.md) · [Next](../VMF-Tutorial-04/README.md)

Use `@Refers` when an object can be linked from multiple places. Here a book can have several authors, and an author can be linked to several books. A book and its authors remain separate objects with their own lifetimes. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial03b/vmfmodel/Book.java) · [Java example](src/main/java/eu/mihosoft/vmf/tutorial03b/Main.java)

```java
interface Book {
    String getTitle();

    @Refers(opposite = "books")
    Author[] getAuthors();
}

interface Author {
    String getName();

    @Refers(opposite = "authors")
    Book[] getBooks();
}
```

The `opposite` names make each link reciprocal. Add a book through an author's builder or generated `getBooks()` list and the author's entry appears in `book.getAuthors()`. Removing a book from Lin's list removes Lin from the inverse list. Ada's link remains.

```java
Book book = Book.newBuilder().withTitle("My Book").build();
Author ada = Author.newBuilder().withName("Ada").withBooks(book).build();
Author lin = Author.newBuilder().withName("Lin").withBooks(book).build();
lin.getBooks().remove(book);
```

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Authors: [Ada, Lin]
Authors after removing Lin's link: [Ada]
Ada still lists the book: true
```
