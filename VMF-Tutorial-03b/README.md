# VMF Tutorial 3b

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-04/README.md)

## Cross References

### What you will learn

In this tutorial you will learn how to

- declare cross references

### Declaring Cross References

For this tutorial we use the following model definition:

```java
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
```

In the above code sample the `Book` interface declares that it references one or many `Author` objects. Thus, we use the `@Refers()` annotation and declare the `books` property of the `Author` interface as the opposite. In the `Author` interface we use the `@Refers` annotation to indicate that the referenced property in `Book` is the `authors` property.

Now let's instantiate a `Book`, a `Author` and add the author to the books `authors` list.

```java
// create a book instance
Book book = Book.newBuilder().withTitle("My Book").build();

// and an author. we reference the book.
Author author = Author.newBuilder().withName("The Author").withBooks(book).build();
```

Since we declared a cross reference we expect the book to reference the author. Now we can check the `getAuthors()` method, i.e. the `authors` property:

```java
// cross references make it possible: the book also references the author
if(book.getAuthors().size()==1) {
    System.out.println("#referenced-author: "+ book.getAuthors().get(0));
} else {
    throw new RuntimeException("We referenced the book and therefore, the book has to reference the author.");
}   
```

## Conclusion

Congrats, you have successfully declared your first cross reference.  

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-03b). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-04/README.md)



