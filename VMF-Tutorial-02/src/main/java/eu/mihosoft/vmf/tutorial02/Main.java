package eu.mihosoft.vmf.tutorial02;

public class Main {
    public static void main(String[] args) {
        Parent parent = Parent.newInstance();
        var subscription = parent.vmf().changes().addPropertyListener("name", change -> {
            var values = change.propertyChange().orElseThrow();
            System.out.printf("%s: %s -> %s%n", change.propertyName(), values.oldValue(), values.newValue());
        });

        parent.setName("Parent 1");
        parent.setName("Parent 2");
        subscription.unsubscribe();

        parent.setName("Parent 3");
        System.out.println("Name after unsubscribe: " + parent.getName());
    }
}
