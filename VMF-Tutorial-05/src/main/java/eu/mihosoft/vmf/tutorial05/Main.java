package eu.mihosoft.vmf.tutorial05;

public class Main {
    public static void main(String[] args) {
        Node source = Node.newBuilder()
                .withName("Original")
                .withId(3)
                .build();
        Node destination = Node.newBuilder()
                .withName("Placeholder")
                .withId(9)
                .build();

        WithName.newBuilder().applyFrom(source).applyTo(destination);

        System.out.printf("Source: %s (ID %d)%n", source.getName(), source.getId());
        System.out.printf("Destination: %s (ID %d)%n", destination.getName(), destination.getId());
    }
}
