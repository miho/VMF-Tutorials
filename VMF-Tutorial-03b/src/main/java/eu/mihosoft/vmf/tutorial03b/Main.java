package eu.mihosoft.vmf.tutorial03b;

public class Main {
    public static void main(String[] args) {
        Book book = Book.newBuilder().withTitle("My Book").build();
        Author ada = Author.newBuilder().withName("Ada").withBooks(book).build();
        Author lin = Author.newBuilder().withName("Lin").withBooks(book).build();

        System.out.println("Authors: " + book.getAuthors().stream().map(Author::getName).toList());
        lin.getBooks().remove(book);
        System.out.println("Authors after removing Lin's link: "
                + book.getAuthors().stream().map(Author::getName).toList());
        System.out.println("Ada still lists the book: " + (ada.getBooks().get(0) == book));
    }
}
